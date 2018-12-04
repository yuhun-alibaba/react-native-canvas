// @flow

import echarts from "echarts";

// mock 微信环境
window.wx = {
  getSystemInfoSync: () => {}
};

echarts.env.wxa = true;
echarts.zrender.env.wxa = true;

export default echarts;
