package org.j.spinlock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Ticket自旋锁
 *
 * @author 周琼
 * @since 2022-02-12 14:18
 **/
public class TicketLock implements SpinLock {

    private AtomicInteger ticket = new AtomicInteger();
    private AtomicInteger call = new AtomicInteger();

    @Override
    public void lock() {
        int tick = ticket.getAndIncrement(); // 取号
        while (tick != call.get()) { // 检查叫号和取的号是否相等
            // 自旋
        }
    }

    @Override
    public void unlock() {
        call.getAndIncrement();
    }
}
