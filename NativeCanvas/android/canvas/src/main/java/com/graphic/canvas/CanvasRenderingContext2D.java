package com.graphic.canvas;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by sam on 2018/11/21.
 * <p>
 * API ref:
 * https://developer.mozilla.org/zh-CN/docs/Web/API/CanvasRenderingContext2D
 */

public class CanvasRenderingContext2D {
  private final Paint paint = new Paint();
  private final Path path = new Path();
  private final Matrix matrix = new Matrix();
  private final CanvasDrawingStateManager stateManager = new CanvasDrawingStateManager();

  private Canvas canvas;
  private CanvasDrawingState currentState;
  private float scale;
  private float[] lastPoint; // tracking point

  public CanvasRenderingContext2D() {
  }

  public void setCanvas(Canvas canvasInstance) {
    canvas = canvasInstance;
    stateManager.reset();
    setUpCurrentState();
    resetLastPoint();
    path.reset();
    resetPaint();
  }

  public void setDevicePixelRatio(float devicePixelRatio) {
    // 由设备像素比决定缩放比，使不模糊
    scale = devicePixelRatio;
  }

  /**
   * 私用方法
   */
  private void setUpCurrentState() {
    currentState = stateManager.getCurrentState();
  }

  private void resetLastPoint() {
    lastPoint = new float[]{0.f, 0.f};
  }

  private void resetPaint() {
    paint.reset();
    paint.setFlags(Paint.ANTI_ALIAS_FLAG);
    paint.setTextSize(currentState.textSize);
    paint.setTextAlign(currentState.textAlign);
    paint.clearShadowLayer();
  }

  private void setPaintStyle(Paint.Style style, int[] color) {
    paint.setStyle(style);
    paint.setARGB(color[0], color[1], color[2], color[3]);
    paint.setShadowLayer(
      currentState.shadowBlur,
      currentState.shadowOffsetX,
      currentState.shadowOffsetY,
      CanvasConvert.convertColorListToColor(currentState.shadowColor)
    );
  }

  private void setUpStrokePaint() {
    resetPaint();
    setPaintStyle(Paint.Style.STROKE, currentState.strokeStyle);
    paint.setStrokeCap(currentState.strokeLineCap);
    paint.setStrokeWidth(currentState.strokeLineWidth * scale);
    paint.setStrokeJoin(currentState.strokeLineJoin);
    paint.setPathEffect(currentState.strokeLineDash);
  }

  private void setUpFillPaint() {
    resetPaint();
    setPaintStyle(Paint.Style.FILL, currentState.fillStyle);
  }

  private float getTextVerticalOffset() {
    if (currentState.textBaseline == 0) return 0.f;

    Paint.FontMetrics fm = paint.getFontMetrics();
    float lineHeight = fm.ascent + fm.descent;

    if (currentState.textBaseline == 1) { // top
      return -lineHeight;
    } else if (currentState.textBaseline == 2) { // middle
      return -(lineHeight / 2);
    } else {
      return 0.f;
    }
  }

  private void trackPoint(float x, float y) {
    lastPoint[0] = x;
    lastPoint[1] = y;
  }

  /**
   * Returns the floor modulus of the float arguments. Java modulus will return a negative remainder
   * when the divisor is negative. Modulus should always be positive. This mimics the behavior of
   * Math.floorMod, introduced in Java 8.
   */
  private float modulus(float x, float y) {
    float remainder = x % y;
    float modulus = remainder;
    if (remainder < 0) {
      modulus += y;
    }
    return modulus;
  }

  /**
   * 绘制矩形
   */
  public void clearRect(float x, float y, float width, float height) {
    x *= scale;
    y *= scale;
    width *= scale;
    height *= scale;
    Paint clearPaint = new Paint();
    clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    canvas.drawRect(x, y, x + width, y + height, clearPaint);
  }

  public void fillRect(float x, float y, float width, float height) {
    x *= scale;
    y *= scale;
    width *= scale;
    height *= scale;
    RectF rectF = new RectF(x, y, x + width, y + height);
    setUpFillPaint();
    canvas.drawRect(rectF, paint);
  }

