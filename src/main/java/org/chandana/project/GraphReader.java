package org.chandana.project;

import java.io.IOException;

public interface GraphReader {

	public abstract Graph getGraph() throws IOException;

	public abstract NodeManager getNodeManager();

	public abstract EdgeManager getEdgeManager();

}