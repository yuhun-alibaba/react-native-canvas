// @flow

import React, { Component } from "react";
import { View, Dimensions } from "react-native";

import { ECCanvas } from "rn-canvas-adaption";

const windowWidth = Dimensions.get("window").width;

export default function createECChart(initChart, { width, height } = {}) {
  const canvasWidth = width || windowWidth;
  const canvasHeight = height || 300;

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
