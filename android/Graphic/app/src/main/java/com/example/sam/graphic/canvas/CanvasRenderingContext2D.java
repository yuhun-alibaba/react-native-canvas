package com.example.sam.graphic.canvas;


import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by sam on 2018/11/21.
 */

public class CanvasRenderingContext2D {
  private final Paint paint = new Paint();
  private final Path path = new Path();
  private final Float[] lastPoint = new Float[]{(float) 0, (float) 0}; // tracking point

  private Canvas canvas;
  private int[] fillStyle = new int[]{255, 0, 0, 0};
  private int[] strokeStyle = new int[]{255, 0, 0, 0};
  private int textBaseline;

  public CanvasRenderingContext2D() {
    setLineWidth((float) 1);
  }

  public Canvas getCanvas() {
    return canvas;
  }

  public void setCanvas(Canvas canvasInstance) {
    canvas = canvasInstance;
  }

  /**
   * 私用方法
   */
  private void setPaintStyle(Paint.Style style, int[] color) {
    paint.setStyle(style);
    paint.setARGB(color[0], color[1], color[2], color[3]);
  }

  private void setFillAndStrokeBeforeDrawing() {
    setPaintStyle(Paint.Style.FILL_AND_STROKE, fillStyle);
  }

  private void setStrokePaintBeforeDrawing() {
    setPaintStyle(Paint.Style.STROKE, strokeStyle);
  }

  private void setFillPaintBeforeDrawing() {
    setPaintStyle(Paint.Style.FILL, fillStyle);
  }

  private float getTextVerticalOffset() {
    if (textBaseline == 0) return (float) 0;

    Paint.FontMetrics fm = paint.getFontMetrics();
    float lineHeight = fm.bottom - fm.top + fm.leading;

    if (textBaseline == 1) { // bottom
      return -lineHeight;
    } else if (textBaseline == 2) { // middle
      return -(lineHeight / 2);
    } else {
      return (float) 0;
    }
  }

  private void trackPoint(float x, float y) {
    lastPoint[0] = x;
    lastPoint[1] = y;
  }

  /**
   * 绘制矩形
   */
  public void clearRect(float x, float y, float width, float height) {
    Paint clearPaint = new Paint();
    clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    canvas.drawRect(x, y, x + width, y + height, clearPaint);
  }

  public void fillRect(float x, float y, float width, float height) {
    RectF rectF = new RectF(x, y, x + width, y + height);
    setFillPaintBeforeDrawing();
    canvas.drawRect(rectF, paint);
  }

  public void strokeRect(float x, float y, float width, float height) {
    RectF rectF = new RectF(x, y, x + width, y + height);
    setStrokePaintBeforeDrawing();
    canvas.drawRect(rectF, paint);
  }

  /**
   * 设置文本样式
   */
  public void setFont(HashMap font) {
    float fontSize = (float) font.get("fontSize");
    Typeface fontFace = Typeface.SANS_SERIF;
    // @todo convert font family ...
    paint.setTextSize(fontSize);
    paint.setTypeface(fontFace);
  }

  public void setTextAlign(String textAlign) {
    Paint.Align align = Paint.Align.LEFT;
    if (textAlign.equals((String) "right")) {
      align = Paint.Align.RIGHT;
    } else if (textAlign.equals((String) "center")) {
      align = Paint.Align.CENTER;
    }
    paint.setTextAlign(align);
  }

  public void setTextBaseline(int baseline) {
    textBaseline = baseline;
  }

  /**
   * 绘制文本
   */
  private void drawText(String text, float x, float y) {
    float textVerticalOffset = getTextVerticalOffset();
    canvas.drawText(text, x, y + textVerticalOffset, paint);
  }

  public void fillText(String text, float x, float y) {
    setFillPaintBeforeDrawing();
    drawText(text, x, y);
  }

