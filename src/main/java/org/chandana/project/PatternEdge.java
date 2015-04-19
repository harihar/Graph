package org.chandana.project;

import java.util.Objects;

public class PatternEdge {

    private final Feature feature1;
    private final Feature feature2;

    public PatternEdge(Feature feature1, Feature feature2) {
        this.feature1 = feature1;
        this.feature2 = feature2;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(feature1 == null ? null : feature1.getDesc()).append(" - ").append(feature2 == null ? null : feature2.getDesc());
        return result.toString();
    }

    private String reverseToString() {
        StringBuilder result = new StringBuilder();
        result.append(feature2 == null ? null : feature2.getDesc()).append(" - ").append(feature1 == null ? null : feature1.getDesc());
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PatternEdge other = (PatternEdge) obj;
        if (this.toString().contains("null")) {
            return false;
        }
        return this.toString().equalsIgnoreCase(other.toString())
                || this.toString().equalsIgnoreCase(other.reverseToString());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.feature1);
        hash = 31 * hash + Objects.hashCode(this.feature2);
        return hash;
    }

}
