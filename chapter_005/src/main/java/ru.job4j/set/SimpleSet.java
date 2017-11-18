package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;


//Реализовать коллекцию SimpleSet. Коллекция должна обеспечивать void add(E e) и реализовывать Iterator<E>.
//        Коллекция не должна хранить дубликаты.
//        Set - внутри для хранения данных использует обычные массивы.
public class SimpleSet<E> implements Iterable<E> {


    Object[] container;

    private int size;

    public int getSize() {
        return size;
    }

    void add(E e){

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

                throw new NoSuchElementException();

            } else {

                return (E) container[cursor++];

            }
        }
    }
}
