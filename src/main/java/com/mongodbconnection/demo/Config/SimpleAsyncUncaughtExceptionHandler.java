package com.mongodbconnection.demo.Config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class SimpleAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        System.out.println("Method Name::"+method.getName());
        System.out.println("Exception occurred::"+ throwable);
    }
}
