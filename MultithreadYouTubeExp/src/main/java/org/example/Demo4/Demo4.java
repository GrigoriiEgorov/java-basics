package org.example.Demo4;

import javax.sound.midi.Soundbank;

public class Demo4 {
    public static void main(String[] args) throws Exception{
        Account account = new Account(0);
        System.out.println("Begin balance: " + account.getBalance());

        new DepositThread(account).start();

        System.out.println("Calling waitAndWithdraw ... ");
        account.waitAndWithdraw(20_000_000);

        System.out.println("waitAndWithdraw is finished!");
    }


    private static class DepositThread extends Thread{
        private final Account account;

        private DepositThread(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20_000_000; ++i){
                account.deposit(1);
            }
        }
    }

}
