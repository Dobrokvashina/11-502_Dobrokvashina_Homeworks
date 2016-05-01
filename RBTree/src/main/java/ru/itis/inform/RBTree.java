package ru.itis.inform;


public class RBTree {
    private Node inRoot;
    private  Node root;

    public void show() {
        showRec(root, 0);
    }

    private void showRec(Node root, int level) {
        if (root != null) {
            showRec(root.getRight(), level + 1);

            for (int i = 0; i < level; i++) {
                System.out.print("---");
            }

            System.out.println(/*root.getData() + " " +*/ root.getKey());

            showRec(root.getLeft(), level + 1);
        }
    }

    public void add(int element) {
        insertNode(null, this.root, element);
        insert_case1(inRoot);

}

    private Node insertNode(Node parent, Node root, int element) {
        if (root == null) {
            root = new Node(element, "element");
            root.setLeft(null);
            root.setRight(null);
            root.setParent(parent);
            if (parent!=null) {
                if (root.getKey() <= parent.getKey()) {
                    parent.setLeft(root);
                } else {
                    parent.setRight(root);
                }
            }
            inRoot = root;
        } else if (element <= root.getKey()) {
            root.setLeft(insertNode(root, root.getLeft(), element));
        } else {
            root.setRight(insertNode(root, root.getRight(), element));
        }
        return root;
    }


    private void insert_case1(Node n){
        if (n.getParent() == null){
            n.setRed(false);
            this.root = n;
        }else{
            insert_case2(n);
        }
    }

    private void insert_case2(Node n){
        if (n.getParent().isRed()){
            insert_case3(n);
        }
    }

    private void insert_case3(Node n){
        Node u = uncle(n);
        Node g;
        if ((u != null) && (u.isRed())&&(n.getParent().isRed())){
            n.getParent().setRed(false);
            u.setRed(false);
            g = grandparent(n);
            g.setRed(true);
            insert_case1(g);
        }else{
            insert_case4(n);
        }
    }

    private void insert_case4(Node n){
        Node g = grandparent(n);
        if ((n == n.getParent().getRight())&&(n.getParent() == g.getLeft())) {
            rotate_left(n.getParent());
            n = n.getLeft();
        }else if ((n == n.getParent().getLeft())&&(n.getParent() == g.getRight())){
            rotate_right(n.getParent());
            n = n.getRight();
        }
        insert_case5(n);
    }

    private void insert_case5(Node n){
        Node g = grandparent(n);
        n.getParent().setRed(false);
        g.setRed(true);
        if ((n == n.getParent().getLeft())&&(n.getParent() == g.getLeft())){
            rotate_right(g);
        }else{
            rotate_left(g);
        }
    }



    private Node grandparent(Node n){
        if ((n != null) && (n.getParent() != null)){
            return n.getParent().getParent();
        }else{
            return null;
        }
    }

    private Node uncle(Node n){
        Node g = grandparent(n);
        if (g == null){
            return null;
        }
        if (n.getParent() == g.getLeft()){
            return g.getRight();
        }else{
            return g.getLeft();
        }
    }

    private void rotate_left(Node n){
        Node pivot = n.getRight();
        pivot.setParent(n.getParent());
        if (n.getParent() != null) {
            if (n.getParent().getLeft() == n) {
                n.getParent().setLeft(pivot);
            } else {
                n.getParent().setRight(pivot);
            }
        }else{
            this.root = pivot;
        }

        n.setRight(pivot.getLeft());
        if (pivot.getLeft() != null){
            pivot.getLeft().setParent(n);
        }
        n.setParent(pivot);
        pivot.setLeft(n);
    }

    private void rotate_right(Node n){
        Node pivot = n.getLeft();
        pivot.setParent(n.getParent());
        if (n.getParent() != null){
            if(n.getParent().getLeft() == n){
                n.getParent().setLeft(pivot);
            }else{
                n.getParent().setRight(pivot);
            }
        }else{
            this.root = pivot;
        }

        n.setLeft(pivot.getRight());
        if (pivot.getRight() != null){
            pivot.getRight().setParent(n);
        }

        n.setParent(pivot);
        pivot.setRight(n);
    }
}
