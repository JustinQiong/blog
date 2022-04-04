package org.j.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Mcs自旋锁
 *
 * @author 周琼
 * @since 2022-02-12 16:41
 **/
public class McsLock implements SpinLock {

    private AtomicReference<Node> tail;
    private ThreadLocal<Node> node;

    public McsLock() {
        tail = new AtomicReference<>();
        node = ThreadLocal.withInitial(Node::new);
    }

    @Override
    public void lock() {
        Node curr = node.get();
        Node pre = tail.getAndSet(curr);
        if (pre != null) { //没有前置节点则当前
            pre.next = curr;
            while (!curr.flag) { //检查当前节点的锁状态
                //自旋
            }
        }
    }

    @Override
    public void unlock() {
        Node curr = node.get();
        if (curr.next == null) { //如果当前节点是尾结点，则将尾结点置为null
            if (tail.compareAndSet(curr, null)) { //由于多线程执行，所以再次检查当前节点是否是尾节点
                return;
            }
            while (curr.next == null){ //说明当前节点后又新增了节点

            }
        }
        curr.next.flag = true; //将当前节点的后置节点锁状态置为true
        curr.next = null; //断开当前节点和后置节点的连接
    }

    private class Node {
        public volatile boolean flag = false;
        public volatile Node next;
    }

}
