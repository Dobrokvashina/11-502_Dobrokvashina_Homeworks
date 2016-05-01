package ru.itis.inform;


public class HashMap {

    private Entry[] map;
    private int size;

    public HashMap() {
        map = new Entry[16];
        size = 16;
    }

    public HashMap(int size) {
        map = new Entry[size];
        this.size = size;
    }

    public void show() {
        for (int i = 0; i < size; i++) {
            if (map[i] != null) {
                System.out.print((i + 1) + ")" + map[i].getValue());
                Entry node = map[i];
                while(node.getNext() != null) {
                    node = node.getNext();
                    System.out.print("  ---- " + node.getValue());
                }
                System.out.println();
            } else {
                System.out.println((i + 1) + ")Null ");
            }
        }
    }

    public void put(String key, String value) {
        Entry newOne = new Entry(key, value);
        int ind = index(newOne.getHash());
        if (map.length == 0 || map[ind] == null) {
            map[ind] = newOne;
        } else {
            Entry cur = map[ind];
            while (cur != null && key != cur.getKey()) {
                cur = cur.getNext();
            }
            if (cur != null)
                cur.setValue(value);
            else {
                newOne.setNext(map[ind]);
                map[ind] = newOne;
            }
        }
    }

    public int index(int hash) {
        return hash & (size - 1);
    }

    public void delete(String key, String value) {
        Entry newOne = new Entry(key, value);
        Entry previous = null;
        Entry node = map[index(newOne.getHash())];
        while (node.getValue() != value && node.getNext() != null) {
            previous = node;
            node = node.getNext();
        }
        if (node.getValue() == value) {
            if (node.getNext() != null) {
                if (previous != null) {
                    previous.setNext(node.getNext());
                } else {
                    map[index(newOne.getHash())] = node.getNext();
                }
            } else {
                if (previous != null) {
                    previous.setNext(null);
                } else {
                    map[index(newOne.getHash())] = null;
                }
            }
        } else {
            System.out.println("There is no such element");
        }
    }
}
