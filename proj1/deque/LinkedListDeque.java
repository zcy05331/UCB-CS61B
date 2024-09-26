package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Deque<T> {
    private final Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        size += 1;
        Node newNode = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        if (sentinel.prev == null) {
            sentinel.prev = newNode;
        }
    }

    public void addLast(T item) {
        size += 1;
        Node newNode = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        if (sentinel.next == null) {
            sentinel.next = newNode;
        }
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        Node now = this.sentinel.next;
        while (now != this.sentinel) {
            System.out.print(now.item + " ");
            now = now.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        Node first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        return first.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        Node last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        return last.item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node now = this.sentinel.next;
        for (int i = 0; i < index; i++) {
            now = now.next;
        }
        return now.item;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node current;
        public LinkedListIterator() {
            current = sentinel.next;
        }
        public boolean hasNext() {
            return current != sentinel;
        }
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    public boolean equals(Object o) {
        if (o instanceof Deque) {
            Deque d = (Deque) o;
            if (size() != d.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!get(i).equals(d.get(i))) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    private T getRecursiveHelper(Node cur, int index) {
        if (index == 0) {
            return cur.item;
        }
        return getRecursiveHelper(cur.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node now = this.sentinel.next;
        return getRecursiveHelper(now, index);
    }

    private class Node {
        private final T item;
        private Node next;
        private Node prev;

        Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
