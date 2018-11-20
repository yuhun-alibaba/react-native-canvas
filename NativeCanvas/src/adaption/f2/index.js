// @flow
// Auto drawing when component did mount

import React, { PureComponent } from "react";

import createPanResponder from "../createPanResponder";
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

  isInResponse = false;

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

  componentDidMount() {
    if (this.canvas) {
      this.props.draw && this.props.draw(this.canvas, F2);
    }
  }

  render() {
    return (
      <Canvas {...this.props} {...this.panHandlers} ref={this.setRef} />
    );
  }
}
