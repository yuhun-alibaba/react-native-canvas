// @flow

import React, { Component } from "react";
import { View, Dimensions } from "react-native";

import { CanvasAutoDrawing } from "../src/core";

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

    ctx.translate(10, 10);
    ctx.save();
    ctx.arc(3, 0, 3, 0, 6.283185307179586, false);
    ctx.fill();
    ctx.restore();
    ctx.save();
    ctx.fillText("其他  2%", 12, -8);
    ctx.restore();

    ctx.draw();
  };

  render() {
    return (
      <CanvasAutoDrawing
        style={{
          marginTop: 100,
          width: canvasWidth,
          height: canvasHeight,
          backgroundColor: "#dddddd"
        }}
        draw={this.draw}
      />
    );
  }
}
