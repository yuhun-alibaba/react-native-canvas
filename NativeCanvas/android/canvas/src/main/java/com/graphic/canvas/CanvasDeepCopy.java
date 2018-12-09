package com.graphic.canvas;

/**
 * Created by sam on 2018/12/1.
 */

public class CanvasDeepCopy {

  public static String deepCopyString(String a) {
    return new String(a);
  }

  public static float deepCopyFloat(float a) {
    float b = a;
    return b;
  }

  public static float[] deepCopyFloatList(float[] a) {
    if (a.length == 0) {
      return new float[]{};
    }

    float[] b = new float[a.length];
    for (int i = 0; i < a.length; i++) {
      b[i] = deepCopyFloat(a[i]);
    }
    return b;
  }

  public static int deepCopyInt(int a) {
    int b = a;
    return b;
  }

  public static int[] deepCopyIntList(int[] a) {
    if (a.length == 0) {
      return new int[]{};
    }

    int[] b = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      b[i] = deepCopyInt(a[i]);
    }
    return b;
  }

}
