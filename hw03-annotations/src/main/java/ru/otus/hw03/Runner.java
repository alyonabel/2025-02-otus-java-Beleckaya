package ru.otus.hw03;

import ru.otus.hw03.reflection.TestFramework;

public class Runner {
    public static void main(String[] args)  {


       TestFramework demo = new TestFramework();
       int [] result =  demo.run(DemoTest.class);
        printStatistic(result[0],result[1]);

    }
    private static void printStatistic(int executed, int failled) {

        int count = failled + executed;
        System.out.println("\n" + "Count of existing = " + count + "\n" +  "Count of executed = " + executed + "\n" +  "Count of errors = " + failled);

    }

    }


