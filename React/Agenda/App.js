import React, { Component } from "react";
import { Platform, StyleSheet } from "react-native";
import { createAppContainer, createStackNavigator } from "react-navigation";
import Home from "./screens/Home";
import New from "./screens/New";
import Details from "./screens/Details";
import AddButton from "./componets/AddButton";

const AppNavigator = createStackNavigator(
  {
    Home: Home,
    New: New,
    Details: Details,
    AddButton: AddButton
  },
  {
    initialRouteName: "Home"
  }
);

const AppContainer = createAppContainer(AppNavigator);

export default class App extends Component {
  constructor(props) {
    super(props);
  }
  render() {
    return <AppContainer />;
  }
}
