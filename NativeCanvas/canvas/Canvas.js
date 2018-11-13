// @flow

import { requireNativeComponent, View } from 'react-native';
import React,{ PureComponent } from "react"
import PropTypes from 'prop-types';

class Canvas extends PureComponent {
  static propTypes = {
    actions: PropTypes.arrayOf(PropTypes.object),
    ...View.propTypes
  };

  render() {
    return <CanvasView {...this.props}/>
  }
}

const CanvasView = requireNativeComponent('CanvasView', Canvas);

export default Canvas;