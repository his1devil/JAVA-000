import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author qiujun
 * @Version @version $Id: ThreadHomeWork, v0.1 2020-11-11 11:10 上午 qiujun Exp $
 * @Description
 */
public class ThreadHomeWork {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        // single thread or thread pool
        // run below async
        //int result = sum();

        // completablefuture
        //int result = CompletableFuture.supplyAsync(ThreadHomeWork::sum).join();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        Future<Integer> task = completionService.submit(ThreadHomeWork::sum);

        // 确保拿到result输出
        System.out.println("异步计算结果为: " + task.get());

        System.out.println("运行时间: " + (System.currentTimeMillis() - start) + "ms");
        executorService.shutdown();
    }

    /**
     * CountDownLatch
     * @return
     * @throws Exception
     */
    private int get() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicInteger result = new AtomicInteger();
        new Thread(() -> {
            result.set(sum());
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
        return result.get();
    }

    private int getForkJoin() throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool(1);
        ForkJoinTask<Integer> result = forkJoinPool.submit(ThreadHomeWork::sum);
        int count = result.get();
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.SECONDS);
        return result.get();
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    //public static void main(String[] args) {
    //
    //    long start=System.currentTimeMillis();
    //    // 在这里创建一个线程或线程池，
    //    // 异步执行 下面方法
    //
    //    int result = sum(); //这是得到的返回值
    //
    //    // 确保  拿到result 并输出
    //    System.out.println("异步计算结果为："+result);
    //
    //    System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    //
    //    // 然后退出main线程
    //}
    //
    //private static int sum() {
    //    return fibo(36);
    //}
    //
    //private static int fibo(int a) {
    //    if ( a < 2)
    //        return 1;
    //    return fibo(a-1) + fibo(a-2);
    //}
}
