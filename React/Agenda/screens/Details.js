import React from "react";
import { ListView, AsyncStorage, ToastAndroid } from "react-native";
import AddButton from "./../componets/AddButton";

import {
  Container,
  Content,
  Button,
  Icon,
  List,
  ListItem,
  Text,
  Body,
  Right
} from "native-base";

export default class Details extends React.Component {
  constructor(props) {
    super(props);
    this.ds = new ListView.DataSource({ rowHasChanged: (r1, r2) => r1 !== r2 });
    let events = this.props.navigation.getParam("events", {});
    let inner = [];
    if (
      events !== undefined &&
      events !== null &&
      events.hasOwnProperty("events")
    ) {
      if (events.events.length > 0) {
        inner = events.events.map(e => {
          if (e != null)
            return { title: e.title, description: e.description, hour: e.hour };
        });
      }
    }
    let allData = this.props.navigation.getParam("allData", null);
    this.state = {
      basic: true,
      listViewData: inner,
      allData: allData,
      actualDate: events ? events.date : null
    };
  }
  static navigationOptions = ({ navigation }) => {
    return {
      title: "Data: " + navigation.getParam("date", "")
    };
  };

  async deleteRow(secId, rowId, rowMap) {
    rowMap[`${secId}${rowId}`].props.closeRow();
    const newData = [...this.state.listViewData];
    newData.splice(rowId, 1);
    await this._updateStorage(newData);
    this.props.navigation.navigate("Home", { submited: true });

    /*     this.setState({ listViewData: newData });
     */
  }

  async _updateStorage(newData) {
    try {
      let stored = await AsyncStorage.getItem("Dates");
      let data = JSON.parse(stored);
      data[this.state.actualDate].events = newData;
      self = this;
      if (newData.length === 0) {
        data[this.state.actualDate] = null;
      }
      await AsyncStorage.setItem("Dates", JSON.stringify(data));
    } catch (err) {
      ToastAndroid.show("Error" + err.toString(), ToastAndroid.LONG);
    }
  }
  render() {
    const ds = new ListView.DataSource({
      rowHasChanged: (r1, r2) => r1 !== r2
    });
    return (
      <Container>
        <Content>
          <List
            // leftOpenValue={75}
            rightOpenValue={-75}
            dataSource={this.ds.cloneWithRows(this.state.listViewData)}
            renderRow={data => (
              <ListItem>
                <Body>
                  <Text> {data.title} </Text>
                  <Text style={{ fontSize: 13, color: "grey" }}>
                    {data.description}
                  </Text>
                </Body>
                <Right>
                  <Text note>{data.hour}</Text>
                </Right>
              </ListItem>
            )}
            /*       renderLeftHiddenRow={data => (
              <Button full onPress={() => alert(data)}>
                <Icon active name="information-circle" />
              </Button>
            )} */
            renderRightHiddenRow={(data, secId, rowId, rowMap) => (
              <Button
                full
                danger
                onPress={_ => this.deleteRow(secId, rowId, rowMap)}
              >
                <Icon active name="trash" />
              </Button>
            )}
          />
        </Content>
        <AddButton
          handleButtonClick={() =>
            this.props.navigation.push("New", {
              actualDate: this.state.actualDate
            })
          }
        />
      </Container>
    );
  }
}
