package org.chandana.project;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class FaceBookGraphReader implements GraphReader {

    private static final Logger logger = Logger.getLogger(FaceBookGraphReader.class);

    private final String filePrefix;
    private final NodeManager nodeManager;
    private final EdgeManager edgeManager;

    public FaceBookGraphReader(String filePrefix) {
        this.filePrefix = filePrefix;
        this.nodeManager = new NodeManager();
        this.edgeManager = new EdgeManager();
    }

    @Override
    public Graph getGraph() throws IOException {
        File file = new File(filePrefix + ".featnames");
        FeatureManager.readFetureSet(file);
        readNodes();
        Graph result = new Graph();
        readEdges(result);

        return result;
    }

    @SuppressWarnings("unchecked")
    private void readEdges(Graph graph) throws IOException {
        Iterator<String> fileIterator = FileUtils.lineIterator(new File(
                filePrefix + ".edges"));
        while (fileIterator.hasNext()) {
            String[] nodeLabels = fileIterator.next().split(" ");
            Node node1 = nodeManager.getNode(nodeLabels[0]);
            Node node2 = nodeManager.getNode(nodeLabels[1]);
            nodeManager.incrementAppearanceCount(node1);
            nodeManager.incrementAppearanceCount(node2);
            Edge edge = new Edge(node1, node2);
            edgeManager.addEdge(edge);
            graph.addEdge(edge);
            logger.info("Added edge - " + edge);
        }
    }

    @SuppressWarnings("unchecked")
    private void readNodes() throws IOException {
        Iterator<String> fileIterator = FileUtils.lineIterator(new File(
                filePrefix + ".feat"));
        while (fileIterator.hasNext()) {
            String[] features = fileIterator.next().split(" ");
            Node node = new Node(features[0]);
            for (int i = 1; i < features.length; i++) {
                if ("1".equals(features[i])) {
                    node.addFeature(FeatureManager.getFeature(String
                            .valueOf(i - 1)));
                }
            }
            nodeManager.addNode(node);
            logger.info("Added node - " + node);
        }
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
