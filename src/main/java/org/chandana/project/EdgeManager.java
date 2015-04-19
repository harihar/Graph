package org.chandana.project;

import java.util.HashSet;
import java.util.Set;

public class EdgeManager {
	private Set<Edge> edges = new HashSet<>();

	public void addEdge(Edge edge) {
		edges.add(edge);
		edges.add(new Edge(edge.getNode2(), edge.getNode1()));
	}

	public boolean hasEdge(Edge edge) {
		return edges.contains(edge);
	}

	public boolean hasAllEdges(Set<Edge> inputEdges) {
		return this.edges.containsAll(inputEdges);
	}

}
