package ru.itis.inform;


public interface List {

    void add(RationalNumber element);

    void addAfter(RationalNumber element, RationalNumber previous);

    RationalNumberNode getFirst();

    void show();

}
