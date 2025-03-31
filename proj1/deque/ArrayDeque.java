package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private int size;
    private int nextfirst;
    private int nextlast;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextfirst = items.length / 2;
        nextlast = nextfirst + 1;
    }

    @Override
    public void addFirst(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        //是否需要扩容
        if (size >= items.length) {
            resize(size * 2);
        }
        items[nextfirst] = item;
        //是否越界
        if (nextfirst == 0) {
            nextfirst = items.length - 1;
        } else {
            nextfirst--;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        //是否需要扩容
        if (size >= items.length) {
            resize(size * 2);
        }
        items[nextlast] = item;
        nextlast = (nextlast + 1) % items.length;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int i = nextfirst;
        while (nextlast != i + 1) {
            i = (i + 1) % items.length;
            System.out.println(items[i] + " ");

        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T temp = get(0);
        nextfirst = (nextfirst + 1) % items.length;
        size--;
        if (items.length >= 16) {
            if (size < items.length / 4 && size > 0) {
                resize(items.length / 2);
            }
        }
        return temp;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T temp = get(size - 1);
        if (nextlast == 0) {
            nextlast = items.length - 1;
        } else {
            nextlast--;
        }
        size--;
        if (items.length >= 16) {
            if (size < items.length / 4 && size > 0) {
                resize(items.length / 2);
            }
        }
        return temp;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int itemIndex = (nextfirst + 1 + index) % items.length;
        return items[itemIndex];
    }


    public Iterator<T> iterator() {
        return new ArrayDequeIterable();
    }


    private class ArrayDequeIterable implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size && get(currentIndex) != null;
        }

        @Override
        public T next() {
            T temp = get(currentIndex);
            currentIndex++;
            return temp;
        }
    }

    private void resize(int capacity) {
        if (capacity < size) {
            return;
        }
        if (capacity < 8) {
            capacity = 8;
        }
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> ol = (Deque<T>) o;
        if (ol.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(ol.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;

    }
}
