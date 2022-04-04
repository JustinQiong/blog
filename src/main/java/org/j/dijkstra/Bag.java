package org.j.dijkstra;

import java.util.Iterator;

/**
 * 背包数据结构
 *
 * @author 周琼
 * @since 2022-04-04 15:28
 **/
public class Bag<T> implements Iterable<T> {

    private Node first;
    private int size;

    public Bag() {
    }

    public void add(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    public boolean isEmpty () {
        return first == null;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class Node {
        T item;
        Node next;
    }

    private class ListIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }
    
}
