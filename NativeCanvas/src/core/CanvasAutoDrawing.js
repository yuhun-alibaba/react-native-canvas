// @flow
// Auto drawing when component did mount

import React, { PureComponent } from "react";
import Canvas from "./Canvas";

type Props = {
  style?: any,
  draw: Function
};

export default class CanvasAutoDrawing extends PureComponent<Props> {
  canvas;

  setRef = ref => {
    this.canvas = ref.canvas;
  };

  componentDidMount() {
    if (this.canvas) {
      this.props.draw && this.props.draw(this.canvas);
    }
  }

  render() {
    return <Canvas {...this.props} ref={this.setRef} />;
  }
}
