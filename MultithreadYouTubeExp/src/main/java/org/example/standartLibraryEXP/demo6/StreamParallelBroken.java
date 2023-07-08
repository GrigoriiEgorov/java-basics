package org.example.standartLibraryEXP.demo6;

import java.util.Arrays;
import java.util.concurrent.atomic.DoubleAdder;

public class StreamParallelBroken {
    public static void main(String[] args) {
        int[] array = Commons.prepareArray();

        long startTime = System.currentTimeMillis();


        //double [] sum = new double[1];//массив из одной переменной, потому что иначе не дали бы доступ из стрима
        //в лямбдах можно использовать только финальные переменные
        //неправильное решение, так как мы никак не синхрогизируем доступ к общей памяти операция += не атомарна


//        Arrays.stream(array)
//                .parallel()
//                .mapToDouble(Commons::function)
//                .forEach(x -> sum[0] += x);

        DoubleAdder sum = new DoubleAdder();

        Arrays.stream(array)
                .parallel()
                .mapToDouble(Commons::function)
                .forEach(sum::add);

        long endTime = System.currentTimeMillis();

        System.out.println("sum = " + sum.doubleValue());
        System.out.println("time = " + (endTime - startTime) + " ms");
    }
}
