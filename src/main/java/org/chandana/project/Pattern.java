package org.chandana.project;

import java.util.ArrayList;
import java.util.List;

public class Pattern {

    private final List<PatternEdge> patternEdges = new ArrayList<>();

    public void addPatternEdge(PatternEdge patternEdge) {
        patternEdges.add(patternEdge);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((patternEdges == null) ? 0 : patternEdges.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Pattern other = (Pattern) obj;
        if (patternEdges == null) {
            if (other.patternEdges != null) {
                return false;
            }
        } else if (!patternEdges.equals(other.patternEdges)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (PatternEdge edge : patternEdges) {
            result.append(edge).append(", ");
        }
        return "Pattern [" + result.substring(0, result.length() - 1) + "]";
    }

}
