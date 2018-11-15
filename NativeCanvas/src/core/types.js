// @flow

import { View } from "react-native";
import PropTypes from "prop-types";

export type ActionArguments = Array<any>;

export type ActionMethod = string;

export type Action = {
  method: string,
  arguments: Array<any>
};

export type Actions = Array<Action>;

export const CanvasPropTypes = {
  actions: PropTypes.arrayOf(PropTypes.object),
  ...View.propTypes
};

declare export class RendererType {
  constructor(context: Object): void;
  getContext(type?: string): Object;
}
