// @flow
// f2 canvas

import F2 from "@antv/f2";

F2.Util.addEventListener = function(source, type, listener) {
  source.addListener(type, listener);
};

F2.Util.removeEventListener = function(source, type, listener) {
  source.removeListener(type, listener);
};

F2.Util.createEvent = function(event, chart) {
  let x = 0;
  let y = 0;
  const touches = event.touches;
  if (touches && touches.length > 0) {
    x = touches[0].locationX;
    y = touches[0].locationY;
  }
  return {
    type: event.type,
    chart,
    x,
    y
  };
};

export default F2;
