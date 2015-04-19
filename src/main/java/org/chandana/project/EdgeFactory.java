package org.chandana.project;

public class EdgeFactory {

	public static Edge makeEdge(String node1Label, String node2Label) {
		return new Edge(new Node(node1Label), new Node(node2Label));
	}
}
