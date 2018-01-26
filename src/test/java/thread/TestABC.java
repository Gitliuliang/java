package thread;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程按序交替
 *  编写一个程序，开启 3 个线程，这三个线程的 ID 分别为
 * A、B、C，每个线程将自己的 ID 在屏幕上打印 20 遍，要
 * 求输出的结果必须按顺序显示。
 */
public class TestABC {
    private static int nu = 1;
    private ReentrantLock lock = new ReentrantLock();
    private Condition cnA = lock.newCondition();
    private Condition cnB = lock.newCondition();
    private Condition cnC = lock.newCondition();

    public void printA() {
        try {
            lock.lock();
            Thread.sleep(200);
            while (nu != 1) {
                cnA.await();
            }
            System.out.println(Thread.currentThread().getName() + "----" + "A");
            nu = 2;
            cnB.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        try {
            lock.lock();
            Thread.sleep(200);
            while (nu != 2) {
                cnB.await();
            }
            System.out.println(Thread.currentThread().getName() + "----" + "B");
            nu = 3;
            cnC.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        try {
            lock.lock();
            Thread.sleep(200);
            while (nu != 3) {
                cnC.await();
            }
            System.out.println(Thread.currentThread().getName() + "----" + "C");
            nu = 1;
            cnA.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        final TestABC testABC = new TestABC();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    testABC.printA();
                }
            }
        }, "打印A").start();


        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    testABC.printB();
                }
            }
        }, "打印B").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    testABC.printC();
                }
            }
        }, "打印C").start();
    }

}

