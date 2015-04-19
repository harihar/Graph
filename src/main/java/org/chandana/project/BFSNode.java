package org.chandana.project;

public class BFSNode {
	private Node thisNode;
	private Node parentNode;
	
	public BFSNode(Node thisNode){
		this.thisNode = thisNode;
	}
	
	public Node getThisNode() {
		return thisNode;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((thisNode == null) ? 0 : thisNode.hashCode());
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
		BFSNode other = (BFSNode) obj;
		if (thisNode == null) {
			if (other.thisNode != null)
				return false;
		} else if (!thisNode.equals(other.thisNode))
			return false;
		return true;
	}
	
}
