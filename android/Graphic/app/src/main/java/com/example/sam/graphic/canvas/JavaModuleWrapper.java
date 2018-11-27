package com.example.sam.graphic.canvas;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by sam on 2018/11/21.
 *
 * 生成模块方法配置，参考自 react-native
 *
 */

public class JavaModuleWrapper {

  private final Class mModuleClass;
  private final HashMap<String, JavaMethodWrapper> mMethods;
  private final String TAG = "JavaModuleWrapper";

  public JavaModuleWrapper(Class moduleClass) {
    mModuleClass = moduleClass;
    mMethods = new HashMap();
    findMethods();
  }

  private void findMethods() {
    Class classForMethods = mModuleClass;
    Method[] targetMethods = classForMethods.getDeclaredMethods();

    for (Method targetMethod : targetMethods) {
      JavaMethodWrapper method = new JavaMethodWrapper(targetMethod);
      mMethods.put(method.getName(), method);
    }
  }

  public void invoke(Object mModuleClassInstance, String method, Object[] arguments) {
    JavaMethodWrapper drawMethod = mMethods.get(method);
    if (drawMethod != null) {
      drawMethod.invoke(mModuleClassInstance, arguments);
    } else {
      Log.w(TAG, "Could not find method " + method);
    }
  }
}
