package com.nativecanvas;

import com.graphic.RNCanvas.CanvasAPI;
import com.graphic.RNCanvas.CanvasViewManager;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sam on 2018/11/28.
 */

public class ReactNativePackages implements ReactPackage {

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    return Arrays.<ViewManager>asList(
      new CanvasViewManager()
    );
  }

  @Override
  public List<NativeModule> createNativeModules(
    ReactApplicationContext reactContext) {
    List<NativeModule> modules = new ArrayList<>();

    modules.add(new CanvasAPI(reactContext));

    return modules;
  }

}
