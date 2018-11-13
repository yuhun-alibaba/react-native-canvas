// @flow

import CanvasRenderingAction from "./CanvasRenderingAction";

export default class CanvasRenderingContextStyle extends CanvasRenderingAction {
  // fill stroke style
  _fillStyle;
  _strokeStyle;
  // line style
  _lineWidth;
  _lineCap;
  _lineJoin;
  // text style
  _font;
  _textAlign;
  _textBaseline;
  _direction;
  // shadow style
  _shadowColor;
  _shadowBlur;

  set fillStyle(fillStyle) {
    if (this._fillStyle === fillStyle) return;
    this._fillStyle = fillStyle;

    this.enqueue(this.createAction("fillStyle", [fillStyle]));
  }

  set strokeStyle(strokeStyle) {
    if (this._strokeStyle === strokeStyle) return;
    this._strokeStyle = strokeStyle;

    this.enqueue(this.createAction("strokeStyle", [fillStyle]));
  }

  set lineWidth(lineWidth) {
    if (this._lineWidth === lineWidth) return;
    this._lineWidth = lineWidth;

    this.enqueue(this.createAction("lineWidth", [lineWidth]));
  }

  set lineCap(lineCap) {
    if (this._lineJoin === lineJoin) return;
    this._lineCap = lineCap;

    this.enqueue(this.createAction("lineCap", [lineCap]));
  }

  set lineJoin(lineJoin) {
    if (this._lineJoin === lineJoin) return;
    this._lineJoin = lineJoin;

    this.enqueue(this.createAction("lineJoin", [lineJoin]));
  }

  set font(font) {
    if (this._font === font) return;
    this._font = font;

    this.enqueue(this.createAction("font", [font]));
  }

  set textAlign(textAlign) {
    if (this._textAlign === textAlign) return;
    this._textAlign = textAlign;

    this.enqueue(this.createAction("textAlign", [textAlign]));
  }

  set textBaseline(textBaseline) {
    if (this._textBaseline === textBaseline) return;
    this._textBaseline = textBaseline;

    this.enqueue(this.createAction("textBaseline", [textBaseline]));
  }

  set direction(direction) {
    if (this._direction === direction) return;
    this._direction = direction;

    this.enqueue(this.createAction("direction", [direction]));
  }

  set shadowColor(shadowColor) {
    if (this._shadowColor === shadowColor) return;
    this._shadowColor = shadowColor;

    this.enqueue(this.createAction("shadowColor", [shadowColor]));
  }

  set shadowBlur(direction) {
    if (this._shadowBlur === shadowBlur) return;
    this._shadowBlur = shadowBlur;

    this.enqueue(this.createAction("shadowBlur", [shadowBlur]));
  }
}
