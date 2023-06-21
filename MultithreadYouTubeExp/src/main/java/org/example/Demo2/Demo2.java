package org.example.Demo2;

class Demo2 {
    public static void main(String[] args) throws Exception {
        Thread worker = new WorkerThread();
        worker.setDaemon(true);
        //обозаначив демонами, основной поток их ждать не будет, и виртуальная машина их просто прибъёт
        //при условии того, что мы не вызовем методы интерапт и джоин.
        //если не обозначить их демонами, то будет висеть вечно, пока не выполнится метод ран
        Thread sleeper = new SleeperThread();
        sleeper.setDaemon(true);

        System.out.println("Starting thread");
        worker.start();
        sleeper.start();

        Thread.sleep(100L);

//        System.out.println("Interrupted threads");
//        worker.interrupt();
//        sleeper.interrupt();
//
//        System.out.println("Joining threads");
//        worker.join();
//        sleeper.join();

        System.out.println("All done");

    }

    private static class WorkerThread extends Thread {
        @Override
        public void run() {
            long sum = 0;
            for (int i = 0; i < 1_000_000_000; i++){
                sum += i;
                if(sum % 100 == 0 && isInterrupted()){
                    System.out.println(sum%100);
                    System.out.println("Loop interrupted at sum = " + sum);
                    break;
                }
            }
        }
    }

    private static class SleeperThread extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(10_000L);
            }catch (InterruptedException e){
                System.out.println("Sleep interrupted");
            }
        }
    }
}
