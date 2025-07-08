package ru.otus.hw03.reflection;



import ru.otus.hw03.annotations.Test;
import ru.otus.hw03.annotations.Before;
import ru.otus.hw03.annotations.After;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ReflectionMethod {

     private List<Method> testMethods;
     private List<Method> beforeMethods;
     private List<Method> afterMethods;


    ReflectionMethod(Method[] list){
        this.testMethods= Arrays.stream(list).filter(x->x.isAnnotationPresent(Test.class)).collect(Collectors.toList());
        this.beforeMethods=Arrays.stream(list).filter(x->x.isAnnotationPresent(Before.class)).collect(Collectors.toList());
        this.afterMethods=Arrays.stream(list).filter(x->x.isAnnotationPresent(After.class)).collect(Collectors.toList());

    }


    public List<Method> getTestMethods() {
        return testMethods;
    }

    public List<Method> getBeforeMethods() {
        return beforeMethods;
    }

    public List<Method> getAfterMethods() {
        return afterMethods;
    }



    }

