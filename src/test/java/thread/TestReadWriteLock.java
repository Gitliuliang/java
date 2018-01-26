package thread;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {
    private int nu = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void get() {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "--------" + nu);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void set(int nu) {
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "--------" + nu);
            this.nu = nu;
        } finally {
            lock.writeLock().unlock();
        }
    }


    public static void main(String[] args) {
        final TestReadWriteLock lock = new TestReadWriteLock();
        new Thread(new Runnable() {
            public void run() {
                lock.set((int)(Math.random()*100));
            }
        }, "写").start();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                public void run() {
                    lock.get();
                }
            }).start();
        }

    }
}

