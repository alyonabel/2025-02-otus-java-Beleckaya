package ru.otus.hw05;

import java.lang.reflect.*;
import java.lang.reflect.Proxy;
import java.util.Arrays;

class Ioc {
    private Ioc() {};

    static MyInterface showAnnotation() {

        ClassLoader classLoader = MyClass.class.getClassLoader();
        Class<?>[] interfaces = MyClass.class.getInterfaces();
        LoggingInvocationHandler invocationHandler = new LoggingInvocationHandler(new MyClass());
        return (MyInterface) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }

    static class LoggingInvocationHandler implements InvocationHandler {

        private final MyInterface myInterface;

        LoggingInvocationHandler(MyInterface myInterface) {
            this.myInterface = myInterface;

        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
           ReflectionMethod reflectionMethods = new ReflectionMethod(MyClass.class.getMethods());
            for (Method m : reflectionMethods.getTestMethods()) {
                System.out.println("Executed method: " + method.getName() + ", param:" +  Arrays.toString(args));
            }
           return method.invoke(myInterface, args);
        }
    }
}



