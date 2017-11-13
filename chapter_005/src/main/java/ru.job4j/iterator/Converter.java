package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {



        return new Iterator<Integer>() {


            private final ArrayList<Integer> values = getValuesFromIteratorIterator();
            private int index = 0;



            ArrayList<Integer> getValuesFromIteratorIterator() {

                ArrayList<Integer> valuesFromIteratorIterator = new ArrayList<Integer>();

                while (it.hasNext()) {

                    Iterator<Integer> currIterator = it.next();

                    while (currIterator.hasNext()) {

                        valuesFromIteratorIterator.add(currIterator.next());

                    }
                }
                return valuesFromIteratorIterator;
            }



            @Override
            public boolean hasNext() {

                return values.size() > index;

            }

            @Override
            public Integer next() {

                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    return values.get(index++);
                }
            }
        };



    }
}
