// @flow

import React, { Component } from "react";
import { TouchableWithoutFeedback, View, Text, Dimensions } from "react-native";
import { CanvasAutoDrawing } from "../src/core";

const windowWidth = Dimensions.get("window").width;

type Props = {};

type State = {};

export default class Animate extends Component<Props, State> {
  state = {
    running: false
  };

  timeIndex = 0;

  toggleRunning = () => {
    this.setState(
      {
        running: !this.state.running
      },
      () => {
        if (this.state.running) {
          this.gameLoopStart();
        } else {
          this.gameLoopEnd();
        }
      }
    );
  };

  gameLoopStart() {
    this.timer = setInterval(() => {
      window.requestAnimationFrame(() => this.paint());
      this.timeIndex++;
    }, 20);
  }

  gameLoopEnd = () => {
    clearInterval(this.timer);
  };

  draw = canvas => {
    this.renderingContext = canvas.getContext();
  };

  paint() {
    const x = 0 + this.timeIndex;
    const y = 0 + this.timeIndex / 3;

    if (x >= windowWidth) {
      this.timeIndex = 0;
      return;
    }

    this.drawContext(x, y);
  }

  drawContext(x = 0, y = 0) {
    const ctx = this.renderingContext;

    ctx.fillStyle = "red";
    ctx.fillRect(x, y, 50.0, 50.0);
    ctx.arc(x + 100, y + 100, 50.0, 0, Math.PI * 2, 1);
    ctx.stroke();
    ctx.draw();
  }

  render() {
    const { running } = this.state;

    return (
      <View
        style={{
          justifyContent: "center",
          alignItems: "center"
        }}
      >
        <CanvasAutoDrawing
          style={{
            width: windowWidth,
            height: 300,
            backgroundColor: "#dddddd"
          }}
          draw={this.draw}
        />
        <TouchableWithoutFeedback onPress={this.toggleRunning}>
          <View
            style={{
              marginTop: 30,
              width: 60,
              height: 30,
              justifyContent: "center",
              alignItems: "center",
              borderWidth: 1,
              borderColor: "#dddddd",
              borderRadius: 4
            }}
          >
            <Text
              style={{
                fontSize: 16
              }}
            >
              {running ? "stop" : "start"}
            </Text>
          </View>
        </TouchableWithoutFeedback>
      </View>
    );
  }
}
