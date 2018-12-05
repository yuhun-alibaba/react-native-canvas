// @flow

export default class Renderer {
  _context;
  _chart;
  event = {};

  constructor(context) {
    this._context = context;
    this._initEvent();
  }

  getContext() {
    return this._context;
  }

  setChart(chart) {
    this._chart = chart;
  }

  attachEvent() {
    // noop
  }

  detachEvent() {
    // noop
  }

  dispatchEvent(name, x, y) {
    this._chart._zr.handler.dispatch(name, {
      preventDefault: () => {},
      stopPropagation: () => {},
      zrX: x,
      zrY: y
    });
  }

  _initEvent() {
    const eventNames = [
      {
        rnName: "touchStart",
        ecName: "mousedown"
      },
      {
        rnName: "touchMove",
        ecName: "mousemove"
      },
      {
        rnName: "touchEnd",
        ecName: "mouseup"
      },
      {
        rnName: "touchEnd",
        ecName: "click"
      }
    ];

    eventNames.forEach(name => {
      this.event[name.rnName] = event => {
        let x = 0;
        let y = 0;
        const touches = event.touches;
        if (touches && touches.length > 0) {
          x = touches[0].locationX;
          y = touches[0].locationY;
        }
        this.dispatchEvent(name.ecName, x, y);
      };
    });
  }
}
