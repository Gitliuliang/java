package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 根据Lock锁的生产者消费者模型
 */
public class TestLock extends ReentrantLock {
    private int count = 0;
    private Condition cd1 = newCondition();
    private Condition cd2 = newCondition();

    public void setCount() {
        try {
            lock();
            Thread.sleep(200);
            while (count >= 1) {
                cd1.await();
            }
            System.out.println(Thread.currentThread().getName() + "-----" + (++count));
            cd2.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            unlock();
        }
    }

    public void getCount() {
        try {
            lock();
            Thread.sleep(200);
            while (count == 0) {
                cd2.await();
            }
            System.out.println(Thread.currentThread().getName() + "-----" + (--count));
            cd1.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            unlock();
        }
    }

    public static void main(String[] args) {
        TestLock testLock = new TestLock();
        OnSetCount onSetCount = new OnSetCount(testLock);
        OnGetCount onGetCount = new OnGetCount(testLock);
        new Thread(onSetCount, "生产者A").start();
        new Thread(onSetCount, "生产者B").start();
        new Thread(onGetCount, "消费者----A").start();
    }
}

/**
 * 生产者
 */
class OnSetCount implements Runnable {
    private TestLock testLock;

    public OnSetCount(TestLock testLock) {
        this.testLock = testLock;
    }

    public void run() {
        for (int i = 0; i <= 20; i++) {
            testLock.setCount();
        }
    }
}

/**
 * 消费者
 */
class OnGetCount implements Runnable {
    private TestLock testLock;

    public OnGetCount(TestLock testLock) {
        this.testLock = testLock;
    }

    public void run() {
        for (int i = 0; i <= 40; i++) {
            testLock.getCount();
        }
    }
}