package org.chandana.project;

import java.util.List;

public class FeatureSet {
	private List<String> names;

	public void addFeature(String name) {
		names.add(name);
	}

	public String getName(int no) {
		if (names.size() <= no)
			throw new IllegalArgumentException("The feature no is invalid. "
					+ no);
		return names.get(no);
	}
}
