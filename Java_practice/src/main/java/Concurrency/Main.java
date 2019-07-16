package Concurrency;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {

/** Разрешить deadlock в /.payload/java8/multithreading/Deadlock.java всеми способами, которые вы знаете */
        System.out.println("Разрешить deadlock в /.payload/java8/multithreading/Deadlock.java всеми способами, которые вы знаете");
        Deadlock1.main(new String[]{});


        /** Реализовать обмен данными между потоками через семафор */
        System.out.println("\nРеализовать обмен данными между потоками через семафор");

        Semaphore sem = new Semaphore(2,true);
        CounterWithSemaphore counterWithSemaphore1 = new CounterWithSemaphore(sem);
        CounterWithSemaphore counterWithSemaphore2 = new CounterWithSemaphore(sem);

        new Thread(counterWithSemaphore1).start();
        new Thread(counterWithSemaphore2).start();











    }
}
