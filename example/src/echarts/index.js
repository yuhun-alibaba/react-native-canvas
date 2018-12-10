// @flow

import React, { Component } from "react";
import { View, ScrollView, Dimensions } from "react-native";

import HeatMap from "./heatmap";
import Scatter from "./scatter";
import Pie from "./pie";
import Gauge from "./gauge";
import Graph from "./graph";
import Line from "./line";
import Bar from "./bar";

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
          backgroundColor: "#f4f4f4",
          flex: 1
        }}
      >
        <HeatMap />
        <Scatter />
        <Pie />
        <Gauge />
        <Graph />
        <Line />
        <Bar />
      </ScrollView>
    );
  }
}
