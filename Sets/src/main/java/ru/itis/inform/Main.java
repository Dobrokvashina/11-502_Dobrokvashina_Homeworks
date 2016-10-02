package ru.itis.inform;

public class Main {

    public static void main(String[] args) {
        SetArrayWithLinksImp testIt = new SetArrayWithLinksImp(10);

        testIt.add(3,4);
        testIt.add(4,9);
        testIt.add(8,0);
        testIt.add(2,3);
        testIt.add(5,6);
        testIt.add(2,9);
        testIt.add(5,9);
        testIt.add(7,3);
        testIt.add(4,8);
        testIt.add(5,6);
        testIt.add(0,2);
        testIt.add(6,1);

        System.out.println();
        System.out.println("elem - set");

        testIt.show();

    }

}
