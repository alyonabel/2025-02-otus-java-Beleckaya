package ru.otus.hw05;
import ru.otus.hw05.annotations.Log;

public class MyClass implements MyInterface {

    @Log
    public void checkPerson(String name) {
        System.out.println("The person " + name + " is under suspicion" );
    };

    @Override
    public String  toString(){
        return "MyInterface{}";
    };
    }
