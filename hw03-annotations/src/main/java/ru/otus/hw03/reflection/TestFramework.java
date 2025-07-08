package ru.otus.hw03.reflection;


import java.lang.reflect.Method;
import java.util.List;

public class TestFramework {

    public int[] run(Class<?> clazz) {
        ReflectionMethod reflectionMethods = new ReflectionMethod(clazz.getMethods());
        int executed = 0;
        for (Method test : reflectionMethods.getTestMethods()) {
            printMethodName(test.getName());
            Object testsClassInstance=null;
            try {
                testsClassInstance = clazz.getDeclaredConstructor().newInstance();
                if (!runMethod(reflectionMethods.getBeforeMethods(), testsClassInstance)) {
                    break;
                }
                test.invoke(testsClassInstance);
                executed++;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                runMethod(reflectionMethods.getAfterMethods(), testsClassInstance);
            }
        }

       int[] myArray = {executed, reflectionMethods.getTestMethods().size() - executed};
       return  myArray;

    }



    private static void printMethodName(String testName) {
        System.out.println(testName + "\n");
    }

    private static boolean runMethod(List<Method> methodList, Object testsClassInstance) {
        for (Method method : methodList) {
            try {
                method.invoke(testsClassInstance);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

}