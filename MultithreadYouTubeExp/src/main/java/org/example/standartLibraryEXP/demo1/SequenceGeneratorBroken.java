package org.example.standartLibraryEXP.demo1;

import java.util.ArrayList;
import java.util.List;

public class SequenceGeneratorBroken {

    private static volatile int counter = 0;

    public static int nextInt() {
        return counter++;
    }

    public static void main(String[] args) throws Exception {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {//в конструктор передаём Runnable
                //Runnable конструируем через лямбда выражения
                for (int j = 0; j < 1000; j++) {
                    nextInt();
                }
            });
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads){
            thread.join();
        }

        System.out.println("Counter final value: " + counter);
    }
}