package com.graphic.RNCanvas;


import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by sam on 2018/11/28.
 */

public class CanvasViewManager extends SimpleViewManager {

  private static final String NAME = "CanvasView";
  private static final HashMap<String, CanvasTextureView> canvasViews = new HashMap();

  private static void setCanvasView(String tag, CanvasTextureView canvas) {
    canvasViews.put(tag, canvas);
  }

  public static CanvasTextureView getCanvasView(String tag) {
    return canvasViews.get(tag);
  }

  public static void removeCanvasView(String tag) {
    canvasViews.remove(tag);
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public CanvasTextureView createViewInstance(ThemedReactContext context) {
    return new CanvasTextureView(context);
  }

  @Override
  public @Nullable
  Map getExportedCustomDirectEventTypeConstants() {
    return MapBuilder.of(
      "onReady",
      MapBuilder.of("registrationName", "onReady")
    );
  }

  @ReactProp(name = ViewProps.BACKGROUND_COLOR, customType = "Color")
  public void setBackgroundColor(CanvasTextureView view, @Nullable Integer color) {
    view.setBackgroundColor(color);
  }

  @ReactProp(name = "nativeID")
  public void setNativeID(CanvasTextureView view, @Nullable String nativeID) {
    if (getCanvasView(nativeID) == null) {
      setCanvasView(nativeID, view);
    }
  }

  @ReactProp(name = "actions")
  public void setActions(CanvasTextureView view, @Nullable ReadableArray actions) {
    int size = actions.size();
    if (size == 0) return;

    ArrayList<HashMap> drawingActions = CanvasConvert.convertActions(actions);
    view.setActions(drawingActions);
    view.drawOutput();
  }

}
