package ru.itis.inform;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinarySearchTreeImpl implements BinarySearchTree {

    private  Node root;
    private  int[] order;
    private int count;
    private int i;

    private Node insertNode(Node root, int element)
    {
        if (root == null) {
            root = new Node(element);
            root.setLeft(null);
            root.setRight(null);
        } else if (element <= root.getData()) {
            root.setLeft(insertNode(root.getLeft(), element));
        } else {
            root.setRight(insertNode(root.getRight(), element));
        }

        return root;
    }

    private void showRec(Node root, int level) {
        if (root != null) {
            showRec(root.getRight(), level + 1);

            for (int i = 0; i < level; i++) {
                System.out.print("---");
            }

            System.out.println(root.getData());

            showRec(root.getLeft(), level + 1);
        }
    }

    public void insert(int element) {
        this.root = insertNode(this.root, element);
        count++;
    }

    public void show() {
        showRec(root, 0);
    }

    private Node search(Node root, int element) {
        if(root == null) {
            return null;
        } else
        if(root.getData() == element) {
            return root;
        } else {
            if (search(root.getRight(), element) == null)
                return search(root.getLeft(), element);
            else
                return search(root.getRight(), element);
        }
    }

    public void change (int nowElement, int newElement) {
        Node need = search(root, nowElement);

        if (need != null)
            need.setData(newElement);
        else throw new NoSuchElementException("There is no such element in this tree");
    }

    private void inOrder(Node root)
    {
        if (root != null) {
            inOrder(root.getLeft());
            System.out.print(root.getData() + " ");
            inOrder(root.getRight());
        }
    }

    public void inOrderPrint() {
        inOrder(this.root);
    }

    public boolean isSumOfEveryLeverBiggerThanSumOfPreviousOne() {
        Queue<Node> currentLevel = new LinkedList<Node>();
        Queue<Node> nextLevel = new LinkedList<Node>();

        currentLevel.add(this.root);
        int level = 0;
        boolean flag = true;
        int sum1 = this.root.getData();
        int sum2 = 0;
        while ((!currentLevel.isEmpty()) && flag) {

            level++;
            Iterator<Node> iter = currentLevel.iterator();
            while (iter.hasNext()) {
                Node currentNode = iter.next();
                if (currentNode.getLeft() != null) {
                    nextLevel.add(currentNode.getLeft());
                }
                if (currentNode.getRight() != null) {
                    nextLevel.add(currentNode.getRight());
                }

            }

            if (level > 1) {

                for (Node i: currentLevel) {
                    sum2 += i.getData();
                }
                if (sum1 > sum2) {
                    flag = false;
                }
                sum1 = sum2;
                sum2 = 0;
            }

            currentLevel = nextLevel;
            nextLevel = new LinkedList<Node>();

        }

        return flag;
    }

    public boolean isBST(){
        return isValid(root, Integer.MIN_VALUE,
                Integer.MAX_VALUE);
    }

    private boolean isValid(Node node, int MIN, int MAX) {
        if(node == null)
            return true;
        if(node.getData() > MIN && node.getData() < MAX
                && isValid(node.getLeft(), MIN, node.getData()) && isValid(node.getRight(), node.getData(), MAX))
            return true;
        else
            return false;
    }


}
