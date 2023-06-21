package org.example;

public class Main {

    public static void main(String[] args) {

//        for (int i = 0; i < 10; i++) {
//            new HelloThread().start();
//        }

//        for(int i = 0; i < 10; i++){
//            new Thread(new HelloRunnable()).start();
//        }

        //Если простое выражение, то можно просто лямбду использовать, а не декларировать отдельный класс
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println("Hello from ")).start();
        }

        //Если не происходит работы с памятью и состоянием, что можно сделать общий Runnable
        HelloRunnable helloRunnable = new HelloRunnable();

        for (int i = 0; i < 10; i++) {
            new Thread(helloRunnable).start();
        }

//        Если вызвать метод run() то всё будет происходить в главном потоке, и никакой многопоточности не будет

        System.out.println("Hello world from main thread");
    }

    private static class HelloThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello thread name: " + getName());
        }
    }

    private static class HelloRunnable implements Runnable {

        public void run() {
            System.out.println("Hello from thread: " + Thread.currentThread().getName());
        }
    }
}

