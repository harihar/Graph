package org.chandana.project;

import java.io.IOException;
import java.io.PrintStream;

public class FacebookMain {

    private final static PrintStream LOGGER = System.err;
    private final static String FILE_PATH = "C:/Users/Harihar/Desktop/Graph Mining/Standford dataset/facebook/";

    public static void main(String[] args) {
        String[] featureNames = {"birthday", "education;classes", "education;concentration",
            "education;degree", "education;school", "education;type", "education;with",
            "education;year", "first_name", "gender", "hometown", "languages", "last_name",
            "locale", "location", "middle_name", "work;employer", "work;end_date",
            "work;location", "work;position", "work;projects", "work;start_date"};

        GraphReader faceBookGraphReader = new FaceBookGraphReader(FILE_PATH
                + "1912");//1912
        try {
            faceBookGraphReader.getGraph();
            NodeManager nodeManager = faceBookGraphReader.getNodeManager();
            GraphMiner graphMiner = new GraphMiner(nodeManager.getTopApperaingNodes(20));
            graphMiner.mine(new FeatureType(featureNames[5]), 3);
        } catch (IOException e) {
            e.printStackTrace(LOGGER);
        }
    }
}
