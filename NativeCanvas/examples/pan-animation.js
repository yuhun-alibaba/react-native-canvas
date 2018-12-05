// @flow

import React, { Component } from "react";
import { View, Dimensions } from "react-native";

import { CanvasAutoDrawing } from "../src/core";
import createPanResponder from "../src/adaption/createPanResponder";

const windowWidth = Dimensions.get("window").width;
const canvasWidth = windowWidth;
const canvasHeight = 300;

export default class PanAnimation extends Component<any> {
  x = 150;
  y = 150;

  renderingContext;

  createTouchEvent(name: string) {
    return ({ nativeEvent }, gs) => {
      const touches = nativeEvent.touches;
      if (touches && touches.length > 0) {
        this.x = touches[0].locationX;
        this.y = touches[0].locationY;
      }
      this.paint();
    };
  }

  onTouchStart = this.createTouchEvent("touchstart");
  onTouchMove = this.createTouchEvent("touchmove");
  onTouchEnd = this.createTouchEvent("touchend");
  onTouchCancel = this.createTouchEvent("touchcacel");

  panHandlers = createPanResponder({
    start: this.onTouchStart,
    move: this.onTouchMove,
    end: this.onTouchEnd,
    cancel: this.onTouchCancel
  });

  draw = canvas => {
    this.renderingContext = canvas.getContext();
    this.paint();
  };

  paint() {
    const ctx = this.renderingContext;

    ctx.fillStyle = "red";
    ctx.strokeStyle = "blue";
    ctx.beginPath();
    ctx.arc(this.x, this.y, 50.0, 0, Math.PI * 2);
    ctx.fill();
    ctx.stroke();
    ctx.draw();
  }

  render() {
    return (
      <CanvasAutoDrawing
        {...this.panHandlers}
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
