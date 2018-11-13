// @flow

import CanvasRenderingAction from "./CanvasRenderingAction";

export default class CanvasRenderingContextProperty extends CanvasRenderingAction {
  // fillStrokeStyles
  _fillStyle;
  _strokeStyle;
  // lineStyles
  _lineWidth;
  _lineCap;
  _lineJoin;
  // textStyles
  _font;
  _textAlign;
  _textBaseline;
  _direction;
  // shadow
  _shadowColor;
  _shadowBlur;

  set fillStyle(fillStyle) {
    if (_fillStyle === fillStyle) return;
    _fillStyle = fillStyle;

    this.enqueue(
      this.createAction("fillStyle", [fillStyle])
    )
  }

  set strokeStyle(strokeStyle) {
    if (_strokeStyle === strokeStyle) return;
    _strokeStyle = strokeStyle;

    this.enqueue(
      this.createAction("strokeStyle", [fillStyle])
    )
  }

  set lineWidth(lineWidth) {
    if (_lineWidth === lineWidth) return;
    _lineWidth = lineWidth;

    this.enqueue(
      this.createAction("lineWidth", [lineWidth])
    )
  }

  set lineCap(lineCap) {
    if (_lineJoin === lineJoin) return;
    _lineCap = lineCap;

    this.enqueue(
      this.createAction("lineCap", [lineCap])
    )
  }

  set lineJoin(lineJoin) {
    if (_lineJoin === lineJoin) return;
    _lineJoin = lineJoin;

    this.enqueue(
      this.createAction("lineJoin", [lineJoin])
    )
  }

  set font(font) {
    if (_font === _font) return;
    _font = font;

    this.enqueue(
      this.createAction("font", [font])
    )
  }

  set textAlign(textAlign) {
    if (_textAlign === textAlign) return;
    _textAlign = textAlign;

    this.enqueue(
      this.createAction("textAlign", [textAlign])
    )
  }

  set textBaseline(textBaseline) {
    if (_textBaseline === textBaseline) return;
    _textBaseline = textBaseline;

    this.enqueue(
      this.createAction("textBaseline", [textBaseline])
    )
  }

  set direction(direction) {
    if (_direction === direction) return;
    _direction = direction;

    this.enqueue(
      this.createAction("direction", [direction])
    )
  }

  set shadowColor(shadowColor) {
    if (_shadowColor === shadowColor) return;
    _shadowColor = shadowColor;

    this.enqueue(
      this.createAction("shadowColor", [shadowColor])
    )
  }

  set shadowBlur(direction) {
    if (_shadowBlur === shadowBlur) return;
    _shadowBlur = shadowBlur;

    this.enqueue(
      this.createAction("shadowBlur", [shadowBlur])
    )
  }

}