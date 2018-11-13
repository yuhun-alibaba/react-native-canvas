// @flow

import React, { Component } from "react";
import { TouchableWithoutFeedback, View, Text, Dimensions } from "react-native";
import Canvas from "../canvas";

const windowWidth = Dimensions.get("window").width;

type Props = {};

type State = {
  actions: Array<Object>
};

export default class Animate extends Component<Props, State> {
  state = {
    actions: [],
    running: false
  };

  canvas;

  timeIndex = 0;

  setCanvas = ref => {
    this.canvas = ref;
  };

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
      window.requestAnimationFrame(() => this.draw());
      this.timeIndex++;
    }, 20);
  }

  gameLoopEnd = () => {
    clearInterval(this.timer);
  };

  draw() {
    const x = 0 + this.timeIndex;
    const y = 0 + this.timeIndex / 3;

    if (x >= windowWidth) {
      this.timeIndex = 0;
      return;
    }

    this.drawContext(x, y);
  }

  drawContext(x = 0, y = 0) {
    if (!this.renderingContext) return;

    this.renderingContext.font = "16px sans-serif";
    this.renderingContext.fillText("hello world", 150, 200);
    this.renderingContext.fillRect(x, y, 50.0, 50.0);
    this.renderingContext.arc(x + 100, y + 100, 50.0, 0, Math.PI * 2, 1);
    this.renderingContext.fill();
    this.renderingContext.draw();
  }

  componentDidMount() {
    if (this.canvas) {
      this.renderingContext = this.canvas.getContext();
    }
  }

  render() {
    const { running } = this.state;

    return (
      <View style={{ justifyContent: "center", alignItems: "center" }}>
        <Canvas
          style={{
            width: windowWidth,
            height: 500,
            backgroundColor: "#dddddd"
          }}
          ref={this.setCanvas}
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
            <Text style={{ fontSize: 16 }}>{running ? "stop" : "start"}</Text>
          </View>
        </TouchableWithoutFeedback>
      </View>
    );
  }
}
