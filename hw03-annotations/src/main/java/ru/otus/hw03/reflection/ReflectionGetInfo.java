package ru.otus.hw03.reflection;

import ru.otus.hw03.DemoTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public  class ReflectionGetInfo {
    public static void main(String[] args) throws NoSuchMethodException{

        Class<DemoTest> clazz = DemoTest.class;
        System.out.println("Class Name: " + clazz.getSimpleName());

        Constructor<?> [] constructors = clazz.getConstructors();
        System.out.println("Constructors:");
        System.out.println(Arrays.toString(constructors));

        Method [] methodsPublic = clazz.getMethods();
        System.out.println("Public methods:");
        Arrays.stream(methodsPublic).forEach(method -> System.out.println(method.getName()));

        Method [] methodsAll= clazz.getDeclaredMethods();
        System.out.println("All methods:");
        Arrays.stream(methodsAll).forEach(method -> System.out.println(method.getName()));


        System.out.println("All fields:");
        Field[] fieldsAll = clazz.getDeclaredFields();
        Arrays.stream(fieldsAll).forEach(field -> System.out.println(field.getName()));


        System.out.println("Annotations:");
        Method annotatedMethod = clazz.getMethod("toString");
        Annotation[] annotations = annotatedMethod.getDeclaredAnnotations();
        System.out.println(Arrays.toString(annotations));

    }

}
