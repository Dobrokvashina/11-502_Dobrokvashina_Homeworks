package ru.itis.inform;

public class SetArrayWithLinksImp implements Set {

    private int[] setsArray;
    private int size;

    public SetArrayWithLinksImp(int size) {
        this.size = size;
        setsArray = new int[size];

        for (int i = 0; i < size; i++) {
            setsArray[i] = i;
        }
    }

    public void add(int elementOne, int elementTwo) {
        if (findSet(elementOne) == findSet(elementTwo)) {
            System.out.println("same set");
        } else {
            unionBySetsName(findSet(elementOne), findSet(elementTwo));
                    System.out.println(elementOne + " -- " + elementTwo);
        }
    }

    public int findSet(int element) {
        int t = element;
        while (t != setsArray[t])
            t = setsArray[t];
        return t;
    }

    public void unionBySetsName(int setA, int setB) {
        setsArray[setA] = setB;
    }

    public void show() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + " --> " + findSet(i));
        }
    }
}
