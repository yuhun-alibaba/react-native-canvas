// @flow

import type {
  ActionMethod,
  ActionArguments,
  Action,
  Actions
} from "./flowTypes";
import createAction from "./createAction";

export default class CanvasRenderingAction {
  _actions = [];

  get actions() {
    return this._actions;
  }

  resetActions() {
    this._actions = [];
  }

  enqueue(action: Action) {
    this._actions.push(action);
  }

  createAction(method: ActionMethod, args: ActionArguments) {
    return createAction(method, args);
  }
}
