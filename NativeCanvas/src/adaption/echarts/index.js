// @flow

import React, { PureComponent } from "react";

import createPanResponder from "../createPanResponder";
import createCanvas from "../../core/createCanvas";
import Renderer from "./renderer";
import echarts from "./adaption";

type Props = {
  onReady?: Function
};

const Canvas = createCanvas(Renderer);

export default class ECCanvas extends PureComponent<Props> {
  canvas;

  createTouchEvents(events: Array<String>) {
    return ({ nativeEvent }) => {
      let x = 0;
      let y = 0;
      const touches = nativeEvent.touches;
      if (touches && touches.length > 0) {
        x = touches[0].locationX;
        y = touches[0].locationY;
      }
      events.forEach(event => {
        this.canvas.dispatchEvent(event, x, y);
      });
    };
  }

  onTouchStart = this.createTouchEvents(["mousedown", "mousemove"]);
  onTouchMove = this.createTouchEvents(["mousemove"]);
  onTouchEnd = this.createTouchEvents(["mouseup", "click"]);

  panHandlers = createPanResponder({
    start: this.onTouchStart,
    move: this.onTouchMove,
    end: this.onTouchEnd
  });

  onReady = canvas => {
    this.canvas = canvas;
    echarts.setCanvasCreator(() => {
      return this.canvas;
    });
    this.props.onReady && this.props.onReady(this.canvas, echarts);
  };

  render() {
    return (
      <Canvas
        {...this.props}
        {...this.panHandlers}
        onReady={this.onReady}
        ref={this.setRef}
      />
    );
  }
}
