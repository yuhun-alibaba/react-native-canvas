package com.graphic.canvas;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sam on 2018/11/29.
 */

public class CanvasConvert {

  public static HashMap createAction(String method, Object[] arguments) {
    HashMap action = new HashMap();
    action.put("method", method);
    action.put("arguments", arguments);
    return action;
  }

  public static ArrayList<HashMap> convertActions(ReadableArray actions) {
    int size = actions.size();

    ArrayList<HashMap> drawingActions = new ArrayList<HashMap>();

    for (int i = 0; i < size; i++) {
      ReadableMap command = actions.getMap(i);
      String method = command.getString("method");
      ArrayList arguments = command.getArray("arguments").toArrayList();

      drawingActions.add(createAction(method, arguments.toArray()));
    }

    return drawingActions;
  }

  public static int[] convertColor(int[] style) {
    if (style.length != 4) {
      return new int[]{255, 0, 0, 0};
    }
    // alpha
    return new int[]{style[3] * 255, style[0] * 255, style[1] * 255, style[2] * 255};
  }

  public static float convertDegree(float degree) {
    return (float) (degree * 180 / Math.PI);
  }
}
