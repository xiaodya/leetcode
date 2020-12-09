package com.company;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadStatusTest {
    static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args){
        Thread thread1 = new Thread(new Work("thread1"));
        Thread thread2 = new Thread(new Work("thread2"));

        thread1.start();
        thread2.start();
        try{
            Thread.sleep(1);
        } catch (Exception ex){

        }
        System.out.println("Thread1 State:"+thread1.getState().toString());
        System.out.println("Thread2 State:"+thread2.getState().toString());
    }

    static class Work implements Runnable{
        private String name;
        public Work(String name){
            this.name = name;
        }

        @Override
        public void run(){
            try {
                System.out.println(name + " run");
                lock.lock();
                Thread.sleep(1*1000);
                System.out.println(name + "Teminated");
            } catch (Exception ex){

            }
            finally{
                lock.unlock();
            }
        }
    }

    static class SyncWork implements Runnable{
        private String name;
        public SyncWork(String name){
            this.name = name;
        }

        @Override
        public void run(){
            synchronized (SyncWork.class) {
                try {

                    System.out.println(name + " run");
                    Thread.sleep(1*1000);
                    System.out.println(name + " Teminated");

                } catch (Exception ex) {

                }
            }
        }
    }
}
