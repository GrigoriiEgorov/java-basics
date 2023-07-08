package org.example.standartLibraryEXP.demo6;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelExecutorService {

    public static void main(String[] args) throws Exception{
        int[] array = Commons.prepareArray();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        long startTime = System.currentTimeMillis();

        Future<Double> future1 = executorService.submit(
                new PartialCalc(array, 0, array.length / 2));
        Future<Double> future2 = executorService.submit(
                new PartialCalc(array, array.length / 2, array.length));

        double sum = future1.get() + future2.get();

        long endTime = System.currentTimeMillis();

        System.out.println("sum = " + sum);
        System.out.println("time = " + (endTime - startTime) + " ms");
    }

    private static class PartialCalc implements Callable<Double> {

        private final int[] array;
        private final int start;
        private final int end;

        public PartialCalc(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        public Double call() throws Exception {
            return Commons.calculate(array, start, end);
        }
    }

}
