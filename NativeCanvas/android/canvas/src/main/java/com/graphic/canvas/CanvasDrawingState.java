package com.graphic.canvas;

import android.graphics.DashPathEffect;
import android.graphics.Paint;

/**
 * Created by sam on 2018/12/1.
 */

public class CanvasDrawingState {
  public float globalAlpha;

  public int[] fillStyle;
  public int[] strokeStyle;

  public float strokeLineWidth;
  public DashPathEffect strokeLineDash;
  public float lineDashOffset;
  public Paint.Cap strokeLineCap;
  public Paint.Join strokeLineJoin;
  public float miterLimit;

  public float textSize;
  public int textBaseline;
  public Paint.Align textAlign;

  public int[] shadowColor;
  public float shadowBlur;
  public float shadowOffsetX;
  public float shadowOffsetY;

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
    globalAlpha = 1.f;
    fillStyle = new int[]{255, 0, 0, 0};
    strokeStyle = new int[]{255, 0, 0, 0};
    strokeLineWidth = 1.f;
    strokeLineCap = Paint.Cap.BUTT;
    strokeLineJoin = Paint.Join.MITER;
    miterLimit = 0.f;
    lineDashOffset = 0.f;
    strokeLineDash = new DashPathEffect(new float[]{0.f, 0.f}, lineDashOffset);
    textSize = 10.f;
    textBaseline = 0; // bottom
    textAlign = Paint.Align.LEFT;
    shadowColor = new int[]{0, 0, 0, 0};
    shadowBlur = 0.f;
    shadowOffsetX = 0.f;
    shadowOffsetY = 0.f;
    // primitive
    primitiveTextAlign = "left";
    primitiveLineCap = "butt";
    primitiveLineJoin = "miter";
    primitiveLineDash = new float[]{0.f, 0.f};
  }

  private void setUp(CanvasDrawingState preState) {
    globalAlpha = CanvasDeepCopy.deepCopyFloat(preState.globalAlpha);
    fillStyle = CanvasDeepCopy.deepCopyIntList(preState.fillStyle);
    strokeStyle = CanvasDeepCopy.deepCopyIntList(preState.strokeStyle);
    strokeLineWidth = CanvasDeepCopy.deepCopyFloat(preState.strokeLineWidth);
    strokeLineCap = CanvasConvert.convertLineCap(preState.primitiveLineCap);
    strokeLineJoin = CanvasConvert.convertLineJoin(preState.primitiveLineJoin);
    miterLimit = CanvasDeepCopy.deepCopyFloat(preState.miterLimit);
    lineDashOffset = CanvasDeepCopy.deepCopyFloat(preState.lineDashOffset);
    strokeLineDash = CanvasConvert.convertLineDash(preState.primitiveLineDash, lineDashOffset);
    textSize = CanvasDeepCopy.deepCopyFloat(preState.textSize);
    textBaseline = CanvasDeepCopy.deepCopyInt(preState.textBaseline);
    textAlign = CanvasConvert.convertTextAlign(preState.primitiveTextAlign);
    shadowColor = CanvasDeepCopy.deepCopyIntList(preState.shadowColor);
    shadowBlur = CanvasDeepCopy.deepCopyFloat(preState.shadowBlur);
    shadowOffsetX = CanvasDeepCopy.deepCopyFloat(preState.shadowOffsetX);
    shadowOffsetY = CanvasDeepCopy.deepCopyFloat(preState.shadowOffsetY);
    // primitive
    primitiveTextAlign = CanvasDeepCopy.deepCopyString(preState.primitiveTextAlign);
    primitiveLineCap = CanvasDeepCopy.deepCopyString(preState.primitiveLineCap);
    primitiveLineJoin = CanvasDeepCopy.deepCopyString(preState.primitiveLineJoin);
    primitiveLineDash = CanvasDeepCopy.deepCopyFloatList(preState.primitiveLineDash);
  }

  public void setGlobalAlpha(float alpha) {
    globalAlpha = alpha;
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
    strokeLineDash = CanvasConvert.convertLineDash(lineDash, lineDashOffset);
  }

  public void setStrokeLineCap(String lineCap) {
    primitiveLineCap = lineCap;
    strokeLineCap = CanvasConvert.convertLineCap(lineCap);
  }

  public void setStrokeLineJoin(String lineJoin) {
    primitiveLineJoin = lineJoin;
    strokeLineJoin = CanvasConvert.convertLineJoin(lineJoin);
  }

  public void setLineDashOffset(float dashOffset) {
    lineDashOffset = dashOffset;
    strokeLineDash = CanvasConvert.convertLineDash(primitiveLineDash, lineDashOffset);
  }

  public void setMiterLimit(float limit) {
    miterLimit = limit;
  }

  public void setTextAlign(String align) {
    primitiveTextAlign = align;
    textAlign = CanvasConvert.convertTextAlign(align);
  }

  public void setShadowColor(float[] color) {
    shadowColor = CanvasConvert.convertColor(color);
  }

  public void setShadowBlur(float blur) {
    shadowBlur = blur;
  }

  public void setShadowOffsetX(float offsetX) {
    shadowOffsetX = offsetX;
  }

  public void setShadowOffsetY(float offsetY) {
    shadowOffsetY = offsetY;
  }

}
