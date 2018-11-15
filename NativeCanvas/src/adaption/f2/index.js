// @flow
// Auto drawing when component did mount

import React, { PureComponent } from "react";

import createPanResponder from "../../utils/createPanResponder";
import createCanvas from "../../core/createCanvas";
import F2 from "./adaption";
import Renderer from "./renderer";

type Props = {
  style?: any,
  draw: Function
};

const Canvas = createCanvas(Renderer);

export default class F2Canvas extends PureComponent<Props> {
  canvas;

  setRef = ref => {
    this.canvas = ref.canvas;
  };

  onTouchStart = ({ nativeEvent }) => {
    const event = { ...nativeEvent, type: "touchstart" };
    if (this.canvas) {
      this.canvas.emit("touchstart", event);
    }
  };

  onTouchMove = ({ nativeEvent }) => {
    const event = { ...nativeEvent, type: "touchmove" };
    if (this.canvas) {
      this.canvas.emit("touchmove", event);
    }
  };

  onTouchEnd = ({ nativeEvent }) => {
    const event = { ...nativeEvent, type: "touchend" };
    if (this.canvas) {
      this.canvas.emit("touchend", event);
    }
  };

  onTouchCancel = ({ nativeEvent }) => {
    const event = { ...nativeEvent, type: "touchcacel" };
    if (this.canvas) {
      this.canvas.emit("touchcacel", event);
    }
  };

  panHandlers = createPanResponder({
    start: this.onTouchStart,
    move: this.onTouchMove,
    end: this.onTouchEnd,
    cancel: this.onTouchCancel
  });

  componentDidMount() {
    if (this.canvas) {
      this.props.draw && this.props.draw(this.canvas, F2);
    }
  }

  render() {
    return <Canvas {...this.props} {...this.panHandlers} ref={this.setRef} />;
  }
}
