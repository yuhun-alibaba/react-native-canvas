// @flow

import CanvasRenderingContextStyle from "./CanvasRenderingContextStyle";

export default class CanvasRenderingContext2D extends CanvasRenderingContextStyle {
  // canvas component instance
  _canvas;
  _lineDash;

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
    super.resetProperties();
    this._lineDash = null;
  }

  /**
   * sync methods
   */

  measureText(text) {
    this.logAction("measureText", [text]);

    return {
      width: text.length * 2,
      height: 5
    };
  }

  getLineDash() {
    return this._lineDash;
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
