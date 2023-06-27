package org.example.standartLibraryEXP.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SequenceGeneratorGood2 {

    private static final AtomicInteger counter = new AtomicInteger();//по умолчанию 0, но можно задать значение

    public static int nextInt() {
        return counter.getAndIncrement();
    }

    public static void main(String[] args) throws Exception {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
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