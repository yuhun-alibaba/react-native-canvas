// @flow

import React, { Component } from "react";
import { View, Dimensions } from "react-native";

import Canvas from "../canvas";

const windowWidth = Dimensions.get("window").width;
const canvasWidth = windowWidth;
const canvasHeight = 300;

type Props = {};

type State = {};

export default class Simple extends Component<Props, State> {
  canvas;

  setCanvas = ref => {
    this.canvas = ref;
  };

  componentDidMount() {
    if (this.canvas) {
      this.draw();
    }
  }

  draw() {
    const ctx = this.canvas.getContext();

    ctx.fillStyle = "red";

    ctx.save();
    ctx.translate(50, 50);
    ctx.fillRect(0, 0, 100, 100);
    ctx.restore();

    ctx.beginPath();
    ctx.strokeStyle = "blue";
    ctx.moveTo(20, 20);
    ctx.lineTo(200, 20);
    ctx.stroke();

    // Second path
    ctx.beginPath();
    ctx.strokeStyle = "green";
    ctx.moveTo(20, 20);
    ctx.lineTo(120, 120);
    ctx.stroke();

    ctx.draw();
  }

  render() {
    return (
      <Canvas
        style={{
          width: canvasWidth,
          height: canvasHeight,
          backgroundColor: "#dddddd"
        }}
        ref={this.setCanvas}
      />
    );
  }
}
