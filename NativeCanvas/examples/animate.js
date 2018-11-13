// @flow

import React, { Component } from 'react';
import { TouchableWithoutFeedback, View, Text, Dimensions } from "react-native";
import { Canvas, getContext } from "../canvas";

const windowWidth = Dimensions.get('window').width;

type Props = {};

type State = {
  actions: Array<Object>
};

export default class Animate extends Component<Props, State> {
  state = {
    actions: [],
    running: false
  }

  renderingContext = getContext();

  timeIndex = 0;

  toggleRunning = () => {
    this.setState({
      running: !this.state.running
    }, () => {
      if (this.state.running) {
        this.gameLoopStart();
      } else {
        this.gameLoopEnd();
      }
    })
  }

  gameLoopStart() {
    this.timer = setInterval(() => {
      window.requestAnimationFrame(() => this.draw())
      this.timeIndex ++;
    }, 20)
  }

  gameLoopEnd = () => {
    clearInterval(this.timer);
  }

  draw() {
    const x = 0 + this.timeIndex;
    const y = 0 + (this.timeIndex / 3);

    if (x >= windowWidth) {
      this.timeIndex = 0;
      return;
    }

    this.drawContext(x, y)
    this.setState({
      actions: this.renderingContext.actions
    })
  }

  drawContext(x = 0, y = 0) {
    this.renderingContext.clear();
    this.renderingContext.fillRect(x, y, 50.0, 50.0);
    this.renderingContext.arc(x + 100, y + 100, 50.0, 0, Math.PI * 2, 1);
    this.renderingContext.fill();
  }

  componentDidMount() {
    this.drawContext();
  }

  render() {
    const { actions, running } = this.state;

    return (
      <View style={{ justifyContent: 'center', alignItems: 'center'}}>
        <Canvas
          style={{
            width: windowWidth,
            height: 500,
            backgroundColor: "#dddddd",
          }}
          actions={actions}
        />
          <TouchableWithoutFeedback onPress={this.toggleRunning}>
            <View style={{
              marginTop: 30,
              width: 60,
              height: 30,
              justifyContent: 'center',
              alignItems: 'center',
              borderWidth: 1,
              borderColor: "#dddddd",
              borderRadius: 4
            }}><Text style={{fontSize: 16}}>{running ? "stop" : "start"}</Text></View>
          </TouchableWithoutFeedback>
      </View>
    );
  }
}

