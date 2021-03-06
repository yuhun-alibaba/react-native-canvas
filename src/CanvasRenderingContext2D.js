// @flow
// CanvasRenderingContext2D
// API ref: https://developer.mozilla.org/zh-CN/docs/Web/API/CanvasRenderingContext2D

import CanvasNativeAPI from "./CanvasNativeAPI";
import CanvasRenderingAction from "./CanvasRenderingAction";
import {
  extractColor,
  extractFont,
  extractAlignment,
  measureText
} from "./utils";

export default class CanvasRenderingContext2D extends CanvasRenderingAction {
  _canvas; // canvas view component instance
  _lineDash;
  _fontSize = 10;

  constructor(canvas) {
    super();
    this._canvas = canvas;
  }

  draw() {
    this.beforeDrawing();
    this.drawing();
    this.afterDrawing();
  }

  drawing() {
    if (__DEV__) {
      this._canvas.asyncUpdate(this.actions);
    } else {
      CanvasNativeAPI.drawSync(this._canvas.nativeID, this.actions);
    }
  }

  release() {
    CanvasNativeAPI.release(this._canvas.nativeID);
  }

  beforeDrawing() {
    super.beforeDrawing();
  }

  afterDrawing() {
    super.afterDrawing();
    this.resetProperties();
  }

  resetProperties() {
    this._lineDash = null;
    this._fontSize = 10;
  }

  /**
   * sync methods
   */

  measureText(text) {
    this.enqueueJSAction("measureText", [text]);
    if (__DEV__) {
      return measureText(text, this._fontSize);
    } else {
      return CanvasNativeAPI.measureText(text, this._fontSize);
    }
  }

  getLineDash() {
    this.enqueueJSAction("getLineDash", []);
    return this._lineDash;
  }

  /**
   * set styles
   */

  set globalAlpha(alpha) {
    this.enqueue(this.createAction("globalAlpha", [alpha]));
  }

  set fillStyle(fillStyle) {
    this.enqueue(this.createAction("fillStyle", [extractColor(fillStyle)]));
  }

  set strokeStyle(strokeStyle) {
    this.enqueue(this.createAction("strokeStyle", [extractColor(strokeStyle)]));
  }

  set lineWidth(lineWidth) {
    this.enqueue(this.createAction("lineWidth", [lineWidth]));
  }

  set lineCap(lineCap) {
    this.enqueue(this.createAction("lineCap", [lineCap]));
  }

  set lineJoin(lineJoin) {
    this.enqueue(this.createAction("lineJoin", [lineJoin]));
  }

  set miterLimit(miterLimit) {
    this.enqueue(this.createAction("miterLimit", [miterLimit]));
  }

  set lineDashOffset(lineDashOffset) {
    this.enqueue(this.createAction("lineDashOffset", [lineDashOffset]));
  }

  set font(font) {
    this._fontSize = font["fontSize"] || this._fontSize;
    this.enqueue(this.createAction("font", [extractFont(font)]));
  }

  set textAlign(textAlign) {
    this.enqueue(this.createAction("textAlign", [extractAlignment(textAlign)]));
  }

  set textBaseline(textBaseline) {
    this.enqueue(this.createAction("textBaseline", [textBaseline]));
  }

  set shadowColor(shadowColor) {
    this.enqueue(this.createAction("shadowColor", [extractColor(shadowColor)]));
  }

  set shadowBlur(shadowBlur) {
    this.enqueue(this.createAction("shadowBlur", [shadowBlur]));
  }

  set shadowOffsetX(shadowOffsetX) {
    this.enqueue(this.createAction("shadowOffsetX", [shadowOffsetX]));
  }

  set shadowOffsetY(shadowOffsetY) {
    this.enqueue(this.createAction("shadowOffsetY", [shadowOffsetY]));
  }

  /**
   * aysnc drawing methods
   */

  setLineDash(lineDash) {
    this._lineDash = lineDash;
    this.enqueue(this.createAction("setLineDash", [lineDash]));
  }

  clearRect(...args) {
    this.enqueue(this.createAction("clearRect", args));
  }

  fillRect(...args) {
    this.enqueue(this.createAction("fillRect", args));
  }

  strokeRect(...args) {
    this.enqueue(this.createAction("strokeRect", args));
  }

  fillText(...args) {
    this.enqueue(this.createAction("fillText", args));
  }

  strokeText(...args) {
    this.enqueue(this.createAction("strokeText", args));
  }

  beginPath(...args) {
    this.enqueue(this.createAction("beginPath", args));
  }

  closePath(...args) {
    this.enqueue(this.createAction("closePath", args));
  }

  moveTo(...args) {
    this.enqueue(this.createAction("moveTo", args));
  }

  lineTo(...args) {
    this.enqueue(this.createAction("lineTo", args));
  }

  bezierCurveTo(...args) {
    this.enqueue(this.createAction("bezierCurveTo", args));
  }

  quadraticCurveTo(...args) {
    this.enqueue(this.createAction("quadraticCurveTo", args));
  }

  arc(...args) {
    this.enqueue(this.createAction("arc", args));
  }

  arcTo(...args) {
    this.enqueue(this.createAction("arcTo", args));
  }

  rect(...args) {
    this.enqueue(this.createAction("rect", args));
  }

  clip(...args) {
    this.enqueue(this.createAction("clip", args));
  }

  fill() {
    this.enqueue(this.createAction("fill", []));
  }

  stroke() {
    this.enqueue(this.createAction("stroke", []));
  }

  rotate(...args) {
    this.enqueue(this.createAction("rotate", args));
  }

  scale(...args) {
    this.enqueue(this.createAction("scale", args));
  }

  translate(...args) {
    this.enqueue(this.createAction("translate", args));
  }

  transform(...args) {
    this.enqueue(this.createAction("transform", args));
  }

  setTransform(...args) {
    this.enqueue(this.createAction("setTransform", args));
  }

  resetTransform() {
    this.enqueue(this.createAction("resetTransform", []));
  }

  drawImage(...args) {
    // this.enqueue(this.createAction("drawImage", args));
  }

  createImageData(...args) {
    // this.enqueue(this.createAction("createImageData", args));
  }

  getImageData(...args) {
    // this.enqueue(this.createAction("getImageData", args));
  }

  putImageData(...args) {
    // this.enqueue(this.createAction("putImageData", args));
  }

  save() {
    this.enqueue(this.createAction("save", []));
  }

  restore() {
    this.enqueue(this.createAction("restore", []));
  }
}
