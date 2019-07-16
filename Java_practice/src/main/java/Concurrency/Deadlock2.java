package Concurrency;

import java.util.concurrent.Semaphore;

public class Deadlock2 {

    private String name;

    public Deadlock2(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public synchronized void hello(Deadlock2 lock) {
        System.out.format("%s: %s" + "  has bowed to me!%n", this.name, lock.getName());
        lock.helloBack(this);
    }

    public synchronized void helloBack(Deadlock2 lock) {
        System.out.format("%s: %s"
                        + " has bowed back to me!%n",
                this.name, lock.getName());
    }

    public static void main(String[] args) {
        Deadlock2 deadlock = new Deadlock2("1");
        Deadlock2 deadlock2 = new Deadlock2("2");
        Semaphore sem = new Semaphore(2,true);

        new Thread(() -> deadlock.hello(deadlock2)).start();
        new Thread(() -> deadlock2.hello(deadlock)).start();
    }

}

