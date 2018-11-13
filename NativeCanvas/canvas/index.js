// @flow

import CanvasRenderingContext2D from "./CanvasRenderingContext2D"

export { default as Canvas } from "./Canvas";

export function getContext(){
  return new CanvasRenderingContext2D();
}