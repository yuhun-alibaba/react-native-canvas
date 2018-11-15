// @flow

export default class Renderer {
  _context;

  constructor(context) {
    this._context = context;
  }

  getContext() {
    return this._context;
  }
}
