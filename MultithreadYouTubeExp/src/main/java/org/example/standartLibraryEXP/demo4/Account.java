package org.example.standartLibraryEXP.demo4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private final Lock lock = new ReentrantLock();//true для составления справедливой очереди
    private final Condition balanceIncrease = lock.newCondition();

    private long balance;

    public Account() {
        this(0L);
    }

    public Account(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public void deposit(long amount) {
        checkAmountNonNegative(amount);
        lock.lock();
        try {
            balance += amount;
            balanceIncrease.signalAll();//главное не перепутать с notify
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(long amount) {
        checkAmountNonNegative(amount);
        lock.lock();
        try {
            if (amount > balance) {
                throw new IllegalArgumentException("not enough money");
            }
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    public void waitAndWithdraw(long amount) throws InterruptedException{
        checkAmountNonNegative(amount);
        lock.lock();
        try{
            while(amount > balance){
                balanceIncrease.await();//не путать с wait()
            }
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }
    private static void checkAmountNonNegative(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("negative amount");
        }
    }

}
