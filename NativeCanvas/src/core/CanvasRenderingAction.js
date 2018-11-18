// @flow

import type { ActionMethod, ActionArguments, Action, Actions } from "./types";
import createAction from "./createAction";

export default class CanvasRenderingAction {
  _actions = [];
  _jsActions = []; // for debug

  get actions() {
    return this._actions;
  }

  beforeDrawing() {
    this.logJSActions();
  }

  afterDrawing() {
    this.clearActions();
  }

  enqueue(action: Action) {
    this._actions.push(action);
  }

  createAction(method: ActionMethod, args: ActionArguments) {
    this.enqueueJSAction(method, args);
    return createAction(method, args);
  }

  logJSActions() {
    this._jsActions.forEach(this.logAction);
  }

  logAction([method, ...args]) {
    console.log(`${method}(${args})`);
  }

  enqueueJSAction(method, args) {
    this._jsActions.push([method, args]);
  }

  clearActions() {
    this._actions = [];
    this._jsActions = [];
  }
}
