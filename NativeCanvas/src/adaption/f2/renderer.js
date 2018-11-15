// @flow

import EventEmitter from "EventEmitter";

export default class Renderer extends EventEmitter {
  _context;

  style = {}; // mock

  constructor(context) {
    super();
    this._context = context;
  }

  getContext() {
    return this._context;
  }
}
