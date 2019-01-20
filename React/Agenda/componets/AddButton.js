import React, { Component } from "react";
import { Text, Button } from "native-base";

export default class AddButton extends Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <Button
        style={{
          width: 60,
          height: 60,
          borderRadius: 30,
          backgroundColor: "#ee6e73",
          position: "absolute",
          bottom: 10,
          right: 10
        }}
        title="Adicionar Novo"
        onPress={this.props.handleButtonClick}
      >
        <Text style={{ fontSize: 10 }}>Novo</Text>
      </Button>
    );
  }
}
