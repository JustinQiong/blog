package org.j.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 自旋锁简单实现
 *
 * @author 周琼
 * @since 2022-02-12 10:08
 **/
public class SimpleLock implements SpinLock {

    private AtomicBoolean flag = new AtomicBoolean();

    @Override
    public void lock() {
        while (!flag.compareAndSet(false, true)) {
            // 自旋
        }
    }

    @Override
    public void unlock() {
        flag.set(false);
    }
}
