package org.j.dijkstra;

/**
 * 加权有向图
 *
 * @author 周琼
 * @since 2022-04-04 15:04
 **/
public class EdgeWeightedDigraph {

    private final int vertexNum;
    private int edgeNum;
    private final Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int vertexNum) {
        this.vertexNum = vertexNum;
        this.edgeNum = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[vertexNum];
        for (int v = 0; v < vertexNum; v++) {
            adj[v] = new Bag<>();
        }
    }

    int vertexNum() {
        return vertexNum;
    }

    int edgeNum() {
        return edgeNum;
    }

    public void addEdge(DirectedEdge edge) {
        adj[edge.from()].add(edge);
        edgeNum++;
    }

    public Iterable<DirectedEdge> adj(int vertex) {
        return adj[vertex];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < vertexNum; v++) {
            for (DirectedEdge e : adj[v]) {
                bag.add(e);
            }
        }

        return bag;
    }

}
