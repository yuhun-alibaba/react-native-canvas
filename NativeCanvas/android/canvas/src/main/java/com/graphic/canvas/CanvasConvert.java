package com.graphic.canvas;

import android.graphics.DashPathEffect;
import android.graphics.Paint;

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

  public static int[] convertColor(float[] style) {
    if (style.length != 4) {
      return new int[]{255, 0, 0, 0};
    }

    return new int[]{
      (int) (style[3] * 255), // alpha
      (int) (style[0] * 255),
      (int) (style[1] * 255),
      (int) (style[2] * 255)
    };
  }

  public static Paint.Cap convertLineCap(String lineCap) {
    Paint.Cap cap = Paint.Cap.BUTT;
    if (lineCap.equals("round")) {
      cap = Paint.Cap.ROUND;
    } else if (lineCap.equals("square")) {
      cap = Paint.Cap.SQUARE;
    }
    return cap;
  }

  public static Paint.Join convertLineJoin(String lineJoin) {
    Paint.Join join = Paint.Join.MITER;
    if (lineJoin.equals("bevel")) {
      join = Paint.Join.BEVEL;
    } else if (lineJoin.equals("round")) {
      join = Paint.Join.ROUND;
    }
    return join;
  }

  public static DashPathEffect convertLineDash(float[] lineDash) {
    float size = lineDash.length;
    boolean isOdd = size % 2 != 0;
    float[] dashEffect;

    if (isOdd) {
      // 奇数变偶数
      dashEffect = new float[lineDash.length * 2];
    } else {
      dashEffect = new float[lineDash.length];
    }

    for (int i = 0; i < dashEffect.length; i++) {
      int atIndex = (int) (i % size);
      dashEffect[i] = lineDash[atIndex];
    }

    return new DashPathEffect(dashEffect, 0);
  }

  public static Paint.Align convertTextAlign(String textAlign) {
    Paint.Align align = Paint.Align.LEFT;
    if (textAlign.equals("right")) {
      align = Paint.Align.RIGHT;
    } else if (textAlign.equals("center")) {
      align = Paint.Align.CENTER;
    }
    return align;
  }

  public static int convertTextBaseline(String baseline) {
    int baselineType = 0;
    if (baseline.equals("bottom")) {
      baselineType = 1;
    } else if (baseline.equals("middle")) {
      baselineType = 2;
    }
    return baselineType;
  }


}
