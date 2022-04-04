package org.j.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Clh自旋锁
 *
 * @author 周琼
 * @since 2022-02-12 15:18
 **/
public class ClhLock implements SpinLock {

    private AtomicReference<Node> tail;
    private ThreadLocal<Node> node;
    private ThreadLocal<Node> pre;

    public ClhLock() {
        tail = new AtomicReference<>(new Node());
        node = ThreadLocal.withInitial(Node::new);
        pre = new ThreadLocal<>();
    }

    @Override
    public void lock() {
        Node curr = new Node(); //新增node节点
        curr.flag = true; //节点设置为待获取锁状态
        node.set(curr);
        Node pr = tail.getAndSet(curr); //读取当前节点的前置节点并将当前节点链接到tail后
        pre.set(pr);
        while (pr.flag) { //检查前置节点的锁状态
            //自旋
        }
    }

    @Override
    public void unlock() {
        Node curr = node.get();
        curr.flag = false; //当前节点置为未持有锁状态
        pre.set(null); // 解除前置节点与当前节点的链接
    }

    private static class Node {
        private volatile boolean flag = false; //true=待获取锁或持有锁状态，false=未持有锁状态
    }
}
