package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

//Необходимо создать контейнер с методами add(E value);E get(int index);и реализовать для него Iterable<E>
//Внутри контейнера нельзя использовать структуры данных из JDK - ArrayList. LinkedList и другие.
public class Container<E> implements SimpleContainer<E> {

    //Внутри контейнера должен быть массив Object[] container;
    Object[] container;

    private int size;


//    Контейнер должен быть динамический. То есть метод add(E value) - может принимать бесконечное количество элементов.
    @Override
    public void add(E value) {

        Object[] newContainer = new Object[size + 1];

        Iterator<E> it = this.iterator();

        int ind = 0;

        if (size != 0) {

            while (it.hasNext()) {

                newContainer[ind] = it.next();

                ind++;
            }
        }
        newContainer[ind] = value;

        container = newContainer;

        size++;

    }

    @Override
    public E get(int index) {

        int ind = 0;

        E returnValue = null;

        Iterator<E> it = this.iterator();

        while (it.hasNext()) {

            E currElem = it.next();

            if (ind == index) {
                returnValue = currElem;
                break;
            }

            ind++;
        }

        return returnValue;
    }


    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {

        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {

            if (!this.hasNext()) {

                throw new NoSuchElementException();
            } else {
                return (E) container[cursor++];
            }
        }
    }
}