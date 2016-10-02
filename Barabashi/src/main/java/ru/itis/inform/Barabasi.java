package ru.itis.inform;


public class Barabasi {
    private int graph[][];
    private int deg[];
    private int bigDeg;

    public Barabasi(int size) {
        graph = new int[size][size];

        deg = new int [size];

        graph[0][1] = 1;
        graph[1][0] = 1;

        deg[0] = 1;
        deg[1] = 1;
        bigDeg = 2;


        for (int i = 2; i < graph.length; i++) {
            addVertex(i);
        }

    }

    private void addVertex(int i) {


        for (int k = 0; k < i; k++) {
            double may = deg[k];
            may /=bigDeg;
            double random = Math.random();
            while (random == 0) {
                random = Math.random();
            }
            if (random < may) {
                graph[k][i] = 1;
                graph[i][k] = 1;
                deg[k]++;
                deg[i]++;
                bigDeg +=2;
            }
        }
    }

    public void show() {
        for (int i = 0; i < graph.length; i++) {
            for (int k = 0; k < graph.length - 1; k++) {
                System.out.print(graph[i][k] + ", ");
            }
            System.out.println(graph[i][graph.length-1]);
        }
    }
}
