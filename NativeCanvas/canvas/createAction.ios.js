// @flow

import type {
  ActionMethod,
  ActionArguments
} from "./flowTypes";

const methodMap = {
  fillStyle: 'fillStyle:',
  strokeStyle: 'strokeStyle:',
  lineWidth: 'lineWidth:',
  lineCap: 'lineCap:',
  lineJoin: 'lineJoin:',
  font: 'font:',
  textAlgin: 'textAlgin:',
  textBaseline: 'textBaseline:',
  direction: 'direction:',
  shadowColor: 'shadowColor:',
  shadowBlur: 'shadowBlur:',
  clearRect: 'clearRect:',
  fillRect: 'fillRect:y:width:height:',
  strokeRect: 'strokeRect:',
  fillText: 'fillText:',
  strokeText: 'strokeText:',
  measureText: 'measureText:',
  beginPath: 'beginPath:',
  closePath: 'closePath:',
  moveTo: 'moveTo:',
  lineTo: 'lineTo:',
  bezierCurveTo: 'bezierCurveTo:',
  quadraticCurveTo: 'quadraticCurveTo:',
  arc: 'arc:y:radius:startAangle:endAngle:anticlockwise:',
  arcTo: 'arcTo:',
  rect: 'rect:',
  fill: 'fill',
  stroke: 'stroke',
  rotate: 'rotate:',
  scale: 'scale:',
  translate: 'translate:',
  transform: 'transform:',
  resetTransform: 'resetTransform:',
  drawImage: 'drawImage:',
  createImageData: 'createImageData:',
  getImageData: 'getImageData:',
  putImageData: 'putImageData:',
  save: 'save',
  restore: 'restore'
}

export default function createAction(method: ActionMethod, args: ActionArguments) {
  console.log("method", method, "arguments", args);
  return {
    method: methodMap[method],
    arguments: args
  }
}