  public void strokeText(String text, float x, float y) {
    setStrokePaintBeforeDrawing();
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
  public void setFillStyle(int[] style) {
    fillStyle = style;
  }

  public void setStrokeStyle(int[] style) {
    strokeStyle = style;
  }

  /**
   * 线型样式
   */
  public void setLineWidth(float lineWidth) {
    paint.setStrokeWidth(lineWidth);
  }

  public void setLineDash(float[] lineDash) {
    paint.setPathEffect(new DashPathEffect(lineDash, 0));
  }

  public PathEffect getLineDash() {
    return paint.getPathEffect();
  }

  public void setLineJoin(String lineJoin) {
    Paint.Join join = Paint.Join.BEVEL;
    if (lineJoin.equals((String) "miter")) {
      join = Paint.Join.MITER;
    } else if (lineJoin.equals((String) "round")) {
      join = Paint.Join.ROUND;
    }
    paint.setStrokeJoin(join);
  }

  public void setLineCap(String lineCap) {
    Paint.Cap cap = Paint.Cap.ROUND;
    if (lineCap.equals((String) "butt")) {
      cap = Paint.Cap.BUTT;
    } else if (lineCap.equals((String) "square")) {
      cap = Paint.Cap.SQUARE;
    }
    paint.setStrokeCap(cap);
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
   * 生成路径
   */
  public void beginPath() {
    path.rewind();
  }

  public void closePath() {
    path.close();
  }

  public void moveTo(float x, float y) {
    trackPoint(x, y);
    path.moveTo(x, y);
  }

  public void lineTo(float x, float y) {
    trackPoint(x, y);
    path.lineTo(x, y);
  }

  public void bezierCurveTo(float cp1x, float cp1y, float cp2x, float cp2y, float x, float y) {
    trackPoint(x, y);
    path.cubicTo(cp1x, cp1y, cp2x, cp2y, x, y);
  }

  public void quadraticCurveTo(float cpx, float cpy, float x, float y) {
    trackPoint(x, y);
    path.quadTo(cpx, cpy, x, y);
  }


  public void arc(float x, float y, float radius, float startAngle, float endAngle) {
    arc(x, y, radius, startAngle, endAngle, 0);
  }

  public void arc(float x, float y, float radius, float startAngle, float endAngle, int anticlockwise) {
    if ((endAngle - startAngle) == 360) {
      path.addCircle(x, y, radius, anticlockwise == 1 ? Path.Direction.CW : Path.Direction.CCW);
      return;
    }
    if (endAngle < startAngle) {
      endAngle = 360 + endAngle;
    }
    float sweepAngle = endAngle - startAngle;
    if (anticlockwise == 1) { //  逆时针
      sweepAngle = -sweepAngle;
    }
    RectF rectF = new RectF(x - radius, y - radius, x + radius, y + radius);
    path.arcTo(rectF, startAngle, sweepAngle);
  }


  public void arcTo(float x1, float y1, float x2, float y2, float radius) {
    // 几何问题：已知两直线与圆弧半径，求圆弧的圆心坐标
    // https://sourcegraph.com/github.com/WebKit/webkit/-/blob/Source/WebCore/platform/graphics/cairo/PathCairo.cpp#L223:12
    float x0 = lastPoint[0];
    float y0 = lastPoint[1];

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
    arc(p[0], p[1], radius, sa, ea, anticlockwise ? 1 : 0);
  }

  public void rect(float x, float y, float width, float height) {
    RectF rectF = new RectF(x, y, x + width, y + height);
    path.addRect(rectF, Path.Direction.CW);
  }

  /**
   * 绘制路径
   */
  public void fill() {
    setFillAndStrokeBeforeDrawing();
    canvas.drawPath(path, paint);
  }

  public void stroke() {
    setStrokePaintBeforeDrawing();
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
    if (fillRule.equals((String) "nonzero")) {
      clip();
    } else if (fillRule.equals((String) "evenodd")) {
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
    canvas.translate(x, y);
  }

  public void transform(float a, float b, float c, float d, float e, float f) {
    // [ MSCALE_X, MSKEW_X, MTRANS_X, MSKEW_Y, MSCALE_Y, MTRANS_Y, MPERSP_0, MPERSP_1, MPERSP_2]
    // MDN canvas transform: https://developer.mozilla.org/en-US/docs/Web/API/CanvasRenderingContext2D/setTransform
    Matrix transformed = new Matrix();
    transformed.setValues(new float[]{a, c, e, b, d, f, 0, 0, 1});
    canvas.concat(transformed);
  }

  public void setTransform(float a, float b, float c, float d, float e, float f) {
    transform((float) 1, (float) 0, (float) 0, (float) 1, (float) 0, (float) 0);
    transform(a, b, c, d, e, f);
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
    canvas.save();
  }

  public void restore() {
    canvas.restore();
  }

}
