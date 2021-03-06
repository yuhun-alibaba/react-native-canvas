package com.graphic.RNCanvas;


import com.facebook.common.logging.FLog;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by sam on 2018/11/21.
 * <p>
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
      mMethods.put(method.getName(), method);
    }
  }

  public void invoke(Object mModuleClassInstance, String method, Object[] arguments) {
    CanvasMethodWrapper drawMethod = mMethods.get(method);
    if (drawMethod != null) {
      drawMethod.invoke(mModuleClassInstance, arguments);
    } else {
      FLog.w(TAG, "Could not find method " + method);
    }
  }
}
