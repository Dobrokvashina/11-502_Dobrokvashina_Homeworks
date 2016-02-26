package ru.itis.inform;

public interface Iterator<T> {

    /**
     * Проверить, есть ли слеующий элемент
     * @return true - если есть, false - в противном случае
     */
    boolean hasNext();

    /**
     * Возвращает следующий элемент
     * @return возвращаемый элемент
     */
    void next();

    T peekNext();

    Node getNext();

    /**
     * Возвращает предыдущий элемент
     * @return возвращаемый элемент
     */
    void previous();

    T peekPrevious();

    /**
     * Вставляет новый элемент между next и previous
     * @param element
     */
    void insert(T element);
}
