package com.example.sam.graphic.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;
import java.lang.Object;
import java.util.HashMap;

/**
 * Created by sam on 2018/11/21.
 */

public class CanvasView extends View {
  private static final JavaModuleWrapper module = new JavaModuleWrapper(CanvasRenderingContext2D.class);

  ArrayList<HashMap> actions = new ArrayList<>();
  private final CanvasRenderingContext2D context = new CanvasRenderingContext2D();

  public CanvasView(Context context) {
    super(context);
  }

  /**
   * { "method": "", "arguments": [] };
   */
  private void runAction(String method, Object[] arguments) {
    module.invoke(context, method, arguments);
  }

  private void runActions() {
    for (HashMap action : actions) {
      runAction((String) action.get("method"), (Object[]) action.get("arguments"));
    }
  }

  public void setActions(ArrayList<HashMap> drawActions){
    actions = new ArrayList<HashMap>(drawActions);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    if (context.getCanvas() == null) {
      context.setCanvas(canvas);
    }

    runActions();
  }
}
