// @flow

import React, { Component } from "react";
import { View, ScrollView, Dimensions } from "react-native";

import Pie from "./pie";
import Gauge from "./gauge";
import K from "./k";

const windowWidth = Dimensions.get("window").width;
const canvasWidth = windowWidth;

type Props = {};

type State = {};

export default class EchartsExample extends Component<Props, State> {
  render() {
    return (
      <ScrollView
        style={{
          width: windowWidth,
          flex: 1
        }}
      >
        <Pie />
        <Gauge />
        <K />
      </ScrollView>
    );
  }
}
