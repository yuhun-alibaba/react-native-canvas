// @flow

import EventEmitter from "wolfy87-eventemitter";

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
