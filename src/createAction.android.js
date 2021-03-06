// @flow

import type { ActionMethod, ActionArguments } from "./types";

const methodMap = {
  // styles
  globalAlpha: "setGlobalAlpha:float",
  fillStyle: "setFillStyle:float[]",
  strokeStyle: "setStrokeStyle:float[]",
  lineWidth: "setLineWidth:float",
  lineCap: "setLineCap:String",
  lineJoin: "setLineJoin:String",
  miterLimit: "setMiterLimit:float",
  lineDashOffset: "setLineDashOffset:float",
  font: "setFont:HashMap",
  textAlign: "setTextAlign:String",
  textBaseline: "setTextBaseline:String",
  shadowColor: "setShadowColor:float[]",
  shadowBlur: "setShadowBlur:float",
  shadowOffsetX: "setShadowOffsetX:float",
  shadowOffsetY: "setShadowOffsetY:float",
  // methods
  setLineDash: "setLineDash:float[]",
  clearRect: "clearRect:float:float:float:float",
  fillRect: "fillRect:float:float:float:float",
  strokeRect: "strokeRect:float:float:float:float",
  fillText: "fillText:String:float:float",
  strokeText: "strokeText:String:float:float",
  measureText: "measureText:String",
  beginPath: "beginPath",
  closePath: "closePath",
  moveTo: "moveTo:float:float",
  lineTo: "lineTo:float:float",
  bezierCurveTo: "bezierCurveTo:float:float:float:float:float:float",
  quadraticCurveTo: "quadraticCurveTo:float:float:float:float",
  arc: "arc:float:float:float:float:float",
  arcTo: "arcTo:float:float:float:float:float",
  rect: "rect:float:float:float:float",
  clip: "clip",
  fill: "fill",
  stroke: "stroke",
  rotate: "rotate:float",
  scale: "scale:float:float",
  translate: "translate:float:float",
  transform: "transform:float:float:float:float:float:float",
  setTransform: "setTransform:float:float:float:float:float:float",
  resetTransform: "resetTransform",
  drawImage: "drawImage", // not implement
  createImageData: "createImageData", // not implement
  getImageData: "getImageData", // not implement
  putImageData: "putImageData", // not implement
  save: "save",
  restore: "restore"
};

export default function createAction(
  method: ActionMethod,
  args: ActionArguments = []
) {
  const convertedMethod = methodMap[method];

  if (method === "clip" && args.length) {
    return {
      method: `${convertedMethod}:String`,
      arguments: args
    };
  }

  if (method === "arc" && args.length === 6) {
    args[5] = !!args[5];
    return {
      method: `${convertedMethod}:boolean`,
      arguments: args
    };
  }

  return {
    method: convertedMethod,
    arguments: args
  };
}
