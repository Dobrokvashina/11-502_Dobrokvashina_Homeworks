package ru.itis.inform;

public interface List<T> {

    /**
     * Добавить элемент в начало списка
     * @param element значение добавляемого элемента
     */
    void add(T element);
    void addEnd(T element);

    boolean isEmpty();

    /**
     * Удаление элемента по значению
     * @param element значение элемента
     */
    void remove(T element);

    Iterator<T> iterator();

}
