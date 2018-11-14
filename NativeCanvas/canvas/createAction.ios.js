// @flow

import type { ActionMethod, ActionArguments } from "./flowTypes";

const methodMap = {
  // styles
  fillStyle: "setFillStyle:",
  strokeStyle: "setStrokeStyle:",
  lineWidth: "setLineWidth:",
  lineCap: "setLineCap:",
  lineJoin: "setLineJoin:",
  lineDashOffset: "setLineDashOffset:",
  font: "setFont:",
  textAlgin: "setTextAlgin:",
  textBaseline: "setTextBaseline:",
  direction: "setDirection:",
  shadowColor: "setShadowColor:",
  shadowBlur: "setShadowBlur:",
  // methods
  setLineDash: "setLineDash:",
  clearRect: "clearRect:y:width:height:",
  fillRect: "fillRect:y:width:height:",
  strokeRect: "strokeRect:y:width:height:",
  fillText: "fillText:x:y:",
  strokeText: "strokeText:x:y:",
  measureText: "measureText:",
  beginPath: "beginPath",
  closePath: "closePath",
  moveTo: "moveTo:y:",
  lineTo: "lineTo:y:",
  bezierCurveTo: "bezierCurveTo:cp1y:cp2x:cp2y:x:y:",
  quadraticCurveTo: "quadraticCurveTo:cpy:x:y:",
  arc: "arc:y:radius:startAangle:endAngle:anticlockwise:",
  arcTo: "arcTo:y1:x2:y2:radius:",
  rect: "rect:y:width:height:",
  clip: "clip",
  fill: "fill",
  stroke: "stroke",
  rotate: "rotate:",
  scale: "scale:y:",
  translate: "translate:y:",
  transform: "transform:b:c:d:e:f:",
  setTransform: "setTransform:b:c:d:e:f:",
  drawImage: "drawImage:",
  createImageData: "createImageData:",
  getImageData: "getImageData:",
  putImageData: "putImageData:",
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
      method: `${convertedMethod}:`,
      arguments: args
    };
  }

  return {
    method: convertedMethod,
    arguments: args
  };
}
