package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private int size;
    private int capacity;
    private int nextfirst;
    private int nextlast;
    private T[] items;

    public ArrayDeque() {
        items=(T[])new Object[8];
        this.capacity=items.length;
        nextfirst=capacity-1;
        nextlast=0;
        size=0;

    }

    @Override
    public void addFirst(T item) {
        //直接当size等于capacity时调整大小，而不是看两个指针的相对位置
        if (size==capacity)
            resize(capacity*2);
        items[nextfirst]=item;
        size++;
        //nextFirst有可能越界
        nextfirst=nextfirst==0?capacity-1:nextfirst-1;
    }

    @Override
    public void addLast(T item) {
        if (size==capacity)
            resize(capacity*2);
        items[nextlast]=item;
        size++;
        //nextLast有可能越界
        nextlast=(nextlast+1)%capacity;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        //nextFirst有可能指向最后一个位置
        for (int i=(nextfirst+1)%capacity;i!=nextlast-1;i=(i+1)%capacity)
            System.out.print(items[i]+" ");
        System.out.print(items[nextlast-1]);
    }

    @Override
    public T removeFirst() {
        //当数组的内容为空的时候，才无法进行remove操作，而不是取决于nextFirst的位置。
        if (size==0)return null;
        nextfirst=(nextfirst+1)%capacity;
        T temp=items[nextfirst];
        items[nextfirst]=null;
        size--;
        if (capacity>=16&&size<capacity/4)
            resize(capacity/2);
        return temp;

    }

    @Override
    public T removeLast() {
        if (size==0)return null;
        nextlast = (nextlast - 1 + capacity) % capacity; // ✅ 处理环形索引
        T temp=items[nextlast];
        items[nextlast]=null;
        size--;
        if (items.length>=16&&size<items.length/4)
            resize(items.length/2);
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

    private void resize(int capacity){
        T[]a=(T[])new Object[capacity];
        //由于nextFirst和nextLast的位置不确定，只能一个一个地复制到新的数组中
        //从nextFirst右边的第一个点开始复制
        //到nextLast左边的第一个点复制结束
        for (int i=1;i<=size;i++)
            a[i]=items[(++nextfirst)%this.capacity];
        this.capacity=capacity;
        //这两个指针指向什么地方已经不重要了
        nextfirst=0;
        nextlast=size+1;
        items=a;
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
