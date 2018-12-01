package com.graphic.canvas;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by sam on 2018/11/21.
 *
 * 生成模块方法配置，参考自 react-native
 */

public class CanvasMethodDelegate {

  private final Class mModuleClass;
  private final HashMap<String, CanvasMethodWrapper> mMethods;
  private final String TAG = "CanvasMethodDelegate";

  public CanvasMethodDelegate(Class moduleClass) {
    mModuleClass = moduleClass;
    mMethods = new HashMap();
    findMethods();
  }

  private void findMethods() {
    Class classForMethods = mModuleClass;
    Method[] targetMethods = classForMethods.getDeclaredMethods();

    for (Method targetMethod : targetMethods) {
      CanvasMethodWrapper method = new CanvasMethodWrapper(targetMethod);
//      Log.i(TAG, method.getName());
      mMethods.put(method.getName(), method);
    }
  }

  public void invoke(Object mModuleClassInstance, String method, Object[] arguments) {
    CanvasMethodWrapper drawMethod = mMethods.get(method);
    if (drawMethod != null) {
      drawMethod.invoke(mModuleClassInstance, arguments);
    } else {
      Log.w(TAG, "Could not find method " + method);
    }
  }
}
