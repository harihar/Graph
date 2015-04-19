package org.chandana.project;

public class NodeWithAppearence implements Comparable<NodeWithAppearence>{

	private final Node node;
	private int appearanceCount = 0;
	
	public NodeWithAppearence(Node node) {
		this.node = node;
	}

	public int getAppearanceCount() {
		return appearanceCount;
	}

	public void setAppearanceCount(int appearanceCount) {
		this.appearanceCount = appearanceCount;
	}
	
	public void incrementAppearenceCount(){
		appearanceCount++;
	}
	
	public Node getNode() {
		return node;
	}

	@Override
	public int compareTo(NodeWithAppearence o) {
		return this.appearanceCount - o.appearanceCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeWithAppearence other = (NodeWithAppearence) obj;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		return true;
	}
	
}
