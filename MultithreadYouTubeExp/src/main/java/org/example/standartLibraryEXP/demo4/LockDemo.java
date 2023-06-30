package org.example.standartLibraryEXP.demo4;

public class LockDemo {
    public static void main(String[] args) throws Exception{
        Account account = new Account(0);

        new DepositThread(account).start();

        System.out.println("Entering in waitAndWithdraw");
        account.waitAndWithdraw(500_000_000);
        System.out.println("waitAndWithdraw and, balance: " + account.getBalance());
    }


    private static class DepositThread extends Thread{
        private final Account account;

        public DepositThread(Account account){
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 600_000_000; i++) {
                account.deposit(1);
            }
        }
    }
}
