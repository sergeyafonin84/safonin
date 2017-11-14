package ru.job4j.generic;

//Доделать контейнер SimpleArray<T> добавить методы add, update, delete, get(int index);
public class SimpleArray<T> {

    private Object[] objects;
    private int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(T value) {
        this.objects[index++] = value;
    }

    public T get(int position) {
        rangeCheck(position);
        return (T) this.objects[position];
    }

    private void rangeCheck(int ind) {
        if (ind >= index) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

    }

    private String outOfBoundsMsg(int ind) {
        return "Ind: " + ind + ", Size: " + index;
    }

    public T update(int ind, T value) {
        rangeCheck(ind);

        T oldValue = (T) objects[ind];
        objects[ind] = value;
        return oldValue;
    }

    public T delete(int ind) {
        rangeCheck(ind);

        T oldValue = (T) objects[ind];

        int numMoved = index - ind - 1;

        if (numMoved > 0) {

            System.arraycopy(objects, ind + 1, objects, ind,
                    numMoved);

        }

        objects[--index] = null; // clear to let GC do its work

        return oldValue;
    }

    private void fastRemove(int ind) {

        int numMoved = index - ind - 1;

        if (numMoved > 0) {

            System.arraycopy(objects, ind + 1, objects, ind,
                    numMoved);

        }

        objects[--index] = null; // clear to let GC do its work
    }

}