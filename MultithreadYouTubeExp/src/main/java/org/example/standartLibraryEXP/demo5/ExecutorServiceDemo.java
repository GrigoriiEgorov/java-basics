package org.example.standartLibraryEXP.demo5;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceDemo {
    public static void main(String[] args) throws Exception{
        ExecutorService executor = Executors.newFixedThreadPool(2);
        System.out.println("");

        Future<String> future1 = executor.submit(new Worker("worker 1"));

        Future<String> future2 = executor.submit(new Worker("worker 2"));


        System.out.println("Result from worker 1: " + future1.get());
        System.out.println("Result from worker 2: " + future2.get());

        System.out.println("---------------------");

        System.out.println("Submit workers using invokeAll()");

        List<Future<String>> futures = executor.invokeAll(//он отправляет на работу сразу пачку, при этом он гарантирует, что дождётся всех троих
                Arrays.asList(new Worker("worker 3"),new Worker("worker 4"), new Worker("worker 5")));

        System.out.println("Exited invokeAll()");

        for(Future<String> future : futures){
            System.out.println("Result from worker: " + future.get());
        }

        executor.shutdown();
        executor.awaitTermination(10L, TimeUnit.SECONDS);

    }

    private static class Worker implements Callable<String> {

        private final String name;

        public Worker(String name) {
            this.name = name;
        }


        @Override
        public String call() throws Exception {
            long sleepTime = (long) (Math.random() * 10_000L);
            System.out.println(name + "start sleeping for " + sleepTime);
            Thread.sleep(sleepTime);
            System.out.println(name + " finished");
            return name;
        }
    }
}
