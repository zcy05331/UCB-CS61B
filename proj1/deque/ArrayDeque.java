package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }

    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[getIndex(i)];
        }
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        size += 1;
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        size += 1;
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[getIndex(i)] + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size < items.length / 4) {
            resize(items.length / 2);
        }
        T first = items[getIndex(0)];
        items[getIndex(0)] = null;
        nextFirst = getIndex(0);
        size -= 1;
        return first;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size < items.length / 4) {
            resize(items.length / 2);
        }
        T last = items[getIndex(size - 1)];
        items[getIndex(size - 1)] = null;
        nextLast = getIndex(size - 1);
        size -= 1;
        return last;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[getIndex(index)];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index = 0;
        public boolean hasNext() {
            return index < size;
        }
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = get(index);
            index = index + 1;
            return item;
        }
    }

    private int getIndex(int index) {
        return (index + nextFirst + 1) % items.length;
    }

    @Override
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
}
