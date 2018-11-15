// @flow

import CanvasRenderingAction from "./CanvasRenderingAction";
import { extractColor, extractFont, extractAlignment } from "./utils";

export default class CanvasRenderingContextStyle extends CanvasRenderingAction {
  // fill stroke style
  _fillStyle;
  _strokeStyle;
  // line style
  _lineWidth;
  _lineCap;
  _lineJoin;
  _lineDashOffset;
  // text style
  _font;
  _fontSize = 10;
  _textAlign;
  _textBaseline;
  _direction;
  // shadow style
  _shadowColor;
  _shadowBlur;

  resetProperties() {
    this._fillStyle = null;
    this._strokeStyle = null;
    this._lineWidth = null;
    this._lineCap = null;
    this._lineJoin = null;
    this._lineDashOffset = null;
    this._font = null;
    this._fontSize = 10;
    this._textAlgin = null;
    this._textBaseline = null;
    this._direction = null;
    this._shadowColor = null;
    this._shadowBlur = null;
  }

  set fillStyle(fillStyle) {
    if (this._fillStyle === fillStyle) return;
    this._fillStyle = fillStyle;

    this.enqueue(this.createAction("fillStyle", [extractColor(fillStyle)]));
  }

  set strokeStyle(strokeStyle) {
    if (this._strokeStyle === strokeStyle) return;
    this._strokeStyle = strokeStyle;

    this.enqueue(this.createAction("strokeStyle", [extractColor(strokeStyle)]));
  }

  set lineWidth(lineWidth) {
    if (this._lineWidth === lineWidth) return;
    this._lineWidth = lineWidth;

    this.enqueue(this.createAction("lineWidth", [lineWidth]));
  }

  set lineCap(lineCap) {
    if (this._lineCap === lineCap) return;
    this._lineCap = lineCap;

    this.enqueue(this.createAction("lineCap", [lineCap]));
  }

  set lineJoin(lineJoin) {
    if (this._lineJoin === lineJoin) return;
    this._lineJoin = lineJoin;

    this.enqueue(this.createAction("lineJoin", [lineJoin]));
  }

  set lineDashOffset(lineDashOffset) {
    if (this._lineDashOffset === lineDashOffset) return;
    this._lineDashOffset = lineDashOffset;

    this.enqueue(this.createAction("lineDashOffset", [lineDashOffset]));
  }

  set font(font) {
    if (this._font === font) return;

    this._fontSize = font["fontSize"] || this._fontSize;
    this._font = font;

    this.enqueue(this.createAction("font", [extractFont(font)]));
  }

  set textAlgin(textAlgin) {
    if (this._textAlgin === textAlgin) return;
    this._textAlgin = textAlgin;

    this.enqueue(this.createAction("textAlgin", [extractAlignment(textAlgin)]));
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

  set shadowBlur(shadowBlur) {
    if (this._shadowBlur === shadowBlur) return;
    this._shadowBlur = shadowBlur;

    this.enqueue(this.createAction("shadowBlur", [shadowBlur]));
  }
}
