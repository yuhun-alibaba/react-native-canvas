package com.example.sam.graphic.canvas;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by sam on 2018/11/21.
 */

public class JavaMethodWrapper {

    private final Method mMethod;
    private final JavaModuleWrapper mModuleWrapper;

    public JavaMethodWrapper(JavaModuleWrapper module, Method method) {
        mModuleWrapper = module;
        mMethod = method;
        mMethod.setAccessible(true);
    }

    public String getName(){
        return mMethod.getName();
    }

    public void invoke(Object moduleClassInstance, Object[] parameters) {
        String traceName = mModuleWrapper.getName() + "." + mMethod.getName();

        try {
            try {
                mMethod.invoke(moduleClassInstance, parameters);
            } catch (IllegalArgumentException ie) {
                throw new RuntimeException("Could not invoke " + traceName, ie);
            } catch (IllegalAccessException iae) {
                throw new RuntimeException("Could not invoke " + traceName, iae);
            } catch (InvocationTargetException ite) {
                // Exceptions thrown from native module calls end up wrapped in InvocationTargetException
                // which just make traces harder to read and bump out useful information
                if (ite.getCause() instanceof RuntimeException) {
                    throw (RuntimeException) ite.getCause();
                }
                throw new RuntimeException("Could not invoke " + traceName, ite);
            }
        } finally {

        }
    }


}
