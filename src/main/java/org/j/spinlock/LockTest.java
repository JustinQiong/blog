package org.j.spinlock;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 锁测试类
 *
 * @author 周琼
 * @since 2022-02-12 10:32
 **/
public class LockTest {

    private static final int NUM = 1000;
    private volatile int count = 0;

    ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
            10,
            30,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(NUM));

    @Test
    public void testSimpleLock() throws InterruptedException {
        count = 0;
        SpinLock lock = new SimpleLock();
        testLock(lock);
    }

    @Test
    public void testTickLock() throws InterruptedException {
        count = 0;
        SpinLock lock = new TicketLock();
        testLock(lock);
    }

    @Test
    public void testClhLock() throws InterruptedException {
        count = 0;
        SpinLock lock = new ClhLock();
        testLock(lock);
    }

    @Test
    public void testMcsLock() throws InterruptedException {
        count = 0;
        SpinLock lock = new McsLock();
        testLock(lock);
    }

    private void testLock(SpinLock lock) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(NUM);
        for (int i=0; i< NUM; i++) {
            executor.execute(() -> {
                lock.lock();
                ++count;
                lock.unlock();
                latch.countDown();
            });
        }
        latch.await();
        Assert.assertEquals(NUM, count);
    }
    
}
