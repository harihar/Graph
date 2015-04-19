package org.chandana.project;

import java.util.HashSet;
import java.util.Set;

public class Node {
	private final String label;
	private boolean isVisited;
	
	private final Set<Node> associates = new HashSet<>();
	private final Set<Feature> features = new HashSet<>();

	public Node(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public void addFeature(Feature feature){
		features.add(feature);
	}
	
	public Feature getFeature(FeatureType featureType){
		for(Feature feature : features){
			if(featureType.equals(feature.getFeatureType())){
				return feature;
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		Node other = (Node) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return label;
	}

	public void addAssociate(Node node2) {
		associates.add(node2);
	}

	public Set<Node> getAssociates() {
		return associates;
	}
}
