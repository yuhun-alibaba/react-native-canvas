package com.graphic.canvas;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Nullable;

/**
 * Created by sam on 2018/11/28.
 */

public class CanvasViewManager extends SimpleViewManager {

  private static final String NAME = "CanvasView";
  private static final HashMap<String, CanvasTextureView> canvasViews = new HashMap<>();

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public CanvasTextureView createViewInstance(ThemedReactContext context) {
    return new CanvasTextureView(context);
  }

  @ReactProp(name = "nativeID")
  public void setNativeID(CanvasTextureView canvas, @Nullable String nativeID) {
    if (getCanvasView(nativeID) == null) {
      setCanvasView(nativeID, canvas);
    }
  }

  @ReactProp(name = "actions")
  public void setActions(CanvasTextureView canvas, @Nullable ReadableArray actions) {
    int size = actions.size();
    if (size == 0) return;

    ArrayList<HashMap> drawingActions = CanvasConvert.convertActions(actions);
    canvas.setActions(drawingActions);
    canvas.drawOutput();
  }

  private static void setCanvasView(String tag, CanvasTextureView canvas) {
    canvasViews.put(tag, canvas);
  }

  public static CanvasTextureView getCanvasView(String tag) {
    return canvasViews.get(tag);
  }

  public static void removeCanvasView(String tag) {
    canvasViews.remove(tag);
  }

}
