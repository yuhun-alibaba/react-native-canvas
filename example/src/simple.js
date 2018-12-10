// @flow

import React, { Component } from "react";
import { View, Dimensions } from "react-native";

import Canvas from "rn-canvas";

const windowWidth = Dimensions.get("window").width;
const canvasWidth = windowWidth;
const canvasHeight = 300;

export default class Simple extends Component<any> {
  draw = canvas => {
    const ctx = canvas.getContext();

    ctx.font = "16px Helvetica";
    ctx.fillStyle = "red";
    ctx.textBaseline = "middle";
    ctx.textAlign = "right";

    ctx.translate(50, 50);
    ctx.save();
    ctx.beginPath();
    ctx.arc(50, 50, 20, 0, 6.283185307179586, false);
    ctx.fill();
    ctx.restore();
    ctx.save();
    ctx.fillRect(100, 100, 50, 50);
    ctx.fillText("fillText", 12, -8);
    ctx.restore();

    ctx.draw();
  };

  render() {
    return (
      <Canvas
        style={{
          marginTop: 20,
          width: canvasWidth,
          height: canvasHeight,
          backgroundColor: "#f4f4f4"
        }}
        onReady={this.draw}
      />
    );
  }
}
