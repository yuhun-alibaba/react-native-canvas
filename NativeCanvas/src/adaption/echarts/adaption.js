// @flow

// mock 微信环境
window.wx = {
  getSystemInfoSync: () => {}
};

import echarts from "echarts";

echarts.zrender.env = {
  ...echarts.zrender.env,
  wxa: true,
  canvasSupported: true,
  svgSupported: false,
  touchEventsSupported: true,
  domSupported: false
};

export default echarts;
