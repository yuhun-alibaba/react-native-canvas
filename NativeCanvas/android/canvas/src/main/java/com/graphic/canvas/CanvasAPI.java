package com.graphic.canvas;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;

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
}
