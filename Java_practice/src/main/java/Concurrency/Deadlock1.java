package Concurrency;

public class Deadlock1 {

    private String name;

    public Deadlock1(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public synchronized void hello(Deadlock1 lock) {
        System.out.format(" %s: %s" + "  has bowed to me!%n", this.name, lock.getName());
        lock.helloBack(this);
    }

    public synchronized void helloBack(Deadlock1 lock) {
        System.out.format(" %s: %s"
                        + " has bowed back to me!%n",
                this.name, lock.getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Deadlock1 deadlock = new Deadlock1("1");
        Deadlock1 deadlock2 = new Deadlock1("2");


        Thread thread1 = new Thread(() -> deadlock.hello(deadlock2));
        thread1.start();
        thread1.join();
        Thread thread2 = new Thread(() -> deadlock.hello(deadlock2));
        thread2.start();
        thread2.join();
    }

}

