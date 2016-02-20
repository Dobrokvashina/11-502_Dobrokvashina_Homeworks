package ru.itis.inform;


public class GraphMatrixImpl implements Graph, DirectedGraph {

    private static final int DEFAULT_SIZE = 50;
    /**
     * Тут храним вершины и ребра
     */
    private int matrix[][];

    private int floidMatrix[][];

    /**
     * Сколько вершин сейчас в графе. Вершины нумеруются от 0.
     */
    private int verticesCount;

    private int maxSize;

    public GraphMatrixImpl() {
        initGraph(DEFAULT_SIZE);
    }

    public GraphMatrixImpl(int maxSize) {
        initGraph(maxSize);
    }

    private void initGraph(int maxSize) {
        this.maxSize = maxSize;
        this.verticesCount = 0;
        this.matrix = new int[maxSize][maxSize];
    }

    @Override
    public void addVertex() {
        if (this.verticesCount < this.maxSize) {
            this.verticesCount++;
        } else throw new IllegalArgumentException();
    }

    public void addWeightedEdge(int vertexA, int vertexB, int weight) {
        if (vertexA < verticesCount && vertexB < verticesCount) {
            this.matrix[vertexA][vertexB] = weight;
            this.matrix[vertexB][vertexA] = weight;
        } else throw new IllegalArgumentException();
    }

    public void addEdge(int vertexA, int vertexB) {
        if (vertexA < verticesCount && vertexB < verticesCount) {
            this.matrix[vertexA][vertexB] = 1;
            this.matrix[vertexB][vertexA] = 1;
        } else throw new IllegalArgumentException();
    }

    public void addDirectedWeightedEdge(int vertexA, int vertexB, int weight) {
        if (vertexA < verticesCount && vertexB < verticesCount) {
            this.matrix[vertexA][vertexB] = weight;
        } else throw new IllegalArgumentException();
    }

    public void addDirectedEdge(int vertexA, int vertexB) {
        if (vertexA < verticesCount && vertexB < verticesCount) {
            this.matrix[vertexA][vertexB] = 1;
        } else throw new IllegalArgumentException();
    }

    @Override
    public void showGraph() {
        for (int i = 0; i < verticesCount; i++) {
            for (int j = 0; j < verticesCount - 1; j++) {
                System.out.print(matrix[i][j] + ",  ");
            }
            System.out.println(matrix[i][verticesCount - 1]);
        }
    }

    public int[][] RunFloid() {

        this.floidMatrix = new int[this.verticesCount][this.verticesCount];

        for (int i = 0; i < this.verticesCount; i++) {
            for (int j = 0; j < this.verticesCount; j++) {
                if (i != j && this.matrix[i][j] == 0) {
                    this.floidMatrix[i][j] = 100;
                } else {
                    this.floidMatrix[i][j] = this.matrix[i][j];
                }
            }
        }

        for (int k = 0; k < this.verticesCount; k++) {
            for (int i = 0; i < this.verticesCount; i++) {
                for (int j = 0; j < this.verticesCount; j++) {
                    this.floidMatrix[i][j] = Math.min((this.floidMatrix[i][k] + this.floidMatrix[k][j]), this.floidMatrix[i][j]);
                }
            }
        }
        return floidMatrix;
    }

    public void showFloid() {
        for (int i = 0; i < verticesCount; i++) {
            for (int j = 0; j < verticesCount - 1; j++) {
                System.out.print(this.floidMatrix[i][j] + ",  ");
            }
            System.out.println(this.floidMatrix[i][verticesCount - 1]);
        }
    }
}
