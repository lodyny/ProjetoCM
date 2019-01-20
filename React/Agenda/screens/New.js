import React, { Component } from "react";
import {
  Platform,
  StyleSheet,
  Text,
  View,
  DatePickerAndroid,
  TouchableHighlight,
  AsyncStorage,
  ToastAndroid
} from "react-native";

const moment = require("moment");
const t = require("tcomb-form-native");

const NewEvent = t.struct({
  title: t.String,
  description: t.maybe(t.String),
  date: t.Date,
  hour: t.maybe(t.Date)
});

const options = {
  fields: {
    date: {
      label: "Data",
      mode: "date",
      config: {
        format: date => moment(date).format("YYYY-MM-DD")
      }
    },
    title: { label: "Titulo" },
    description: {
      label: "Descrição",
      multiline: true
    },
    hour: {
      label: "Hora",
      mode: "time",
      config: {
        format: date => moment(date).format("HH:mm")
      }
    }
  }
};

export default class New extends Component {
  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this._prepareData = this._prepareData.bind(this);

    let date = this.props.navigation.getParam("actualDate", null);
    this.state = {
      date: date
    };
  }

  static navigationOptions = {
    title: "Novo Evento"
  };

  async handleSubmit() {
    let value = this.refs.form.getValue();
    if (value) {
      let keyDate = moment(value.date).format("YYYY-MM-DD");
      let hour =
        value.hour !== undefined && value.hour !== null
          ? moment(value.hour).format("HH:mm")
          : "";
      let data = await this._prepareData(value, keyDate, hour);
      let record = { [keyDate]: data };
      let exist = false;
      let storedJson;
      try {
        let stored = await AsyncStorage.getItem("Dates");
        if (stored !== null) {
          storedJson = JSON.parse(stored);
          if (
            storedJson[data.date] !== undefined &&
            storedJson[data.date] !== null
          ) {
            console.log("storedJson[data.date]", storedJson[data.date]);
            if (!storedJson[data.date].hasOwnProperty("events")) {
              storedJson[data.date].events = [];
            }

            storedJson[data.date].events[storedJson[data.date].events.length] =
              data.events[0];
            exist = true;
          }
        }
      } catch (err) {
        console.error(err);
      }
      try {
        if (exist) {
          await AsyncStorage.removeItem("Dates");
          await AsyncStorage.setItem("Dates", JSON.stringify(storedJson));
        } else {
          await AsyncStorage.mergeItem("Dates", JSON.stringify(record));
        }
      } catch (err) {
        ToastAndroid.show("Error" + err.toString(), ToastAndroid.SHORT);
      } finally {
        this.props.navigation.navigate("Home", { submited: true });
      }
    }
  }

  _prepareData(val, dateStr, hour) {
    return new Promise((resolve, reject) => {
      try {
        let newVal = JSON.parse(JSON.stringify(val));
        newVal.date = dateStr;
        newVal.marked = true;
        newVal.dotColor = "blue";
        newVal.events = [
          {
            title: newVal.title,
            description: newVal.description,
            hour: hour
          }
        ];
        delete newVal.title;
        delete newVal.description;
        delete newVal.hour;
        if (newVal.marked === true) {
          resolve(newVal);
        }
      } catch (err) {
        reject(err);
      }
    });
  }

  render() {
    t.form.Form.i18n = {
      optional: "",
      required: " *"
    };
    const Form = t.form.Form;

    return (
      <View style={styles.container}>
        <Form
          ref="form"
          type={NewEvent}
          options={options}
          value={{ date: this.setState.date }}
        />
        <TouchableHighlight
          style={styles.button}
          onPress={this.handleSubmit}
          underlayColor="#99d9f4"
        >
          <Text style={styles.buttonText}>Guardar</Text>
        </TouchableHighlight>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
    marginTop: 50,
    padding: 20,
    backgroundColor: "#ffffff"
  },
  buttonText: {
    fontSize: 18,
    color: "white",
    alignSelf: "center"
  },
  button: {
    height: 36,
    backgroundColor: "#48BBEC",
    borderColor: "#48BBEC",
    borderWidth: 1,
    borderRadius: 8,
    marginBottom: 10,
    alignSelf: "stretch",
    justifyContent: "center"
  }
});
