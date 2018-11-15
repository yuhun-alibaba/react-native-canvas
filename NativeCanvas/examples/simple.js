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

    ctx.arc(3, 0, 3, 0, 6.283185307179586, false);
    ctx.fillText("其他  2%", 12, -8);
    ctx.fill();

    // ctx.save();
    // ctx.translate(50, 50);
    // ctx.fillRect(0, 0, 100, 100);
    // ctx.restore();

    // ctx.beginPath();
    // ctx.strokeStyle = "blue";
    // ctx.moveTo(20, 20);
    // ctx.lineTo(200, 20);
    // ctx.stroke();

    // // Second path
    // ctx.beginPath();
    // ctx.strokeStyle = "green";
    // ctx.moveTo(20, 20);
    // ctx.lineTo(120, 120);
    // ctx.stroke();

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
