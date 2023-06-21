package org.example.Demo3;

public class Account {

    private long balance;

    public Account(){
        this(0L);
    }

    public Account(long balance){
        this.balance = balance;
    }

    public long getBalance(){
        return balance;
    }

    public synchronized void deposit(long amount){
        checkAmountNonNegative(amount);
        balance += amount;
        /*
        операция инкримента и декримента не атомарны(не выполняются в одно действие, потому не потокобезопасны
        Инкремента состоит из трех действий, взять из памяти значение в регистр процессора, там его
        прибавить, и обратно записать в память
         */
    }

    public void withdraw (long amount){
        checkAmountNonNegative(amount);

        /*
        рекомендуется в блок synchronized запизивать только ту часть кода, которая действительно того требует
        дабы увеличить прирост производительности
         */

        synchronized (this){
            if(amount > balance){
                throw new IllegalArgumentException("not enough money");
            }
            balance -= amount;
        }
    }

    static public void checkAmountNonNegative(long amount){
        if(amount < 0){
            throw new IllegalArgumentException("negative amount");
        }
    }
}
