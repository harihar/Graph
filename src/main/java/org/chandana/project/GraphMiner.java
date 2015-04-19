package org.chandana.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;

public class GraphMiner {

    private static final Logger logger = Logger.getLogger(GraphMiner.class);

    private final Map<Node, BreadthFirstSearcher> bfsMap = new HashMap<>();
    private final List<Node> initialSet;

    private final Set<Graph> graphSet = new HashSet<>();
    private final Map<Graph, Integer> isomorphismCount = new HashMap<>();

    private FeatureType featureType;
    private int threshold;

    private final List<String> frequentPatterns = new ArrayList<String>();

    public GraphMiner(List<Node> initialSet) {
        this.initialSet = initialSet;
        log("Initial nodes set", initialSet);
        for (Node node : initialSet) {
            bfsMap.put(node, new BreadthFirstSearcher(node));
        }
    }

    public void mine(FeatureType featureTypeToLookFor, int threshold) {
        featureType = featureTypeToLookFor;
        this.threshold = threshold;
        setupOneEdgeGraphs();

        do {
            calculateSupportCount();

            pruneInfrequentPatterns(threshold);

            saveFrequentPatternsBeforeExtension();

            extendFrequentPatterns();
        } while (!graphSet.isEmpty());

        //show only the graphs for which support count is more than threshold
        log("All frequent patterns with support count more than threshold", this.threshold);
        for (String s : frequentPatterns) {
            log(s);
        }
    }

    private void extendFrequentPatterns() {
        // Extend frequent patterns by one edge
        Iterator<Graph> itr = graphSet.iterator();
        while (itr.hasNext()) {
            Graph graph = itr.next();
            Edge nextEdge = bfsMap.get(graph.getRoot()).getNextEdge();
            if (nextEdge != null) {
                graph.addEdge(nextEdge);
                graph.getPatternEmbedding().addPatternEdge(
                        new PatternEdge(nextEdge.getNode1().getFeature(
                                        featureType), nextEdge.getNode2()
                                .getFeature(featureType)));
            } else {
                log("Edge extension over", graph, "Root", graph.getRoot());
                itr.remove();
            }
        }
    }

    private void calculateSupportCount() {
        for (Graph current : graphSet) {
            int count = 1;
            for (Graph candidate : graphSet) {
                if (!current.equals(candidate)
                        && areIsomorphic(current, candidate)) {
                    count++;
                }
            }
            isomorphismCount.put(current, count);
            log("Isomorphisim Count", "Graph", current,
                    "Pattern", current.getPatternEmbedding(), "Count", count);
        }
    }

    private void log(Object... args) {
        StringBuilder print = new StringBuilder();
        for (Object obj : args) {
            print.append(obj).append(" - ");
        }
        logger.debug(print.substring(0, print.length() - 3));
    }

    private void pruneInfrequentPatterns(int threshold) {
        Iterator<Graph> itr = graphSet.iterator();
        while (itr.hasNext()) {
            Graph g = itr.next();
            if (isomorphismCount.get(g) < threshold) {
                log("Pruned", g, "Pattern", g.getPatternEmbedding());
                itr.remove();
            }
        }
    }

    private boolean areIsomorphic(Graph g1, Graph g2) {
        return (g1.getPatternEmbedding().equals(g2.getPatternEmbedding()));
    }

    private void setupOneEdgeGraphs() {
        for (Node node : initialSet) {
            Edge firstEdge = bfsMap.get(node).getNextEdge();
            Graph graph = new Graph();
            graph.setRoot(node);
            graph.addEdge(firstEdge);

            Pattern pattern = new Pattern();
            pattern.addPatternEdge(new PatternEdge(firstEdge.getNode1()
                    .getFeature(featureType), firstEdge.getNode2().getFeature(
                            featureType)));
            graph.setPatternEmbedding(pattern);

            graphSet.add(graph);
            log("One edge graph", graph);
        }
    }

    private void saveFrequentPatternsBeforeExtension() {
        for (Graph g : isomorphismCount.keySet()) {
            int supportCount = isomorphismCount.get(g);
            if (supportCount >= this.threshold) {
                frequentPatterns.add(g + " - " + g.getPatternEmbedding() + " Count - " + supportCount);
            }
        }
    }
}
