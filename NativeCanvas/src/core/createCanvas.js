// @flow
// create Canvas by Renderer

import React, { PureComponent } from "react";

import CanvasRenderingContext2D from "./CanvasRenderingContext2D";
import type { Actions, RendererType } from "./types";
import { CanvasPropTypes } from "./types";
import CanvasNative from "./CanvasNative";

type Props = {
  actions: Actions
};

type State = {
  actions: Actions
};

export default function createCanvas(Renderer: RendererType) {
  return class Canvas extends PureComponent<Props, State> {
    static propTypes = CanvasPropTypes;

    canvas = new Renderer(new CanvasRenderingContext2D(this));

    state = {
      actions: this.props.actions || []
    };

    update(actions: Actions) {
      console.log("draw ~~~~~~~~~~~~");
      this.setState({ actions });
    }

    componentWillReceiveProps(nextProps: Props) {
      if (this.props.actions !== nextProps.actions) {
        this.update(nextProps.actions);
      }
    }

    render() {
      return <CanvasNative {...this.props} actions={this.state.actions} />;
    }
  };
}
