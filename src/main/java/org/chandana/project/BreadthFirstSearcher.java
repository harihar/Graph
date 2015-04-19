package org.chandana.project;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearcher {

    private final Queue<BFSNode> nodeQueue = new LinkedList<>();

    public BreadthFirstSearcher(Node node) {
        enqueConnectedNodes(node);
    }

    private void enqueConnectedNodes(Node node) {
        for (Node n : node.getAssociates()) {
            BFSNode bfsNode = new BFSNode(n);
            bfsNode.setParentNode(node);
            nodeQueue.add(bfsNode);
        }
        node.setVisited(true);
    }

    // If a visited node is removed, its associates also have to removed
    private BFSNode getNextNode() {
        removeAllNodesVisitedByOtherGrowthPatterns();

        BFSNode result = null;
        if (!nodeQueue.isEmpty()) {
            result = nodeQueue.remove();
            enqueConnectedNodes(result.getThisNode());
        }
        return result;
    }

    private void removeAllNodesVisitedByOtherGrowthPatterns() {
        do {
            if (nodeQueue.element().getThisNode().isVisited()) {
                nodeQueue.remove();
            } else {
                break;
            }
        } while (nodeQueue.isEmpty());
    }

    public Edge getNextEdge() {
        Edge result = null;
        BFSNode node = getNextNode();
        if (node != null) {
            result = new Edge(node.getParentNode(), node.getThisNode());
        }
        return result;
    }

}
