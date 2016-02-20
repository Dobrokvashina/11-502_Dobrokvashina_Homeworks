package ru.itis.inform;


public interface DirectedGraph {
    /**
     * Добавление вершины в граф
     */
    void addVertex();



    /**
     * Добавление ребра взвешенного
     * @param vertexA первая вершина
     * @param vertexB вторая вершина
     * @param weight вес ребра
     */
    void addDirectedWeightedEdge(int vertexA, int vertexB, int weight);

    void addDirectedEdge(int vertexA,int vertexB);

    /**
     * Вывод графа на экран
     */
    void showGraph();

    int [][] RunFloid();

    void showFloid();
}
