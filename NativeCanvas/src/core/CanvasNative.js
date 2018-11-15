// @flow

import { requireNativeComponent } from "react-native";
import { CanvasPropTypes } from "./types";

const NativeCanvas = requireNativeComponent("CanvasView", {
  propTypes: CanvasPropTypes
});

export default NativeCanvas;
