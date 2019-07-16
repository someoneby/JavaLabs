package Concurrency;

public class Deadlock3 {

    private String name;

    public Deadlock3(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public synchronized void hello(Deadlock3 lock) {
        System.out.format("%s: %s" + "  has bowed to me!%n", this.name, lock.getName());
        lock.helloBack(this);
    }

    public synchronized void helloBack(Deadlock3 lock) {
        System.out.format("%s: %s"
                        + " has bowed back to me!%n",
                this.name, lock.getName());
    }

    public static void main(String[] args) {
        Deadlock3 deadlock = new Deadlock3("1");
        Deadlock3 deadlock2 = new Deadlock3("2");


        new Thread(() -> deadlock.hello(deadlock2)).start();
        new Thread(() -> deadlock2.hello(deadlock)).start();
    }

}

