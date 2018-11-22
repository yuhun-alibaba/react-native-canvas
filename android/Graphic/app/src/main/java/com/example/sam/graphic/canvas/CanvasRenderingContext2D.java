package com.example.sam.graphic.canvas;


import android.graphics.Canvas;
import android.graphics.DashPathEffect;
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

  private Canvas canvas;
  private int[] fillStyle;
  private int[] strokeStyle;

  public Canvas getCanvas() {
    return canvas;
  }

  public void setCanvas(Canvas canvasInstance) {
    canvas = canvasInstance;
  }

  public void drawColor(int color) {
    canvas.drawColor(color);
  }

  /**
   *  私用公共方法
   */
  private void setPaintStyle(Paint.Style style, int[] color){
    paint.setStyle(style);
    paint.setARGB(color[0], color[1], color[2], color[3]);
  }

  private void setFillAndStrokeBeforeDrawing(){
    paint.setStyle(Paint.Style.FILL_AND_STROKE);
  }

  private void setStrokePaintBeforeDrawing(){
    setPaintStyle(Paint.Style.STROKE, strokeStyle);
  }

  private void setFillPaintBeforeDrawing(){
    setPaintStyle(Paint.Style.FILL, fillStyle);
  }

  /**
   *   绘制矩形
   */
  public void clearRect(float x, float y, float width, float height){
    Paint clearPaint = new Paint();
    clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    canvas.drawRect(x, y, x + width, y + height, clearPaint);
  }

  public void fillRect(float x, float y, float width, float height){
    RectF rectF = new RectF(x, y, x + width, y + height);
    setFillPaintBeforeDrawing();
    canvas.drawRect(rectF, paint);
  }

  public void strokeRect(float x, float y, float width, float height){
    RectF rectF = new RectF(x, y, x + width, y + height);
    setStrokePaintBeforeDrawing();
    canvas.drawRect(rectF, paint);
  }

  /**
   *   设置文本样式
   */
  public void setFont(HashMap font){
    float fontSize = (float) font.get("fontSize");
    Typeface fontFace = Typeface.SANS_SERIF;
    // @todo convert font family ...
    paint.setTextSize(fontSize);
    paint.setTypeface(fontFace);
  }

  public void setTextAlign(String textAlign){
    // @todo convert textAlign
    paint.setTextAlign(Paint.Align.LEFT);
  }

  public void setTextBaseline(String textBaseline){
    // @todo measure text metric and set position
  }

  /**
   *   绘制文本
   */
  private void drawText(String text, float x, float y) {
    canvas.drawText(text, x, y, paint);
  }

  public void fillText(String text, float x, float y){
    setFillPaintBeforeDrawing();
    drawText(text, x, y);
  }

  public void strokeText(String text, float x, float y){
    setStrokePaintBeforeDrawing();
    drawText(text, x, y);
  }

  public HashMap measureText(String text){
    HashMap textMetric = new HashMap();
    float width = paint.measureText(text);
    textMetric.put("width", width);
    return textMetric;
  }

  /**
   *   设置填充与描边
   */
  public void setFillStyle(int [] style){
    fillStyle = style;
  }

  public void setStrokeStyle(int [] style){
    strokeStyle = style;
  }

  /**
   *   线型样式
   */
  public void setLineWidth(float lineWidth) {
    paint.setStrokeWidth(lineWidth);
  }

  public void setLineDash(float[] lineDash) {
    // @todo convert lineDash
    paint.setPathEffect(new DashPathEffect(lineDash, 0));
  }

  public PathEffect getLineDash() {
    return paint.getPathEffect();
  }

  public void setLineJoin(String lineJoin) {
    // @todo convert lineJoin
    paint.setStrokeJoin(Paint.Join.BEVEL);
  }

  public void setLineCap(String lineCap) {
    // @todo convert lineCap
    paint.setStrokeCap(Paint.Cap.ROUND);
  }

  /**
   *   创建渐变和图案
   */
  public void createLinearGradient(float x0, float y0, float x1, float y1){}
  public void createRadialGradient(float x0, float y0, float r0, float y1, float x1, float r1){}
  public void createPattern(){}

  /**
   *   生成路径
   */
  public void beginPath(){
    path.rewind();
  }

  public void closePath(){
    path.close();
  }

  public void moveTo(float x, float y){
    path.moveTo(x, y);
  }

  public void lineTo(float x, float y){
    path.lineTo(x, y);
  }

  public void bezierCurveTo(float cp1x, float cp1y, float cp2x, float cp2y, float x, float y){}

  public void quadraticCurveTo(float cpx, float cpy, float x, float y){}

  public void arc(float x, float y, float radius, float startAngle, float endAangle, int anticlockwise){
    // @todo anticlockwise
    RectF rectF = new RectF(x - radius, y - radius, x + radius, y + radius);
    path.addArc(rectF, startAngle, endAangle);
  }

  public void arcTo(float x1, float y1, float x2, float y2, float radius){}

  public void rect(float x, float y, float width, float height){
    RectF rectF = new RectF(x, y, x + width, y + height);
    path.addRect(rectF, Path.Direction.CW);
  }

  /**
   *   绘制路径
   */
  public void fill(){
    setFillAndStrokeBeforeDrawing();
    canvas.drawPath(path, paint);
  }

  public void stroke(){
    setStrokePaintBeforeDrawing();
    canvas.drawPath(path, paint);
  }

  public void drawFocusIfNeeded(){}
  public void scrollPathIntoView(){}
  public void clip(){}
  public void clip(String fillRule){}
  public void isPointInPath(){}
  public void isPointInStroke(){}

  /**
   *   坐标变换
   */
  public void rotate(float angle){
    canvas.rotate(angle);
  }

  public void scale(float x, float y){
    canvas.scale(x, y);
  }

  public void translate(float x, float y){
    canvas.translate(x, y);
  }

  public void transform(float a, float b, float c, float d, float e, float f){}
  public void setTransform(float a, float b, float c, float d, float e, float f){}

  /**
   *   绘制图像
   */
  public void drawImage(){}

  /**
   *   像素控制
   */
  public void createImageData(){}
  public void getImageData(){}
  public void putImageData(){}

  /**
   *   状态处理
   */
  public void save(){
    canvas.save();
  }

  public void restore(){
    canvas.restore();
  }

}
