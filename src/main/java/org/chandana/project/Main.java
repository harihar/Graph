package org.chandana.project;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Main {
	private static final PrintStream logger = System.err;

	public static void main(String[] args) {
		NodeManager nodeManager = new NodeManager();
		EdgeManager edgeManager = new EdgeManager();
		GraphReader parentGraphReader = new SimpleGraphReader("master_graph.gr", nodeManager, edgeManager);
		try {
			Graph parent = parentGraphReader.getGraph();

			Graph inputGraph = getInputGraph();

			if (parent.isIsomorphic(inputGraph, edgeManager)) {
				System.out.println("Input graph - \n" + inputGraph
						+ "\nis an isomorphic subgrapgh of - \n" + parent);
			} else {
				System.out.println("Input graph - \n" + inputGraph
						+ "\nis NOT an isomorphic subgraph of - \n" + parent);
			}
		} catch (IOException e) {
			e.printStackTrace(logger);
		}
	}

	private static Graph getInputGraph() throws IOException {
		GraphReader inputGraphReader = new SimpleGraphReader("input_graph.gr");
		return inputGraphReader.getGraph();
	}

}
