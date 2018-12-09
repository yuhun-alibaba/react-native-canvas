// @flow

import React, { Component } from "react";
import { View, Dimensions } from "react-native";

import { ECCanvas } from "../../src/adaption";

const windowWidth = Dimensions.get("window").width;
const canvasWidth = windowWidth;
const canvasHeight = 300;

export default function createECChart(initChart) {
  return class ECChart extends Component<any> {
    draw = (canvas: any, echarts) => {
      initChart(canvas, echarts, canvasWidth, canvasHeight);
    };

    render() {
      return (
        <ECCanvas
          style={{
            marginTop: 20,
            width: canvasWidth,
            height: canvasHeight,
            backgroundColor: "#ffffff"
          }}
          onReady={this.draw}
        />
      );
    }
  };
}
