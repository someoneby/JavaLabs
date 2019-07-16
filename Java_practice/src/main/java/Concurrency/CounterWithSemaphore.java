package Concurrency;

import java.util.concurrent.Semaphore;

public class CounterWithSemaphore implements Runnable{
    private int counter;
    Semaphore sem;

    public CounterWithSemaphore(Semaphore sem) {
        this.sem = sem;
    }

    public void run() {
        for(int i=0;i<20;i++){
            try {
                sem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
            sem.release();
        }
        System.out.println(" "+counter);
    }
}
