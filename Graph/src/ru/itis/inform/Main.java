package ru.itis.inform;


public class Main {

    public static void main(String[] args) {
        Graph graph = new GraphMatrixImpl();

        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();

        graph.addWeightedEdge(0, 2, 3);
        graph.addWeightedEdge(0, 3, 4);
        graph.addWeightedEdge(3, 4, 2);
        graph.addWeightedEdge(2, 1, 5);
        graph.addWeightedEdge(2, 4, 1);

        graph.showGraph();
        System.out.println();

        graph.RunFloid();
        graph.showFloid();

    }
}

