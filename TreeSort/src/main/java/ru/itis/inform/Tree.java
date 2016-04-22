package ru.itis.inform;


public class Tree {
    private  Node root;
    private int count;
    private int[] array = new int[10000];
    private int i = 0;

    // рекурсия для вставки нового "узла"  дерево
    private Node insertNode(Node root, int element)
    {
        count++;
        if (root == null) {
            root = new Node(element);
            /*
            root.setLeft(null);
            root.setRight(null);
            */
        } else if (element <= root.getData()) {
            root.setLeft(insertNode(root.getLeft(), element));
        } else {
            root.setRight(insertNode(root.getRight(), element));
        }

        return root;
    }

    //возвращения количества итераций для данного дерева
    public int getCount(){ return count;}

    //вставка нового "узла"
    public void insert(int element) {
        this.root = insertNode(this.root, element);
    }

    //рекурсионный проход по всем элементам дерева
    private void inOrder(Node root)
    {
        count++;
        if (root != null) {
            inOrder(root.getLeft());
            array[i] = root.getData();
            i++;
            inOrder(root.getRight());
        }
    }

    //вывод в массив упорядоченного методом бинарного дерева массива
    public int[] inOrderPrint() {
        inOrder(this.root);
        return array;
    }
}
