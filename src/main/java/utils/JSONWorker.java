package utils;

import graph.Graph;
import graph.connection.Connection;
import graph.location.Location;

import java.util.List;

public interface JSONWorker {
    public void WriteGraphToJSON(String filename, Graph graph );
    public Graph  ReadGraphFromJSON(String filename);
}
