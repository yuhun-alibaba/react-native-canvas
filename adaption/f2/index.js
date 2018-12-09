// @flow

import React, { PureComponent } from "react";

import createPanResponder from "../createPanResponder";
import createCanvas from "rn-canvas/src/createCanvas";
import Renderer from "./renderer";
import F2 from "./adaption";

type Props = {
  onReady?: Function
};

const Canvas = createCanvas(Renderer);

export default class F2Canvas extends PureComponent<Props> {
  canvas;

  createTouchEvent(name: string) {
    return ({ nativeEvent }) => {
      if (this.canvas) {
        this.canvas.emit(name, { ...nativeEvent, type: name });
      }
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

  onReady = canvas => {
    this.canvas = canvas;
    this.props.onReady && this.props.onReady(canvas, F2);
  };

  render() {
    return (
      <Canvas {...this.props} {...this.panHandlers} onReady={this.onReady} />
    );
  }
}
