package ru.itis.inform;

public interface Graph {

    /**
     * Добавление вершины в граф
     */
    void addVertex();

    /**
     * Добавление ребра
     * @param vertexA первая вершина
     * @param vertexB вторая вершина
     */
    void addWeightedEdge(int vertexA, int vertexB, int weight);

    void addEdge(int vertexA, int vertexB);

    /**
     * Вывод графа на экран
     */
    void showGraph();

    int [][] RunFloid();

    void showFloid();
}
