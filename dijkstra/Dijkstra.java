package dijkstra;

public class Dijkstra {

    private static final int M = Integer.MAX_VALUE;
    private static String[] verts = new String[]{"A", "B", "C", "D", "E"};

    public static void main(String[] args) {
        int[][] weight = {
                {0, 4, M, 2, M},
                {4, 0, 4, 1, M},
                {M, 4, 0, 1, 3},
                {2, 1, 1, 0, 7},
                {M, M, 3, 7, 0}
        };

        int start = 0;
        dijkstra(weight, start);
    }

    private static int[] dijkstra(int[][] weight, int start) {
        int n = weight.length;
        int[] shortPath = new int[n]; // 标记节点的最短路径
        String[] path = new String[n];
        // 初始化起始节点到各节点的路径
        for (int i = 0; i < n; i++) {
            path[i] = verts[start] + "-->" + verts[i];
            shortPath[i] = weight[start][i];
        }
        boolean[] visited = new boolean[n]; // 标记当前该节点的最短路径是否已求出
        // 起点标记为已访问
        shortPath[start] = 0;
        visited[start] = true;

        for (int count = 1; count < n; count++) {
            int k = -1;
            int minDistance = M;

            // 选取尚未访问的节点中的最短路径节点作为新起点
            for (int i = 0; i < n; i++) {
                if (!visited[i] && shortPath[i] < minDistance) {
                    minDistance = shortPath[i];
                    k = i;
                }
            }
            visited[k] = true;
            for (int i = 0; i < n; i++) {
                // 以新的节点作为起点，更新各相邻节点的最短路径
                if (!visited[i] && weight[k][i] != M && minDistance + weight[k][i] < shortPath[i]) {
                    shortPath[i] = minDistance + weight[k][i];
                    path[i] = path[k] + "-->" + verts[i];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println("从" + verts[start] + "出发到" + verts[i] + "的最短路径为：" + path[i] + ", 路径长为：" + shortPath[i]);
        }

        return shortPath;

    }
}