  public void strokeRect(float x, float y, float width, float height) {
    x *= scale;
    y *= scale;
    width *= scale;
    height *= scale;
    RectF rectF = new RectF(x, y, x + width, y + height);
    setUpStrokePaint();
    canvas.drawRect(rectF, paint);
  }

  /**
   * 设置文本样式
   */
  public void setFont(HashMap font) {
    Double fontSize = (Double) font.get("fontSize") * scale;
    Typeface fontFace = Typeface.SANS_SERIF;
    // @todo convert font family ...
    paint.setTypeface(fontFace);
    currentState.setTextSize(fontSize.floatValue());
  }

  public void setTextAlign(String align) {
    currentState.setTextAlign(align);
  }

  public void setTextBaseline(String baseline) {
    currentState.setTextBaseline(baseline);
  }

  /**
   * 绘制文本
   */
  private void drawText(String text, float x, float y) {
    x *= scale;
    y *= scale;
    float textVerticalOffset = getTextVerticalOffset();
    canvas.drawText(text, x, y + textVerticalOffset, paint);
  }

  public void fillText(String text, float x, float y) {
    setUpFillPaint();
    drawText(text, x, y);
  }

  public void strokeText(String text, float x, float y) {
    setUpStrokePaint();
    drawText(text, x, y);
  }

  public HashMap measureText(String text) {
    HashMap textMetric = new HashMap();
    float width = paint.measureText(text);
    textMetric.put("width", width);
    return textMetric;
  }

  /**
   * 设置填充与描边
   */
  public void setFillStyle(float[] style) {
    currentState.setFillStyle(style);
  }

  public void setStrokeStyle(float[] style) {
    currentState.setStrokeStyle(style);
  }

  /**
   * 线型样式
   */
  public void setLineWidth(float lineWidth) {
    currentState.setStrokeLineWidth(lineWidth);
  }

  public void setLineDash(float[] lineDash) {
    if (lineDash.length == 0) return;
    currentState.setStrokeLineDash(lineDash);
  }

  public DashPathEffect getLineDash() {
    return currentState.strokeLineDash;
  }

  public void setLineCap(String lineCap) {
    currentState.setStrokeLineCap(lineCap);
  }

  public void setLineJoin(String lineJoin) {
    currentState.setStrokeLineJoin(lineJoin);
  }

  /**
   * 创建渐变和图案
   */
  public void createLinearGradient(float x0, float y0, float x1, float y1) {
  }

  public void createRadialGradient(float x0, float y0, float r0, float y1, float x1, float r1) {
  }

  public void createPattern() {
  }

  /**
   * 阴影
   */
  public void setShadowColor(float[] color) {
    currentState.setShadowColor(color);
  }

  public void setShadowBlur(float blur) {
    currentState.setShadowBlur(blur);
  }

  public void setShadowOffsetX(float offsetX) {
    currentState.setShadowOffsetX(offsetX);
  }

  public void setShadowOffsetY(float offsetY) {
    currentState.setShadowOffsetY(offsetY);
  }

  /**
   * 生成路径
   */
  public void beginPath() {
    path.reset();
    resetLastPoint();
  }

  public void closePath() {
    path.close();
  }

  public void moveTo(float x, float y) {
    x *= scale;
    y *= scale;
    trackPoint(x, y);
    path.moveTo(x, y);
  }

  public void lineTo(float x, float y) {
    x *= scale;
    y *= scale;
    trackPoint(x, y);
    path.lineTo(x, y);
  }

  public void bezierCurveTo(float cp1x, float cp1y, float cp2x, float cp2y, float x, float y) {
    cp1x *= scale;
    cp1y *= scale;
    cp2x *= scale;
    cp2y *= scale;
    x *= scale;
    y *= scale;
    trackPoint(x, y);
    path.cubicTo(cp1x, cp1y, cp2x, cp2y, x, y);
  }

