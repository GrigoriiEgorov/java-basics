package org.example.standartLibraryEXP.demo41;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();

        lock.readLock().lock();
        try{
            //readOnlyOperation();
        }finally {
            lock.readLock().unlock();
        }

        lock.writeLock().lock();
        try {
            //writeOnlyOperation();
        }finally {
            lock.writeLock().unlock();
        }

    }
}
