// @flow

import React, { Component } from "react";
import { View, ScrollView, Dimensions } from "react-native";

import Area from "./area";
import Bar from "./bar";
import Column from "./column";
import K from "./k";
import Line from "./line";
import Pie from "./pie";

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
          backgroundColor: "#f4f4f4",
          flex: 1
        }}
      >
        <Area />
        <Bar />
        <Column />
        <K />
        <Line />
        <Pie />
      </ScrollView>
    );
  }
}
