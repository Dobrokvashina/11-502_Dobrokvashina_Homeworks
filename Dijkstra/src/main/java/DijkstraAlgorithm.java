
public class DijkstraAlgorithm {

    public int[] findWay(int[][] input, int begin) {

        int[]newOne = new int[input.length];

        for (int i = 0; i < newOne.length; i++) {
            if (i == begin)
                newOne[i] = 0;
            else
                newOne[i] = 1000;
        }

        newOne = dijkstra(input, newOne, begin);

        return newOne;
    }

    private int[] dijkstra(int[][] input, int[] result, int begin) {

        int n = 0;

        int[] wasHere = new int[result.length];
        wasHere[begin] = 1;

        int prev = -1;

        while (prev != begin) {
            prev = begin;

            for (int i = 0; i < result.length; i++) {
                if ((input[begin][i] != 0) && (result[begin] + input[begin][i] < result[i])) {
                   result[i] = result[begin] + input[begin][i];

                }
            }

            int min = 10000;
            for (int i = 0; i < result.length; i++) {
                if ((wasHere[i] == 0) && (result[i] < min ))
                    begin = i;
            }

            wasHere[begin] = 1;

            n++;
        }

        return result;
    }

}
