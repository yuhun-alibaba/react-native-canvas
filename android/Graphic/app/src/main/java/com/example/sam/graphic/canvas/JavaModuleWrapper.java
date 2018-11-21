package com.example.sam.graphic.canvas;


import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by sam on 2018/11/21.
 */

public class JavaModuleWrapper {

  private final Class mModuleClass;
  private final Object mModuleClassInstance;
  private final HashMap<String, JavaMethodWrapper> mMethods;

  public JavaModuleWrapper(Object moduleClassInstance) {
    mModuleClass = moduleClassInstance.getClass();
    mModuleClassInstance = moduleClassInstance;
    mMethods = new HashMap();
    findMethods();
  }

  private void findMethods() {
    Class classForMethods = mModuleClass;
    Method[] targetMethods = classForMethods.getDeclaredMethods();

    for (Method targetMethod : targetMethods) {
        JavaMethodWrapper method = new JavaMethodWrapper(this, targetMethod);
        mMethods.put(method.getName(), method);
    }
  }

  public String getName() {
      return mModuleClass.getName();
  }

  public void invoke(String method, Object[] arguments) {
    if (mMethods == null) {
      return;
    }

    mMethods.get(method).invoke(mModuleClassInstance, arguments);
  }
}
