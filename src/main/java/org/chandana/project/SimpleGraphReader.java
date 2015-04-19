package org.chandana.project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class SimpleGraphReader implements GraphReader {

    private static final PrintStream logger = System.err;
    private BufferedReader fileReader;
    private NodeManager nodeManager;
    private EdgeManager edgeManager;

    public SimpleGraphReader(String fileName, NodeManager nodeManager,
            EdgeManager edgeManager) {
        try {
            fileReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace(logger);
        }
        this.nodeManager = nodeManager;
        this.edgeManager = edgeManager;
    }

    public SimpleGraphReader(String string) {
        this(string, new NodeManager(), new EdgeManager());
    }

    @Override
    public Graph getGraph() throws IOException {
        Graph result = null;
        String line = fileReader.readLine();
        while (line != null) {
            if (!line.startsWith("#")) {
                String[] parts = line.split("[ \t]");
                if ("n".equalsIgnoreCase(parts[0])) {
                    //nodeManager.addNode(new Node(Integer.parseInt(parts[1])));
                } else if ("e".equalsIgnoreCase(parts[0])) {
                    if (result == null) {
                        result = new Graph();
                    }
                    Edge newEdge = new Edge(nodeManager.getNode(parts[1]),
                            nodeManager.getNode(parts[2]));
                    result.addEdge(newEdge);
                    edgeManager.addEdge(newEdge);
                }
            }
            line = fileReader.readLine();
        }
        return result;
    }

    @Override
    public NodeManager getNodeManager() {
        return nodeManager;
    }

    @Override
    public EdgeManager getEdgeManager() {
        return edgeManager;
    }

}
