// @flow
// CanvasRenderingContext2D
// API ref: https://developer.mozilla.org/zh-CN/docs/Web/API/CanvasRenderingContext2D

import CanvasRenderingAction from "./CanvasRenderingAction";
import {
  extractColor,
  extractFont,
  extractAlignment,
  extractTextBaseline,
  measureText
} from "./utils";

export default class CanvasRenderingContext2D extends CanvasRenderingAction {
  _canvas; // canvas component instance
  _lineDash;
  _fontSize = 10;

  get canvas() {
    return _canvas;
  }

  constructor(canvas) {
    super();
    this._canvas = canvas;
  }

  draw() {
    if (this._canvas.update) {
      this._canvas.update(this.actions);
      this.resetActions();
      this.resetProperties();
    }
  }

  resetProperties() {
    this._lineDash = null;
    this._fontSize = 10;
  }

  /**
   * sync methods
   */

  measureText(text) {
    this.logAction("measureText", [text]);

    return measureText(text, this._fontSize);
  }

  getLineDash() {
    return this._lineDash;
  }

  /**
   * set styles
   */

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

  set lineDashOffset(lineDashOffset) {
    this.enqueue(this.createAction("lineDashOffset", [lineDashOffset]));
  }

  set font(font) {
    this._fontSize = font["fontSize"] || this._fontSize;
    this.enqueue(this.createAction("font", [extractFont(font)]));
  }

  set textAlgin(textAlgin) {
    this.enqueue(this.createAction("textAlgin", [extractAlignment(textAlgin)]));
  }

  set textBaseline(textBaseline) {
    this.enqueue(
      this.createAction("textBaseline", [extractTextBaseline(textBaseline)])
    );
  }

  set direction(direction) {
    this.enqueue(this.createAction("direction", [direction]));
  }

  set shadowColor(shadowColor) {
    this.enqueue(this.createAction("shadowColor", [shadowColor]));
  }

  set shadowBlur(shadowBlur) {
    this.enqueue(this.createAction("shadowBlur", [shadowBlur]));
  }

  /**
   * aysnc drawing methods
   */

  setLineDash(lineDash) {
    if (this._lineDash === lineDash) return;
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

  drawImage(...args) {
    this.enqueue(this.createAction("drawImage", args));
  }

  createImageData(...args) {
    this.enqueue(this.createAction("createImageData", args));
  }

  getImageData(...args) {
    this.enqueue(this.createAction("getImageData", args));
  }

  putImageData(...args) {
    this.enqueue(this.createAction("putImageData", args));
  }

  save() {
    this.enqueue(this.createAction("save", []));
  }

  restore() {
    this.enqueue(this.createAction("restore", []));
  }
}
