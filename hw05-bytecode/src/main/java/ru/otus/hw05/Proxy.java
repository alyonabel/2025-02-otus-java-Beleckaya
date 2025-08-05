package ru.otus.hw05;

public class Proxy {
    public static void main(String [] args) throws NullPointerException{

        MyInterface myClass =  Ioc.showAnnotation();
        myClass.checkPerson("James Bond");
     }
}
