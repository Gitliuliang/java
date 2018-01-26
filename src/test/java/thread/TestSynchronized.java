package thread;

/**
 * 根据synchronized的生产者消费者模型
 */
public class TestSynchronized {
    private int sum = 0;

    public synchronized void setSum() {
        try {
            Thread.sleep(200);
            while (sum >= 1) {
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + "------" + (++sum));
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void getSum() {
        try {
            Thread.sleep(200);
            while (sum == 0) {
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + "------" + (--sum));
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestSynchronized tsy = new TestSynchronized();
        OnSetSum onSetSum = new OnSetSum(tsy);
        OnGetSum onGetSum = new OnGetSum(tsy);
        new Thread(onSetSum, "生产者A").start();
        new Thread(onSetSum, "生产者B").start();
        new Thread(onGetSum, "消费者-----A").start();

    }

}

/**
 * 生产者
 */
class OnSetSum implements Runnable {
    private TestSynchronized tsh;

    public OnSetSum(TestSynchronized tsh) {
        this.tsh = tsh;
    }

    public void run() {
        for (int i = 0; i <= 20; i++) {
            tsh.setSum();
        }
    }
}

/**
 * 消费者
 */
class OnGetSum implements Runnable {
    private TestSynchronized tsh;

    public OnGetSum(TestSynchronized tsh) {
        this.tsh = tsh;
    }

    public void run() {
        for (int i = 0; i <= 40; i++) {
            tsh.getSum();
        }
    }
}


