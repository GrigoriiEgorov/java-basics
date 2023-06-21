package org.example.Demo3;

public class Demo3 {
    public static void main(String[] args) throws Exception{
        Account account = new Account(100_000);
        System.out.println("Begin balance: " + account.getBalance());

        Thread withdrawThread = new WithdrawThread(account);
        Thread depositThread = new DepositThread(account);

        withdrawThread.start();
        depositThread.start();

        withdrawThread.join();
        depositThread.join();

        System.out.println("End balance: " + account.getBalance());
    }

    private static class WithdrawThread extends Thread{
        private final Account account;

        private WithdrawThread(Account account){
            this.account = account;
        }

        public void run(){
            for(int i = 0; i < 20_000; ++i){
                account.withdraw(1);
            }
        }
    }

    private static class DepositThread extends Thread{
        private final Account account;

        private DepositThread(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20_000; ++i){
                account.deposit(1);
            }
        }
    }

}
