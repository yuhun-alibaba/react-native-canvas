// @flow

import React, { Component } from "react";
import { View, Dimensions } from "react-native";

import { CanvasAutoDrawing } from "../../src/core";
import { F2Canvas } from "../../src/adaption";

const windowWidth = Dimensions.get("window").width;
const canvasWidth = windowWidth;
const canvasHeight = 300;

export default class Bar extends Component<any> {
  draw = (canvas: any, F2) => {
    var Global = F2.Global;
    var data = [
      { country: "巴西", population: 18203 },
      { country: "印尼", population: 23489 },
      { country: "美国", population: 29034 },
      { country: "印度", population: 104970 },
      { country: "中国", population: 131744 }
    ];
    var chart = new F2.Chart({
      el: canvas,
      width: canvasWidth,
      height: canvasHeight
    });

    chart.source(data, {
      population: {
        tickCount: 5
      }
    });
    chart.coord({
      transposed: true
    });
    chart.axis("country", {
      line: Global._defaultAxis.line,
      grid: null
    });
    chart.axis("population", {
      line: null,
      grid: Global._defaultAxis.grid,
      label: function(text, index, total) {
        var textCfg = {};
        if (index === 0) {
          textCfg.textAlign = "left";
        } else if (index === total - 1) {
          textCfg.textAlign = "right";
        }
        return textCfg;
      }
    });
    chart.interval().position("country*population");
    chart.render();
  };

  render() {
    return (
      <F2Canvas
        style={{
          width: canvasWidth,
          height: canvasHeight,
          backgroundColor: "#ffffff"
        }}
        draw={this.draw}
      />
    );
  }
}
