package com.graphic.canvas;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sam on 2018/11/21.
 */

public class JavaMethodWrapper {

  private static class ArgumentExtractor<T> {
    public T extractArgument(
      Object[] arguments, int atIndex){
      return (T) arguments[atIndex];
    }
  }

  static final private ArgumentExtractor<Float> ARGUMENT_EXTRACTOR_FLOAT =
    new ArgumentExtractor<Float>() {
      @Override
      public Float extractArgument(
        Object[] arguments, int atIndex) {
        Double value = (Double)arguments[atIndex];
        return new Float(value);
      }
    };

  static final private ArgumentExtractor<float[]> ARGUMENT_EXTRACTOR_FLOAT_LIST =
    new ArgumentExtractor<float[]>() {
      @Override
      public float[] extractArgument(
        Object[] arguments, int atIndex) {
        ArrayList argument = (ArrayList) arguments[atIndex];
        float[] list = new float[argument.size()];

        for (int i = 0; i < list.length; i++) {
          Double value = (Double)argument.get(i);
          list[i] = value.floatValue();
        }
        return list;
      }
    };

  static final private ArgumentExtractor<Integer> ARGUMENT_EXTRACTOR_INTEGER =
    new ArgumentExtractor<Integer>() {
      @Override
      public Integer extractArgument(
        Object[] arguments, int atIndex) {
        Double value = (Double)arguments[atIndex];
        return new Integer(value.intValue());
      }
    };

  static final private ArgumentExtractor<int[]> ARGUMENT_EXTRACTOR_INTEGER_LIST =
    new ArgumentExtractor<int[]>() {
      @Override
      public int[] extractArgument(
        Object[] arguments, int atIndex) {
        ArrayList argument = (ArrayList) arguments[atIndex];
        int[] list = new int[argument.size()];

        for (int i = 0; i < list.length; i++) {
          Double value = (Double)argument.get(i);
          list[i] = value.intValue();
        }
        return list;
      }
    };

  static final private ArgumentExtractor<HashMap> ARGUMENT_EXTRACTOR_HASHMAP =
    new ArgumentExtractor<HashMap>() {
      @Override
      public HashMap extractArgument(
        Object[] arguments, int atIndex) {
        return (HashMap) arguments[atIndex];
      }
    };

  private ArgumentExtractor[] buildArgumentExtractors(Class[] paramTypes) {
    ArgumentExtractor[] argumentExtractors = new ArgumentExtractor[paramTypes.length];
    for (int i = 0; i < paramTypes.length; i ++) {
      Class argumentClass = paramTypes[i];
      if (argumentClass == Float.class || argumentClass == float.class) {
        argumentExtractors[i] = ARGUMENT_EXTRACTOR_FLOAT;
      } else if (argumentClass == float[].class) {
        argumentExtractors[i] = ARGUMENT_EXTRACTOR_FLOAT_LIST;
      } else if (argumentClass == int.class || argumentClass == Integer.class) {
        argumentExtractors[i] = ARGUMENT_EXTRACTOR_INTEGER;
      } else if (argumentClass == int[].class) {
        argumentExtractors[i] = ARGUMENT_EXTRACTOR_INTEGER_LIST;
      } else if (argumentClass == HashMap.class) {
        argumentExtractors[i] = ARGUMENT_EXTRACTOR_HASHMAP;
      } else {
        argumentExtractors[i] = new ArgumentExtractor();
      }
    }
    return argumentExtractors;
  }

  private static String paramTypeToString(Class typeClass) {
    if (typeClass == boolean.class) {
      return new String("boolean");
    } else if (typeClass == Boolean.class) {
      return new String("Boolean");
    } else if (typeClass == int.class) {
      return new String("int");
    } else if (typeClass == int[].class) {
      return new String("int[]");
    } else if (typeClass == Integer.class) {
      return new String("Integer");
    } else if (typeClass == double.class) {
      return new String("double");
    } else if (typeClass == Double.class) {
      return new String("Double");
    } else if (typeClass == float.class) {
      return new String("float");
    } else if (typeClass == float[].class) {
      return new String("float[]");
    } else if (typeClass == Float.class) {
      return new String("Float");
    } else if (typeClass == String.class) {
      return new String("String");
    } else if (typeClass == HashMap.class) {
      return new String("HashMap");
    } else {
      return new String("other");
    }
  }

  private final String TAG = "JavaMethodWrapper";
  private final Method mMethod;
  private final Class[] mParameterTypes;
  private String mMethodName;
  private ArgumentExtractor[] mArgumentExtractors;

  public JavaMethodWrapper(Method method) {
    mMethod = method;
    mMethod.setAccessible(true);
    mParameterTypes = mMethod.getParameterTypes();
    processArguments();
  }

  private void processArguments(){
    mMethodName = buildName(mParameterTypes);
    mArgumentExtractors = buildArgumentExtractors(mParameterTypes);
  }

  private String buildName(Class[] parameterTypes) {
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
    Object[] arguments = new Object[parameters.length];
    if (mArgumentExtractors == null) {
      throw new Error("processArguments failed");
    }
    for (int i = 0; i < mArgumentExtractors.length; i++) {
      arguments[i] = mArgumentExtractors[i].extractArgument(parameters, i);
    }

    try {
      mMethod.invoke(moduleClassInstance, arguments);
//      Log.i(TAG, "invoke " + mMethodName);
    } catch (IllegalArgumentException ie) {
      Log.w(TAG, "IllegalArgumentException: Could not invoke " + mMethodName);
      throw new RuntimeException("Could not invoke " + mMethodName, ie);
    } catch (IllegalAccessException iae) {
      Log.w(TAG, "IllegalAccessException: Could not invoke " + mMethodName);
      throw new RuntimeException("Could not invoke " + mMethodName, iae);
    } catch (InvocationTargetException ite) {
      Log.w(TAG, "InvocationTargetException: Could not invoke " + mMethodName);
      throw new RuntimeException("Could not invoke " + mMethodName, ite);
    }

  }

}
