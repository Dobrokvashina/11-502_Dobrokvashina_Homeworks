package ru.itis.inform;

import java.util.ArrayList;
import java.util.Iterator;

public class BinaryHeap {

    private ArrayList<Integer> heap;
    private int size;

    public BinaryHeap() {
        heap = new ArrayList<Integer>();
        size = 0;
    }

    public void add(int elemnt) {
        int i = size;
        int parent = (i - 1)/2;
        heap.add(elemnt);
        while ((parent >= 0) && (i >0))  {
            if (heap.get(i) > heap.get(parent)) {
                int temp = heap.get(i);
                heap.set(i, heap.get(parent));
                heap.set(parent, temp);
            }
            i = parent;
            parent = (i-1)/2;
        }
        size++;
    }

    public void show() {
        int i = 0;
        int k = 1;
        System.out.println();

        while (i < size) {
            while((i < k) && (i < size)) {
                System.out.print(heap.get(i) + "   ");
                i++;
            }
            System.out.println();
            k = k*2 + 1;
        }

        System.out.println();
    }

    public int getMax() {
        int x = heap.get(0);
        heap.set(0, heap.get(size- 1));
        size--;
        heapify(0);
        return x;
    }

    private void heapify(int i) {
        int left = 2*i + 1;
        int right = 2*i +2;

        if(left < size) {
            if(heap.get(i) < heap.get(left)) {
                int temp = heap.get(i);
                heap.set(i, heap.get(left));
                heap.set(left, temp);
                heapify(left);
            }
        }
        if(right < size) {
            if(heap.get(i) < heap.get(right)) {
                int temp = heap.get(i);
                heap.set(i, heap.get(right));
                heap.set(right, temp);
                heapify(right);
            }
        }
        }
}
