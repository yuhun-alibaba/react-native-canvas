package com.graphic.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import java.util.ArrayList;
import java.lang.Object;
import java.util.HashMap;

/**
 * Created by sam on 2018/11/21.
 */

public class CanvasView extends View {
  private static final CanvasMethodDelegate module = new CanvasMethodDelegate(CanvasRenderingContext2D.class);

  ArrayList<HashMap> actions = new ArrayList<>();
  private final CanvasRenderingContext2D renderingContext2D = new CanvasRenderingContext2D();

  public CanvasView(Context context) {
    super(context);
    setDevicePixelRatio(context);
  }

  /**
   * { "method": "", "arguments": [] };
   */
  private void runActions() {
    for (HashMap action : actions) {
      module.invoke(renderingContext2D, (String) action.get("method"), (Object[]) action.get("arguments"));
    }
  }

  private void paint(Canvas canvas) {
    // 不超出边界
    canvas.clipRect(0, 0, canvas.getWidth(), canvas.getHeight());
    renderingContext2D.setCanvas(canvas);
    runActions();
  }

  private void setDevicePixelRatio(Context context) {
    renderingContext2D.setDevicePixelRatio(context.getResources().getDisplayMetrics().density);
  }

  public void setActions(ArrayList<HashMap> drawActions) {
    actions = new ArrayList<HashMap>(drawActions);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    paint(canvas);
  }
}
