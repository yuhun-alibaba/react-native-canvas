package com.graphic.canvas;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sam on 2018/11/28.
 */

public class CanvasAPI extends ReactContextBaseJavaModule {

  private static final String NAME = "CanvasAPI";

  public CanvasAPI(ReactApplicationContext reactContext){
    super(reactContext);
  }

  @Override
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public HashMap measureText(String text, double fontSize){
    // @todo measureText
    HashMap d = new HashMap();
    d.put("width", 10);
    return d;
  }

  @ReactMethod(isBlockingSynchronousMethod = true)
  public Integer drawSync(String tag, ReadableArray actions) {
    int size = actions.size();
    if (size == 0) return 0;

    ArrayList<HashMap> drawingActions = CanvasConvert.convertActions(actions);
    CanvasView canvas = CanvasViewManager.getCanvasView(tag);

    if (canvas != null) {
      canvas.setActions(drawingActions);
      canvas.invalidate();
    }

    return 1;
  }

  @ReactMethod
  public void release(String tag){
    CanvasViewManager.removeCanvasView(tag);
  }

}
