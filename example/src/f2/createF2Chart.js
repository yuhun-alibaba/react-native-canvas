// @flow

import React, { Component } from "react";
import { View, Dimensions } from "react-native";

import { F2Canvas } from "rn-canvas-adaption";

const windowWidth = Dimensions.get("window").width;
const canvasWidth = windowWidth;
const canvasHeight = 300;

export default function createF2Chart(initChart) {
  return class F2Chart extends Component<any> {
    height = this.props.height || canvasHeight;

    draw = (canvas: any, F2) => {
      initChart(canvas, canvasWidth, this.height, F2);
    };

    render() {
      return (
        <F2Canvas
          style={[{
            marginTop: 20,
            width: canvasWidth,
            height: this.height,
            backgroundColor: "#ffffff"
          }, this.props.style]}
          onReady={this.draw}
        />
      );
    }
  };
}