  public void quadraticCurveTo(float cpx, float cpy, float x, float y) {
    cpx *= scale;
    cpy *= scale;
    x *= scale;
    y *= scale;
    trackPoint(x, y);
    path.quadTo(cpx, cpy, x, y);
  }


  public void arc(float x, float y, float radius, float startAngle, float endAngle) {
    arc(x, y, radius, startAngle, endAngle, false);
  }

  public void arc(float x, float y, float radius, float startAngle, float endAngle, boolean anticlockwise) {
    // 参考自 react-native art/ARTShapeShadowNode.java
    x *= scale;
    y *= scale;
    radius *= scale;
    startAngle = (float) Math.toDegrees((double) startAngle);
    endAngle = (float) Math.toDegrees((double) endAngle);
    float sweepAngle = endAngle - startAngle;

    if (Math.abs(sweepAngle) >= 360) {
      path.addCircle(x, y, radius, anticlockwise ? Path.Direction.CCW : Path.Direction.CW);
    } else {
      sweepAngle = modulus(sweepAngle, 360);
      if (anticlockwise && sweepAngle < 360) {
        // anticlockwise sweeps are negative
        sweepAngle = -1 * (360 - sweepAngle);
      }

      RectF oval = new RectF(x - radius, y - radius, x + radius, y + radius);
      path.arcTo(oval, startAngle, sweepAngle);
    }
  }

  public void arcTo(float x1, float y1, float x2, float y2, float radius) {
    // 几何问题：已知两直线与圆弧半径，求圆弧的圆心坐标
    // https://sourcegraph.com/github.com/WebKit/webkit/-/blob/Source/WebCore/platform/graphics/cairo/PathCairo.cpp#L223:12
    float x0 = lastPoint[0] * scale;
    float y0 = lastPoint[1] * scale;
    x1 *= scale;
    y1 *= scale;
    x2 *= scale;
    y2 *= scale;
    radius *= scale;

    if ((x1 == x0 && y1 == y0) || (x1 == x2 && y1 == y2) || radius == 0) {
      lineTo(x1, y1);
      return;
    }

    float[] p1p0 = new float[]{x0 - x1, y0 - y1};
    float[] p1p2 = new float[]{x2 - x1, y2 - y1};

    float p1p0Length = (float) Math.sqrt((double) p1p0[0] * p1p0[0] + p1p0[1] * p1p0[1]);
    float p1p2Length = (float) Math.sqrt((double) p1p2[0] * p1p2[0] + p1p2[1] * p1p2[1]);

    double cosPhi = (p1p0[0] * p1p2[0] + p1p0[1] * p1p2[1]) / (p1p0Length * p1p2Length);
    // all points on a line logic
    if (cosPhi == -1) {
      lineTo(x1, y1);
      return;
    }
    if (cosPhi == 1) {
      // add infinite far away point
      int maxLength = 65535;
      float factorMax = maxLength / p1p0Length;
      lineTo(x0 + factorMax * p1p0[0], y0 + factorMax * p1p0[1]);
      return;
    }

    float tangent = radius / (float) Math.tan(Math.acos(cosPhi) / 2);
    float factorP1p0 = tangent / p1p0Length;
    float[] tP1p0 = new float[]{x1 + factorP1p0 * p1p0[0], y1 + factorP1p0 * p1p0[1]};

    float[] orthP1p0 = new float[]{p1p0[1], -p1p0[0]};
    float orthP1p0Length = (float) Math.sqrt((double) orthP1p0[0] * orthP1p0[0] + orthP1p0[1] * orthP1p0[1]);
    float factorRa = radius / orthP1p0Length;
    // angle between orthP1p0 and p1p2 to get the right vector orthographic to p1p0
    double cosAlpha = (orthP1p0[0] * p1p2[0] + orthP1p0[1] * p1p2[1]) / (orthP1p0Length * p1p2Length);
    if (cosAlpha < 0) {
      orthP1p0[0] = -orthP1p0[0];
      orthP1p0[1] = -orthP1p0[1];
    }

    float[] p = new float[]{tP1p0[0] + factorRa * orthP1p0[0], tP1p0[1] + factorRa * orthP1p0[1]};

    orthP1p0[0] = -orthP1p0[0];
    orthP1p0[1] = -orthP1p0[1];

    // calculate angles for arc
    float sa = (float) (Math.acos(orthP1p0[0] / orthP1p0Length) * 180 / Math.PI);
    if (orthP1p0[1] < 0) {
      sa = 360 - sa;
    }
    // anticlockwise logic
    boolean anticlockwise = false;

    float factorP1p2 = tangent / p1p2Length;
    float[] tP1p2 = new float[]{x1 + factorP1p2 * p1p2[0], y1 + factorP1p2 * p1p2[1]};
    float[] orthP1p2 = new float[]{tP1p2[0] - p[0], tP1p2[1] - p[1]};
    float orthP1p2Length = (float) Math.sqrt((double) orthP1p2[0] * orthP1p2[0] + orthP1p2[1] * orthP1p2[1]);
    float ea = (float) (Math.acos(orthP1p2[0] / orthP1p2Length) * 180 / Math.PI);
    if (orthP1p2[1] < 0) {
      ea = 360 - ea;
    }
    if ((sa > ea) && ((sa - ea) < 180)) {
      anticlockwise = true;
    }
    if ((sa < ea) && ((ea - sa) > 180)) {
      anticlockwise = true;
    }

    lineTo(tP1p0[0], tP1p0[1]);
    arc(p[0], p[1], radius, sa, ea, anticlockwise);
  }

