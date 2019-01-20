import React, { Component } from "react";
import { Platform, StyleSheet, AsyncStorage } from "react-native";
import { CalendarList, LocaleConfig } from "react-native-calendars";
import { Container } from "native-base";
import AddButton from "./../componets/AddButton";
LocaleConfig.locales["pt-PT"] = {
  monthNames: [
    "Janeiro",
    "Fevereiro",
    "Março",
    "Abril",
    "Maio",
    "Junho",
    "Julho",
    "Agosto",
    "Setembro",
    "Outubro",
    "Novembro",
    "Dezembro"
  ],
  monthNamesShort: [
    "Jan",
    "Fev",
    "Mar",
    "Abr",
    "Mai",
    "Jun",
    "Jul",
    "Ago",
    "Set",
    "Out",
    "Nov",
    "Dez"
  ],
  dayNames: [
    "Domingo",
    "Segunda-Feira",
    "Terça-Feira",
    "Quarta-Feira",
    "Quinta-Feira",
    "Sexta-Feira",
    "Sábado"
  ],
  dayNamesShort: ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"]
};

LocaleConfig.defaultLocale = "pt-PT";

export default class Home extends Component {
  constructor(props) {
    super(props);
    //AsyncStorage.clear();
    this.state = {
      markedDates: null
    };
    this.getDates = this.getDates.bind(this);
    this.getDates();
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    console.log("componentDidUpdate", this.state);
    if (this.props !== prevProps) {
      let { navigation } = this.props;
      let submited = navigation.getParam("submited", false);
      if (submited) {
        this.getDates();
      }
    }
  }

  static navigationOptions = {
    title: "Agenda"
  };

  async getDates() {
    try {
      const value = await AsyncStorage.getItem("Dates");
      if (value !== null) {
        let dates = JSON.parse(value);
        this.setState({
          markedDates: dates
        });
      }
    } catch (error) {
      ToastAndroid.show("Error", ToastAndroid.SHORT);
    }
  }
  render() {
    const { markedDates } = this.state;
    return (
      <Container>
        <CalendarList
          // Max amount of months allowed to scroll to the past. Default = 50
          pastScrollRange={50}
          // Max amount of months allowed to scroll to the future. Default = 50
          futureScrollRange={50}
          // Enable or disable scrolling of calendar list
          scrollEnabled={true}
          // Enable or disable vertical scroll indicator. Default = false
          showScrollIndicator={true}
          onPressArrowLeft={substractMonth => substractMonth()}
          // Handler which gets executed when press arrow icon left. It receive a callback can go next month
          onPressArrowRight={addMonth => addMonth()}
          minDate={new Date()}
          onDayPress={day => {
            this.props.navigation.push("Details", {
              events: this.state.markedDates[day.dateString],
              date: day.dateString,
              allData: this.state.markedDates
            });
          }}
          markedDates={markedDates}
        />
        <AddButton
          handleButtonClick={() => this.props.navigation.push("New")}
        />
      </Container>
    );
  }
}
