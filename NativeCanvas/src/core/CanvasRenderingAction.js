// @flow

import type { ActionMethod, ActionArguments, Action, Actions } from "./types";
import createAction from "./createAction";

export default class CanvasRenderingAction {
  _actions = [];

  get actions() {
    return this._actions;
  }

  logAction(method, args) {
    console.log("method", method, "arguments", args);
  }

  resetActions() {
    this._actions = [];
  }

  enqueue(action: Action) {
    this._actions.push(action);
  }

  createAction(method: ActionMethod, args: ActionArguments) {
    this.logAction(method, args);
    return createAction(method, args);
  }
}
