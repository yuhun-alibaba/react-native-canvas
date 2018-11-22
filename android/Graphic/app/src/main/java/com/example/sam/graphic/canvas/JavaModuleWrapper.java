package com.example.sam.graphic.canvas;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
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
    mMethods.get(method).invoke(mModuleClassInstance, arguments);
  }
}
