// @flow
// create Canvas by Renderer

import React, { PureComponent } from "react";

import CanvasRenderingContext2D from "./CanvasRenderingContext2D";
import type { Actions, RendererType } from "./types";
import { CanvasPropTypes } from "./types";
import CanvasNativeView from "./CanvasNativeView";

type Props = {
  nativeID?: string
};

let canvasIndex = 0;
function getNativeID() {
  return `canvas:${canvasIndex++}`;
}

export default function createCanvas(Renderer: RendererType) {
  return class Canvas extends PureComponent<Props> {
    static propTypes = CanvasPropTypes;

    nativeID = this.props.nativeID || getNativeID();
    renderingContext = new CanvasRenderingContext2D(this);
    canvas = new Renderer(this.renderingContext);
    ref;

    setRef = ref => {
      this.ref = ref;
    };

    asyncUpdate(actions: Actions) {
      this.ref.setNativeProps({ actions });
    }

    componentWillReceiveProps(nextProps: Props) {
      if (this.props.actions !== nextProps.actions) {
        this.asyncUpdate(nextProps.actions);
      }
    }

    componentWillUnmount() {
      // @todo should auto realse
      this.renderingContext.release();
    }

    render() {
      return (
        <CanvasNativeView
          {...this.props}
          nativeID={this.nativeID}
          ref={this.setRef}
        />
      );
    }
  };
}
