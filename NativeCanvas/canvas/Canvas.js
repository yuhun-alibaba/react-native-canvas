// @flow

import { requireNativeComponent, View } from "react-native";
import React, { PureComponent } from "react";
import PropTypes from "prop-types";

import CanvasRenderingContext2D from "./CanvasRenderingContext2D";
import type { Actions } from "./flowType";

type Props = {
  actions: Actions
};

type State = {
  actions: Actions
};

class Canvas extends PureComponent<Props, State> {
  static propTypes = {
    actions: PropTypes.arrayOf(PropTypes.object),
    ...View.propTypes
  };

  state = {
    actions: this.props.actions || []
  };

  update(actions: Actions) {
    this.setState({ actions });
  }

  getContext() {
    return new CanvasRenderingContext2D(this);
  }

  componentWillReceiveProps(nextProps: Props) {
    if (this.props.actions !== nextProps.actions) {
      this.update(nextProps.actions);
    }
  }

  render() {
    return <CanvasView {...this.props} actions={this.state.actions} />;
  }
}

const CanvasView = requireNativeComponent("CanvasView", Canvas);

export default Canvas;
