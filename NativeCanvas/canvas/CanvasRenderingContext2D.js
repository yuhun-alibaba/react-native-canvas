// @flow

import CanvasRenderingContextProperty from "./CanvasRenderingContextProperty";

export default class CanvasRenderingContext2D extends CanvasRenderingContextProperty {
  clearRect(...args) {
    this.enqueue(
      this.createAction("clearRect", args)
    )
  }

  fillRect(...args) {
    this.enqueue(
      this.createAction("fillRect", args)
    )
  }

  strokeRect(...args) {
    this.enqueue(
      this.createAction("strokeRect", args)
    )
  }

  fillText(...args) {
    this.enqueue(
      this.createAction("fillText", args)
    )
  }

  strokeText(...args) {
    this.enqueue(
      this.createAction("strokeText", args)
    )
  }

  measureText(...args) {
    this.enqueue(
      this.createAction("measureText", args)
    )
  }

  beginPath(...args) {
    this.enqueue(
      this.createAction("beginPath", args)
    )
  }

  closePath(...args) {
    this.enqueue(
      this.createAction("closePath", args)
    )
  }

  moveTo(...args) {
    this.enqueue(
      this.createAction("moveTo", args)
    )
  }

  lineTo(...args) {
    this.enqueue(
      this.createAction("lineTo", args)
    )
  }

  bezierCurveTo(...args) {
    this.enqueue(
      this.createAction("bezierCurveTo", args)
    )
  }

  quadraticCurveTo(...args) {
    this.enqueue(
      this.createAction("quadraticCurveTo", args)
    )
  }

  arc(...args) {
    this.enqueue(
      this.createAction("arc", args)
    )
  }

  arcTo(...args) {
    this.enqueue(
      this.createAction("arcTo", args)
    )
  }

  rect(...args) {
    this.enqueue(
      this.createAction("rect", args)
    )
  }

  fill() {
    this.enqueue(
      this.createAction("fill", [])
    )
  }

  stroke() {
    this.enqueue(
      this.createAction("stroke", [])
    )
  }

  rotate(...args) {
    this.enqueue(
      this.createAction("rotate", args)
    )
  }

  scale(...args) {
    this.enqueue(
      this.createAction("scale", args)
    )
  }

  translate(...args) {
    this.enqueue(
      this.createAction("translate", args)
    )
  }

  transform(...args) {
    this.enqueue(
      this.createAction("transform", args)
    )
  }

  resetTransform(...args) {
    this.enqueue(
      this.createAction("resetTransform", args)
    )
  }

  drawImage(...args) {
    this.enqueue(
      this.createAction("drawImage", args)
    )
  }

  createImageData(...args) {
    this.enqueue(
      this.createAction("createImageData", args)
    )
  }

  getImageData(...args) {
    this.enqueue(
      this.createAction("getImageData", args)
    )
  }

  putImageData(...args) {
    this.enqueue(
      this.createAction("putImageData", args)
    )
  }

  save(...args) {
    this.enqueue(
      this.createAction("save", args)
    )
  }

  restore(...args) {
    this.enqueue(
      this.createAction("restore", args)
    )
  }

}