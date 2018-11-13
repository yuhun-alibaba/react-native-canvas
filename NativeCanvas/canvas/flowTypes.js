// @flow

export type ActionArguments = Array<any>;

export type ActionMethod = string;

export type Action = {
  method: string,
  arguments: Array<any>
};

export type Actions = Array<Action>;
