// @flow

import React, { Component } from "react";
import { View, ScrollView, Dimensions } from "react-native";

import Line from "./line";
import Bar from "./bar";

const windowWidth = Dimensions.get("window").width;
const canvasWidth = windowWidth;

type Props = {};

type State = {};

export default class F2Example extends Component<Props, State> {
  render() {
    return (
      <ScrollView
        style={{
          width: windowWidth,
          flex: 1
        }}
      >
        <Line />
        <Bar />
      </ScrollView>
    );
  }
}
