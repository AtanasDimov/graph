package graph.builders;


import graph.Graph;
import graph.connection.Connection;
import graph.location.Location;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GraphBuilder {


    public Graph createGraph(List<Location> graphLocations, List<Connection> graphConnections) {
        boolean locationsValidity = true;
        boolean connectionsValidity = true;

        Set<Location> locations = new HashSet<>(graphLocations);
        if (locations.size() != graphLocations.size()) {
            locationsValidity = false;
        }

        Set<Connection> connections = new HashSet<>(graphConnections);
        if (connections.size() != graphConnections.size()) {
            connectionsValidity = false;
        }

        if (locationsValidity && connectionsValidity) {
            Graph graph = new Graph(graphLocations, graphConnections);
            return graph;
        }
        return null;
    }

}

