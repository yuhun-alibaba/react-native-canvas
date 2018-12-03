package com.graphic.canvas;

import android.graphics.Paint;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.UiThreadUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sam on 2018/11/28.
 */

public class CanvasAPI extends ReactContextBaseJavaModule {

  private static final String NAME = "CanvasAPI";
  private static final Paint paint = new Paint();

  public CanvasAPI(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return NAME;
  }

  @ReactMethod(isBlockingSynchronousMethod = true)
  public WritableMap measureText(String text, double fontSize) {
    HashMap textMetric = new HashMap();
    paint.setTextSize((float) fontSize);
    float width = paint.measureText(text);
    textMetric.put("width", width);

    return Arguments.makeNativeMap(textMetric);
  }

  @ReactMethod(isBlockingSynchronousMethod = true)
  public Integer drawSync(final String tag, ReadableArray actions) {
    int size = actions.size();
    if (size == 0) return 0;

    final ArrayList<HashMap> drawingActions = CanvasConvert.convertActions(actions);
    UiThreadUtil.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        CanvasView canvas = CanvasViewManager.getCanvasView(tag);
        if (canvas != null) {
          canvas.setActions(drawingActions);
          canvas.invalidate();
        }
      }
    });

    return 1;
  }

  @ReactMethod
  public void release(String tag) {
    CanvasViewManager.removeCanvasView(tag);
  }

}
