package ru.otus.hw03;


import ru.otus.hw03.annotations.After;
import ru.otus.hw03.annotations.Before;
import ru.otus.hw03.annotations.Test;

import java.util.Random;

public class DemoTest {


    private String value= "RandomInt";
    private Random random = new Random();
    private int value2 = random.nextInt();

    private String getValue() {
        return value;
    }
    private int getValue2() {
        return value2;
    }


    public void setValue(String value) {
        this.value = value;
    }
    public void setValue2(int value2) {
        this.value2 = value2;
    }

    @Before
    public void introduce(){
        System.out.println("Start!");
    }


    @Test
    public void show () {
        System.out.println("DemoTest shows further:" +  getValue() + "=" + getValue2());
    }

    @After
    public void allMethods() {
        System.out.println("AllMethods executed. The end!");
    }

}

