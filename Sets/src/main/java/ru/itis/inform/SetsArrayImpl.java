package ru.itis.inform;


import java.util.ArrayList;
import java.util.LinkedList;

public class SetsArrayImpl implements Set{

    private int[] setsArray;
    private int size;
    private ArrayList<LinkedList<Integer>> ways;

    public SetsArrayImpl(int size) {
        setsArray = new int[size];

        ways = new ArrayList<LinkedList<Integer>>(size*size);
        this.size = size;
        LinkedList<Integer> list;
        for (int i = 0; i < size; i++) {
            setsArray[i] = i;
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    ways.add(null);
                }
                else {
                    list = new LinkedList<Integer>();
                    list.add(i);
                    ways.add(list);
                }
            }
        }
    }

    public int findSet(int element) {
        return setsArray[element];
    }

    public void add(int elementOne, int elementTwo) {
        if(findSet(elementOne) == findSet(elementTwo)) {
            System.out.println(ways.get(elementOne*size + elementTwo).toString());
        } else {
            unionBySetsName(findSet(elementOne), findSet(elementTwo));
            addWays(elementOne, elementTwo);
            System.out.println(elementOne + " -- " + elementTwo);
        }
    }

    public void unionBySetsName(int setA, int setB) {
        for (int i = 0; i < size; i++) {
            if (setsArray[i] == setB) {
                setsArray[i] = setA;
            }

        }
    }

    public void show() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + " --> " + setsArray[i]);
        }
    }

    public void addWays(int elementOne, int elementTwo) {

        LinkedList<Integer> way = new LinkedList<Integer>();
        Object nextWay;
        way.add(elementOne);
        way.add(elementTwo);
        ways.set(elementOne*size + elementTwo, way);
        way = new LinkedList<Integer>();
        way.add(elementTwo);
        way.add(elementOne);
        ways.set(elementTwo*size + elementOne, way);
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if((i!=j) && (findSet(elementOne) == findSet(i)) && (findSet(i) == findSet(j)) && (ways.get(i*size + j) == null)) {
                    if(ways.get(i*size + elementOne) != null && ways.get(elementTwo*size + j) != null) {
                        way = (LinkedList)ways.get(i*size + elementOne).clone();
                        nextWay = ways.get(elementTwo*size + j).clone();
                        way.addAll((LinkedList)nextWay);
                        ways.set(i*size + j, way);
                        way = (LinkedList)ways.get(j*size + elementTwo).clone();
                        nextWay = ways.get(elementOne*size + i).clone();
                        way.addAll((LinkedList)nextWay);
                        ways.set(j*size + i, way);
                    } else {
                        way = (LinkedList)ways.get(i*size + elementTwo).clone();
                        nextWay =ways.get(elementOne*size + j).clone();
                        way.addAll((LinkedList)nextWay);
                        ways.set(i*size + j, way);
                        way = (LinkedList)ways.get(j*size + elementOne).clone();
                        nextWay = ways.get(elementTwo*size + i).clone();
                        way.addAll((LinkedList)nextWay);
                        ways.set(j*size + i, way);
                    }
                }
            }
        }

    }
}
