package ru.itis.inform;

public class ArrayList<T> {

    private Object [] elements;

    private int size = 0;

    private int index = 0;

    private final int defaultSize = 16;

    public ArrayList() {

        this.elements = new Object[defaultSize];
    }

    public ArrayList(int size) {

        this.elements = new Object[size];
    }

    public int length() {
        return this.size;
    }

    public void growArray() {
        Object[] newOne = new Object[elements.length * 2];

        for (int i = 0 ; i < elements.length; i++) {
            newOne[i] = elements[i];
        }

        this.elements = newOne;
    }

    public void add(T element) {
        if (index == elements.length) {
            growArray();
        }
        elements[index] = element;
        this.index++;
        this.size++;
    }

    public T get(int index) {
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        return (T)elements[index];
    }

    public void set(int index, T element) {
        this.elements[index] = element;
    }


}
