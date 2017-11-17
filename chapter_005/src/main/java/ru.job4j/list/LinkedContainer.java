package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

//Внутри контейнера должен данные должны храниться через ссылки.
//Контейнер должен быть динамический. То есть метод add(E value) - может принимать бесконечное количество элементов.
//Внутри контейнера нельзя использовать структуры данных из JDK - ArrayList. LinkedList и другие.
public class LinkedContainer<E> implements SimpleContainer<E> {

    int size = 0;

    Node<E> first;

    Node<E> last;

    @Override
    public void add(E value) {
        linkLast(value);
    }

    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }

        size++;
    }

    protected static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;

            for (int i = 0; i < index; i++) {
                x = x.next;
            }

            return x;
        } else {
            Node<E> x = last;

            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }

            return x;
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public Iterator<E> iterator() {
        checkPositionIndex(0);
        return new Itr(0);
    }

    private class Itr implements Iterator<E> {

        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;

        Itr(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    protected E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }

        size--;
//        modCount++;
        return element;
    }

    public void addFirst(E e) {
        linkFirst(e);
    }

    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }

        size++;
//        modCount++;
    }

    protected E unlinkLast(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }

        size--;
//        modCount++;
        return element;
    }
}