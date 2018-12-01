package com.graphic.canvas;

import android.graphics.DashPathEffect;
import android.graphics.Paint;

/**
 * Created by sam on 2018/12/1.
 */

public class CanvasDrawingState {
  public int[] fillStyle;
  public int[] strokeStyle;
  public float strokeLineWidth;
  public DashPathEffect strokeLineDash;
  public Paint.Cap strokeLineCap;
  public Paint.Join strokeLineJoin;

  public float textSize;
  public int textBaseline;
  public Paint.Align textAlign;

  // primitive
  private float[] primitiveLineDash;
  private String primitiveLineCap;
  private String primitiveLineJoin;
  private String primitiveTextAlign;

  public CanvasDrawingState() {
    setUp();
  }

  public CanvasDrawingState(CanvasDrawingState preState) {
    setUp(preState);
  }

  private void setUp() {
    fillStyle = new int[]{255, 0, 0, 0};
    strokeStyle = new int[]{255, 0, 0, 0};
    strokeLineWidth = 1.f;
    strokeLineCap = Paint.Cap.BUTT;
    strokeLineJoin = Paint.Join.MITER;
    strokeLineDash = new DashPathEffect(new float[]{0.f, 0.f}, 0);
    textSize = 10.f;
    textBaseline = 0; // bottom
    textAlign = Paint.Align.LEFT;
    // primitive
    primitiveTextAlign = "left";
    primitiveLineCap = "butt";
    primitiveLineJoin ="miter";
    primitiveLineDash = new float[]{0.f, 0.f};
  }

  private void setUp(CanvasDrawingState preState) {
    fillStyle = CanvasDeepCopy.deepCopyIntList(preState.fillStyle);
    strokeStyle = CanvasDeepCopy.deepCopyIntList(preState.strokeStyle);
    strokeLineWidth = CanvasDeepCopy.deepCopyFloat(preState.strokeLineWidth);
    strokeLineCap = CanvasConvert.convertLineCap(preState.primitiveLineCap);
    strokeLineJoin = CanvasConvert.convertLineJoin(preState.primitiveLineJoin);
    strokeLineDash = CanvasConvert.convertLineDash(preState.primitiveLineDash);
    textSize = CanvasDeepCopy.deepCopyFloat(preState.textSize);
    textBaseline = CanvasDeepCopy.deepCopyInt(preState.textBaseline);
    textAlign = CanvasConvert.convertTextAlign(preState.primitiveTextAlign);
    // primitive
    primitiveTextAlign = CanvasDeepCopy.deepCopyString(preState.primitiveTextAlign);
    primitiveLineCap = CanvasDeepCopy.deepCopyString(preState.primitiveLineCap);
    primitiveLineJoin = CanvasDeepCopy.deepCopyString(preState.primitiveLineJoin);
    primitiveLineDash = CanvasDeepCopy.deepCopyFloatList(preState.primitiveLineDash);
  }

  public void setFillStyle(float[] style) {
    fillStyle = CanvasConvert.convertColor(style);
  }

  public void setStrokeStyle(float[] style) {
    strokeStyle = CanvasConvert.convertColor(style);
  }

  public void setTextSize(float size) {
    textSize = size;
  }

  public void setTextBaseline(String baseline) {
    textBaseline = CanvasConvert.convertTextBaseline(baseline);
  }

  public void setStrokeLineWidth(float lineWidth) {
    strokeLineWidth = lineWidth;
  }

  public void setStrokeLineDash(float[] lineDash) {
    primitiveLineDash = lineDash;
    strokeLineDash = CanvasConvert.convertLineDash(lineDash);
  }

  public void setStrokeLineCap(String lineCap) {
    primitiveLineCap = lineCap;
    strokeLineCap = CanvasConvert.convertLineCap(lineCap);
  }

  public void setStrokeLineJoin(String lineJoin) {
    primitiveLineJoin = lineJoin;
    strokeLineJoin = CanvasConvert.convertLineJoin(lineJoin);
  }

  public void setTextAlign(String align) {
    primitiveTextAlign = align;
    textAlign = CanvasConvert.convertTextAlign(align);
  }

}