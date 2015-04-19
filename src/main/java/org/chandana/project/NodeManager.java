package org.chandana.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeManager {
	private Map<String, Node> nodes = new HashMap<>();
	private List<NodeWithAppearence> apperanceCountNodes = new ArrayList<>();

	public void addNode(Node node) {
		nodes.put(node.getLabel(), node);
	}

	public Node getNode(String label) {
		return nodes.get(label);
	}

	public void incrementAppearanceCount(Node node) {
		NodeWithAppearence nodeWithAppearence = new NodeWithAppearence(node);
		if (apperanceCountNodes.contains(nodeWithAppearence)) {
			apperanceCountNodes.get(
					apperanceCountNodes.indexOf(nodeWithAppearence))
					.incrementAppearenceCount();
		} else {
			nodeWithAppearence.setAppearanceCount(1);
			apperanceCountNodes.add(nodeWithAppearence);
		}
	}

	public List<Node> getTopApperaingNodes(int count) {
		List<Node> result = new ArrayList<>();
		Collections.sort(apperanceCountNodes);
		Collections.reverse(apperanceCountNodes);
		for (NodeWithAppearence nodeWithAppearence : apperanceCountNodes) {
			result.add(nodeWithAppearence.getNode());
			if(result.size() == count){
				break;
			}
		}
		return result;
	}

}
