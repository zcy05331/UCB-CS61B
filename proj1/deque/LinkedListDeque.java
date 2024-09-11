package deque;

import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements deque<T> {
    private class node {
        private T item;
        private node next;
        private node prev;
        public node(T item, node next, node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    private node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        size += 1;
        node newNode = new node(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        if(sentinel.prev == null)
            sentinel.prev = newNode;
    }

    public void addLast(T item) {
        size += 1;
        node newNode = new node(item, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        if(sentinel.next == null)
            sentinel.next = newNode;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        node now = this.sentinel.next;
        while (now != this.sentinel) {
            System.out.print(now.item + " ");
            now = now.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if(isEmpty())
            return null;
        size -= 1;
        node first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        return first.item;
    }

    public T removeLast() {
        if(isEmpty())
            return null;
        size -= 1;
        node last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        return last.item;
    }

    public T get(int index) {
        if(index < 0 || index >= size)
            return null;
        node now = this.sentinel.next;
        for (int i = 0; i < index; i++)
            now = now.next;
        return now.item;
    }
}
