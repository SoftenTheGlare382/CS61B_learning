package deque;


import java.util.Iterator;
import java.util.function.Consumer;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private TNode sentinelF;//头结点
    private int size;//链表长度
    private TNode sentinelB;//尾结点
//    public Iterator<T> iterator;

//    @Override
//    public boolean hasNext() {
//        return iterator.hasNext();
//    }
//
//    @Override
//    public T next() {
//        return iterator.next();
//    }

    //节点类
    public class TNode {
        public T item;
        public TNode next;
        public TNode prev;

        public TNode(T i, TNode n, TNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    public LinkedListDeque() {
        sentinelF = new TNode(null, null, null);
        sentinelB = new TNode(null, null, null);
        sentinelF.next = sentinelB;
        sentinelB.prev = sentinelF;
        size = 0;
    }


    /**
     * add node from head node
     *
     * @param item value of new node
     */
    @Override
    public void addFirst(T item) {
        TNode temp = new TNode(item, sentinelF.next, sentinelF);
        sentinelF.next.prev = temp;
        sentinelF.next = temp;

        size++;
    }


    /**
     * add node from end node
     *
     * @param item value of new node
     */
    @Override
    public void addLast(T item) {
        TNode temp = new TNode(item, sentinelB, sentinelB.prev);
        sentinelB.prev.next = temp;
        sentinelB.prev = temp;
        size++;
    }

    /**
     * @return int size of deque
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @param index the number of item that wanted
     * @return {@link T } the value of item
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        TNode temp = sentinelF;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }


    /**
     * print all nodes of deque, space between two nodes
     */
    @Override
    public void printDeque() {
        TNode temp = sentinelF;
        for (int i = 0; i < size; i++) {
            temp = temp.next;
            if (temp == null) {
                return;
            }
            System.out.print(temp.item + " ");

        }
    }


    /**
     * remove first one of deque
     *
     * @return {@link T }the value of node removed
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        TNode temp = sentinelF.next;
        sentinelF.next = temp.next;
        temp.next.prev = sentinelF;
        size--;
        return temp.item;
    }

    /**
     * remove the last one of deque
     *
     * @return {@link T }the value of node removed
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        TNode temp = sentinelB.prev;
        sentinelB.prev = temp.prev;
        temp.prev.next = sentinelB;
        size--;
        return temp.item;
    }

    /**
     * get iterator
     *
     * @return {@link Iterator }<{@link T }>
     */
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int wizPos;
        private LinkedListIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T item = get(wizPos);
            wizPos += 1;
            return item;
        }
    }

    /**
     * get the value of node recursively
     *
     * @param index
     * @return {@link T }
     */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        class gethelper {
            public T helper(TNode node, int currentIndex) {
                if (index == currentIndex) {
                    return node.item;
                } else {
                    return helper(node.next, currentIndex + 1);
                }
            }
        }
        gethelper gethelper = new gethelper();
        return gethelper.helper(sentinelF.next, 0);
    }

    /**
     * judge a list equals another one or not
     *
     * @param o list
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
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
