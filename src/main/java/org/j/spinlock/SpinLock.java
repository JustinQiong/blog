package org.j.spinlock;

/**
 * 自旋锁接口
 *
 * @author 周琼
 * @since 2022-02-12 10:08
 **/
public interface SpinLock {

    void lock();

    void unlock();
    
}
