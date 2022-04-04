package org.j.dijkstra;

public class Dijkstra {

    private static final int M = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] weight = {
                {0, 3, 4, M, M, M},
                {M, 0, M, 2, 4, M},
                {M, M, 0, 4, M, 5},
                {M, M, M, 0, 5, M},
                {M, M, M, M, M, 6},
                {M, M, M, M, M, M}
        };

        int start = 0;
        dijkstra(weight, start);
    }

    private static int[] dijkstra(int[][] weight, int start) {
        int n = weight.length;
        int[] minDist = new int[n]; // 标记节点的最短路径
        // 初始化起始节点到各节点的路径
        for (int i = 0; i < n; i++) {
            minDist[i] = weight[start][i];
        }
        boolean[] visited = new boolean[n]; // 标记当前该节点的最短路径是否已求出
        // 起点标记为已访问
        minDist[start] = 0;
        visited[start] = true;

        for (int count = 1; count < n; count++) {
            int k = -1;
            int min = M;

            // 选取尚未访问的节点中的最短路径节点作为新起点
            for (int i = 0; i < n; i++) {
                if (!visited[i] && minDist[i] < min) {
                    min = minDist[i];
                    k = i;
                }
            }
            visited[k] = true;
            for (int i = 0; i < n; i++) {
                // 以新的起点，更新各相邻节点的最短路径
                if (!visited[i] && weight[k][i] != M && min + weight[k][i] < minDist[i]) {
                    minDist[i] = min + weight[k][i];
                }
            }
        }

        return minDist;
    }
}
