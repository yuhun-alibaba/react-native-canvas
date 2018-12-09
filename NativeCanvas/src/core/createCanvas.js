// @flow
// create Canvas by Renderer

import React, { PureComponent } from "react";
import { Platform } from "react-native";

import CanvasRenderingContext2D from "./CanvasRenderingContext2D";
import type { Actions, RendererType } from "./types";
import { CanvasPropTypes } from "./types";
import CanvasNativeView from "./CanvasNativeView";

type Props = {
  nativeID?: string,
  onReady?: Function
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

    ready = false;

    setRef = ref => {
      this.ref = ref;
    };

    onReady = () => {
      this.ready = true;
      this.props.onReady && this.props.onReady(this.canvas);
    };

    componentDidMount() {
      if (Platform.OS === "ios") {
        this.onReady();
      }
    }

    asyncUpdate(actions: Actions) {
      this.ref.setNativeProps({ actions });
    }

    componentWillUnmount() {
      // @todo should auto realse
      this.renderingContext.release();
    }

    render() {
      return (
        <CanvasNativeView
          {...this.props}
          onReady={this.onReady}
          nativeID={this.nativeID}
          ref={this.setRef}
        />
      );
    }
  };
}
