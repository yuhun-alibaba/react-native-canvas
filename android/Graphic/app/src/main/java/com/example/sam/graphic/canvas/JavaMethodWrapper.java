package com.example.sam.graphic.canvas;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by sam on 2018/11/21.
 */

public class JavaMethodWrapper {

  private final String TAG = "JavaMethodWrapper";
  private final Method mMethod;
  private final String mMethodName;

  public JavaMethodWrapper(Method method) {
    mMethod = method;
    mMethod.setAccessible(true);
    mMethodName = buildName(mMethod.getParameterTypes());
  }

  private static String paramTypeToString(Class typeClass) {
    if (typeClass == boolean.class) {
      return new String("boolean");
    } else if (typeClass == Boolean.class) {
      return new String("Boolean");
    } else if (typeClass == int.class) {
      return new String("int");
    } else if (typeClass == Integer.class) {
      return new String("Integer");
    } else if (typeClass == double.class) {
      return new String("double");
    } else if (typeClass == Double.class) {
      return new String("Double");
    } else if (typeClass == float.class) {
      return new String("float");
    } else if (typeClass == Float.class) {
      return new String("Float");
    } else if (typeClass == String.class) {
      return new String("String");
    } else {
      return new String("other");
    }
  }

  private String buildName(Class[] parameterTypes){
    String methodName = new String(mMethod.getName());
    String sep = new String(":");

    for (int i = 0; i < parameterTypes.length; i++) {
      if (i == 0) {
        methodName += sep;
      }
      Class paramClass = parameterTypes[i];
      methodName += paramTypeToString(paramClass);

      if (i < (parameterTypes.length - 1)) {
        methodName += sep;
      }
    }
    return methodName;
  }

  public String getName() {
    return mMethodName;
  }

  public void invoke(Object moduleClassInstance, Object[] parameters) {
    try {
      mMethod.invoke(moduleClassInstance, parameters);
      Log.i(TAG, "invoke " + mMethodName);
    } catch (IllegalArgumentException ie) {
      Log.w(TAG, "IllegalArgumentException: Could not invoke " + mMethodName);
    } catch (IllegalAccessException iae) {
      Log.w(TAG, "IllegalAccessException: Could not invoke " + mMethodName);
    } catch (InvocationTargetException ite) {
      Log.w(TAG, "InvocationTargetException: Could not invoke " + mMethodName);
    }

  }

}
