package ru.job4j.list;

import java.util.Iterator;
import java.util.ListIterator;

public class LinkedListTask<T> implements Iterable<T> {

    Node<T> firstInList;
    int sizeOfList = 0;
    Node<T> lastInList;

    public LinkedListTask() {

        Node first = new Node(1);
        this.firstInList = first;

        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        sizeOfList = 4;

    }

    public void add(T value) {
        Node<T> newNode = new Node<T>(value);
        if (this.firstInList == null) {
            this.firstInList = newNode;
            this.lastInList = newNode;
        } else {
            this.lastInList.next = newNode;
            this.lastInList = newNode;
        }
        sizeOfList++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    class Itr implements Iterator<T> {

        Node<T> itrLastNode;

        int itrIndex = 0;

        @Override
        public boolean hasNext() {
            return itrIndex < sizeOfList;
        }


        @Override
        public T next() {

            T returnValue = null;

            if (!hasNext()) {
                new Exception("no such element!!! ");
            } else {

                if (itrLastNode == null) {
                    returnValue = firstInList.value;
                    itrLastNode = firstInList;

                } else {
                    Node<T> nextNode = itrLastNode.next;
                    returnValue = nextNode.value;
                    itrLastNode = nextNode;

                }
            }

            itrIndex++;
            return returnValue;
        }
    }

    public class Node<T> {

        public Node(T value) {
            this.value = value;
        }


        T value;
        Node<T> next;
    }

    //    Написать алгоритм определяющий, что список содержит замыкания.
//    boolean hasCycle(Node first);
//    Обратите внимание, что список может быть замкнут и в середине. К примеру, 3-й узел будет ссылаться на 2-й узел.
// Определение зацикленности необходимо реализовать путем прохода по узлам, без использования коллекций.
//    boolean hasCycle(Node first) {

    boolean hasCycle() {

        boolean returnValue = false;

        Iterator<T> it = this.iterator();
        int numberOfIterations = 0;
        Node<T> firsCurrNode = firstInList;
        while (it.hasNext()) {

            if (returnValue) {
                break;
            }

            Iterator<T> it2 = this.iterator();
            int numberOfIterations2 = 0;
            Node<T> secondCurrNode = firstInList;
            while (it2.hasNext()) {

                if (returnValue) {
                    break;
                }

                if (numberOfIterations2 > numberOfIterations) {

                    if (firsCurrNode == firsCurrNode.next || secondCurrNode == secondCurrNode.next || secondCurrNode.next == firsCurrNode) {
                        returnValue = true;
                    }

                }

                numberOfIterations2++;
                it2.next();
                secondCurrNode = secondCurrNode.next;
            }

            numberOfIterations++;
            it.next();
            firsCurrNode = firsCurrNode.next;
        }

        return returnValue;

    }
}