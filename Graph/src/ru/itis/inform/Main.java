package ru.itis.inform;

import ru.itis.inform.Graph;
import ru.itis.inform.GraphMatrixImpl;

public class Main {

    public static void main(String[] args) {
        Graph graph = new GraphMatrixImpl();

        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();

        graph.addEdge(0, 2, 3);
        graph.addEdge(0, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(2, 1, 5);
        graph.addEdge(2, 4, 1);

        graph.showGraph();
    }
}

