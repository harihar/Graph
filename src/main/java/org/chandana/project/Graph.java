package org.chandana.project;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private static int idGenerator;

    private Node root;

    private int id = idGenerator++;
    public Set<Edge> edges = new HashSet<>();
    public Set<Node> nodes = new HashSet<>();

    private Pattern patternEmbedding;

    public Pattern getPatternEmbedding() {
        return patternEmbedding;
    }

    public void setPatternEmbedding(Pattern patternEmbedding) {
        this.patternEmbedding = patternEmbedding;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
        nodes.add(edge.getNode1());
        nodes.add(edge.getNode2());

        edge.getNode1().addAssociate(edge.getNode2());
        edge.getNode2().addAssociate(edge.getNode1());
    }

    public boolean hasEdge(Edge edge) {
        return edges.contains(edge);
    }

    public boolean isIsomorphic(Graph other, EdgeManager edgeManager) {
        if (!this.nodes.containsAll(other.nodes)) {
            return false;
        }
        if (!edgeManager.hasAllEdges(other.edges)) {
            return false;
        }
        return true;
    }

    /*
     * public boolean isIsomorphic(Graph other, EdgeManager edgeManager){
     * if(!this.nodes.containsAll(other.nodes)) return false;
     * if(!edgeManager.hasAllEdges(other.edges)) return false; return true; }
     */
    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return root.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Graph other = (Graph) obj;
        if (edges == null) {
            if (other.edges != null) {
                return false;
            }
        } else if (!edges.equals(other.edges)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (Edge edge : edges) {
            result.append(edge).append(" ,");
        }
        return result.append("]").toString();
    }

}
