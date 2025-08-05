package ru.otus.hw05;
import ru.otus.hw05.annotations.Log;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ReflectionMethod {

    private List<Method> testMethods;

    ReflectionMethod (Method[] list){
        this.testMethods= Arrays.stream(list).filter(x->x.isAnnotationPresent(Log.class)).collect(Collectors.toList());
    }

    List<Method> getTestMethods() {
        return testMethods;
    }
  }