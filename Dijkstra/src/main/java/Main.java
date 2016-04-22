
public class Main {

    public static void main(String[] args) {
        int [][] array = new int[3][3];

        array[1][2] = 2;
        array[0][2] = 10;
        array[2][1] = 5;

        DijkstraAlgorithm alg = new DijkstraAlgorithm();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + "   ");
            }
            System.out.println();
        }

        int [] way = alg.findWay(array, 0);

        System.out.println();

        for (int i = 0; i < array.length; i++) {
            if (way[i] != 1000)
                System.out.print(way[i] + "   ");
            else
                System.out.print("inf   ");
        }


    }
}
