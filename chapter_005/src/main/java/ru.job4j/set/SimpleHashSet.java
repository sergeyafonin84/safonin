package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

//Напишите свою реализацию Set на базе хэш-таблицы. Реализуйте следующие методы:
//        1) boolean add (E e)
//        2) boolean contains (E e)
//        3) boolean remove (E e)
//
//        Принципы организации хэш-таблиц вы сможете узнать из следующих источников:
//        1) Wikipedia - хеш-таблица
//        2) Core Java - К.Хорстманн
//        3) Алгоритмы. Построение и анализ. - Т.Кормен и др.
//        4) Алгоритмы и структуры данных. - Н. Вирт
//
//        Разрешение коллизий реализовывать не надо.
public class SimpleHashSet<E> implements Iterable<E>  {
    Object[] container;

    private int size;

    public int getSize() {
        return size;
    }

    void add(E e) {

        if (!suchAnElementAlreadyExists(e)) {

            Object[] newContainer = new Object[size + 1];

            Iterator<E> it = this.iterator();

            int ind = 0;

            if (size != 0) {

                while (it.hasNext()) {

                    newContainer[ind] = it.next();

                    ind++;
                }
            }
            newContainer[ind] = e;

            container = newContainer;

            size++;

        }

    }

    boolean suchAnElementAlreadyExists(E e) {

        boolean returnValue = false;

        Iterator<E> it = this.iterator();

        while (it.hasNext()) {

            E currElement = it.next();

            if (currElement.equals(e)) {

                returnValue = true;

                break;

            }
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

                throw  new NoSuchElementException();

            } else {

                return (E) container[cursor++];

            }
        }
    }
}
