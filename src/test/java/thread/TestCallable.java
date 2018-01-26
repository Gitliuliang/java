package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 一、创建执行线程的方式三：实现 Callable 接口。 相较于实现 Runnable 接口的方式，方法可以有返回值，并且可以抛出异常。
 * <p>
 * 二、执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。  FutureTask 是  Future 接口的实现类
 */
public class TestCallable implements Callable<Integer> {

    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 10000; i++) {
            System.out.println(i);
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable testCallable = new TestCallable();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(testCallable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
        System.out.println("-------------线程运行的过程中主线程不执行，很闭锁效果一样");

    }
}