  public void rect(float x, float y, float width, float height) {
    x *= scale;
    y *= scale;
    width *= scale;
    height *= scale;
    RectF rectF = new RectF(x, y, x + width, y + height);
    path.addRect(rectF, Path.Direction.CW);
  }

  /**
   * 绘制路径
   */
  public void fill() {
    setUpFillPaint();
    canvas.drawPath(path, paint);
  }

  public void stroke() {
    setUpStrokePaint();
    canvas.drawPath(path, paint);
  }

  public void drawFocusIfNeeded() {
  }

  public void scrollPathIntoView() {
  }

  public void clip() {
    path.setFillType(Path.FillType.WINDING);
    canvas.clipPath(path);
  }

  public void clip(String fillRule) {
    if (fillRule.equals("nonzero")) {
      clip();
    } else if (fillRule.equals("evenodd")) {
      path.setFillType(Path.FillType.EVEN_ODD);
      canvas.clipPath(path);
    }
  }

  public void isPointInPath() {
  }

  public void isPointInStroke() {
  }

  /**
   * 坐标变换
   */
  public void rotate(float angle) {
    canvas.rotate(angle);
  }

  public void scale(float x, float y) {
    canvas.scale(x, y);
  }

  public void translate(float x, float y) {
    x *= scale;
    y *= scale;
    canvas.translate(x, y);
  }

  public void transform(float a, float b, float c, float d, float e, float f) {
    // [ MSCALE_X, MSKEW_X, MTRANS_X, MSKEW_Y, MSCALE_Y, MTRANS_Y, MPERSP_0, MPERSP_1, MPERSP_2]
    // MDN canvas transform: https://developer.mozilla.org/en-US/docs/Web/API/CanvasRenderingContext2D/setTransform
    matrix.reset();
    matrix.setValues(new float[]{a, c, e * scale, b, d, f * scale, 0, 0, 1});
    canvas.concat(matrix);
  }

  public void setTransform(float a, float b, float c, float d, float e, float f) {
    resetTransform();
    transform(a, b, c, d, e, f);
  }

  public void resetTransform() {
    matrix.reset();
    canvas.setMatrix(matrix);
  }

  /**
   * 绘制图像
   */
  public void drawImage() {
  }

  /**
   * 像素控制
   */
  public void createImageData() {
  }

  public void getImageData() {
  }

  public void putImageData() {
  }

  /**
   * 状态处理
   */
  public void save() {
    stateManager.save();
    setUpCurrentState();
    canvas.save();
  }

  public void restore() {
    stateManager.restore();
    setUpCurrentState();
    canvas.restore();
  }

}
