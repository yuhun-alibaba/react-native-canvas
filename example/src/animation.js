// @flow

import React, { Component } from "react";
import { TouchableWithoutFeedback, View, Text, Dimensions } from "react-native";
import Canvas from "rn-canvas";

const windowWidth = Dimensions.get("window").width;

type Props = {};

type State = {};

export default class Animation extends Component<Props, State> {
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
      this.paint();
      this.timeIndex++;
    }, 10);
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

    ctx.strokeStyle = "red";
    ctx.beginPath();
    ctx.arc(x + 100, y + 100, 50.0, 0, Math.PI * 2);
    ctx.stroke();
    ctx.draw();
  }

  componentWillUnmount() {
    this.gameLoopEnd();
  }

  render() {
    const { running } = this.state;

    return (
      <View
        style={{
          marginTop: 20,
          justifyContent: "center",
          alignItems: "center"
        }}
      >
        <Canvas
          style={{
            width: windowWidth,
            height: 300,
            backgroundColor: "#f4f4f4"
          }}
          onReady={this.draw}
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
              borderColor: "#f4f4f4",
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
