package com.graphic.canvas;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Nullable;

/**
 * Created by sam on 2018/11/28.
 */

public class CanvasViewManager extends SimpleViewManager {

  private static final String NAME = "CanvasView";
  private static final HashMap<String, CanvasView> canvasViews = new HashMap<>();

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public CanvasView createViewInstance(ThemedReactContext context) {
    return new CanvasView(context);
  }

  @ReactProp(name = "nativeID")
  public void setNativeID(CanvasView canvas, @Nullable String nativeID) {
    if (getCanvasView(nativeID) == null) {
      setCanvasView(nativeID, canvas);
    }
  }

  @ReactProp(name = "actions")
  public void setActions(CanvasView canvas, @Nullable ReadableArray actions) {
    int size = actions.size();
    if (size == 0) {
      return;
    }
    ArrayList<HashMap> drawingActions = new ArrayList<HashMap>();

    for(int i = 0; i < size; i++) {
      ReadableMap command = actions.getMap(i);
      String method = command.getString("method");
      ArrayList arguments = command.getArray("arguments").toArrayList();
      HashMap action = new HashMap();

      action.put("method", method);
      action.put("arguments", (Object []) arguments.toArray());

      drawingActions.add(action);
    }

    canvas.setActions(drawingActions);
    canvas.invalidate();
  }

  private static void setCanvasView(String tag, CanvasView canvas) {
    canvasViews.put(tag, canvas);
  }

  private static CanvasView getCanvasView(String tag) {
    return canvasViews.get(tag);
  }

  private static void removeCanvasView(String tag) {
    canvasViews.remove(tag);
  }

}
