package org.chandana.project;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class FeatureManager {

	private static Map<String, Feature> featureMap = new HashMap<>();

	public static Feature getFeature(String id) {
		return featureMap.get(id);
	}

	@SuppressWarnings("unchecked")
	public static void readFetureSet(File file) throws IOException {
		Iterator<String> fileIterator = FileUtils.lineIterator(file);
		while (fileIterator.hasNext()) {
			String line = fileIterator.next();
			String[] parts = line.split(" ", 2);
			String id = parts[0];
			String featureType;
			String featureValue;
			if(parts[1].startsWith("education") || parts[1].startsWith("work")){
				parts = parts[1].split(";", 3);
				featureType = parts[0]+";"+parts[1];
				featureValue = parts[2];
			}
			else{
				featureType = parts[0];
				featureValue = parts[1];
			}
			featureMap.put(id, new Feature(id, featureValue, new FeatureType(featureType)));
		}
	}

}
