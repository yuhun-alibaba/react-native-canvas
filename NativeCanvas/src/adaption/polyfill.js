// @flow

// 模拟微信场景
global.wx = {
  getSystemInfoSync: function() {}
};
// 防止 echarts 在 chrome debug 环境注册事件
global.window.addEventListener = undefined;
