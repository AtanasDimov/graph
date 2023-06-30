package org.example;

import graph.Generator;
import graph.Graph;
import graph.paths.PathFinder;
import graph.builders.GraphBuilder;
import graph.connection.Connection;
import graph.connection.rules.TrafficRule;
import graph.location.Location;
import utils.JSONWorkerImpl;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        JSONWorkerImpl worker = new JSONWorkerImpl();
        List<Connection> connections = new ArrayList<>();
        List<HashMap<UUID, UUID>> uuids = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        List<TrafficRule> trafficRuleList = new ArrayList<>();
        GraphBuilder builder = new GraphBuilder();
        Generator generator = new Generator();





        Graph graph = worker.ReadGraphFromJSON("test.json");
        List<Connection> graphMapConnections = graph.getConnections();
       // Collections.sort(graphMapConnections, Comparator.comparingInt(Connection::getTrafficValue));
        Collections.sort(graphMapConnections,Comparator.comparingDouble(Connection::getDistance));
        Graph graphMap = builder.createGraph(graph.getLocations(),graphMapConnections);

        graphMap.addMatchingConnections();
        graphMap.setDistanceBetweenPoints();
        graphMap.setAngleSlope();
        PathFinder finder = new PathFinder(graphMap);

        UUID pointA = graphMap.getLocations().get(5).getId();
        UUID pointB = graphMap.getLocations().get(3).getId();
        List<UUID> crossPath = finder.findCrossLocation(pointA,pointB);
        String firstLocation = graphMap.getLocationById(pointA).getName();
        String secondLocation = graphMap.getLocationById(pointB).getName();

        List<UUID> dijkstraPath = finder.findResultWithDijkstra(pointA,pointB);
        System.out.println("Nodes "+firstLocation+" & "+ secondLocation+" met on the path: ");
        for (UUID id :crossPath){
            System.out.print(graphMap.getLocationById(id).getName()+" , ");
        }
        System.out.println("");
        System.out.println("Dijkstra's path from " +firstLocation+" to "+secondLocation+" = ");
        for(UUID id : dijkstraPath){
            System.out.print(graphMap.getLocationById(id).getName()+" , ");
        }



    }
}