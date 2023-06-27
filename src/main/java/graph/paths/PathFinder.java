package graph.paths;

import graph.Graph;
import graph.connection.Connection;
import graph.connection.comparators.DistanceComparator;
import graph.connection.comparators.TrafficComparator;
import graph.distances.LocationDistance;
import graph.location.Location;

import java.util.*;

public class PathFinder {
    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    private Graph graph;

    public PathFinder(Graph graph) {
        this.graph = graph;
    }
    public List<UUID> findCrossLocation(UUID startLocation, UUID finishLocation){
        Comparator<Connection> trafficComparator = new TrafficComparator();
        Comparator<Connection> distanceComparator = new DistanceComparator();

        PriorityQueue<UUID> firstQueue = new PriorityQueue<>();
        PriorityQueue<UUID> secondQueue = new PriorityQueue<>();
        Set<UUID> firstVisited = new HashSet<>();
        Set<UUID> secondVisited = new HashSet<>();
        Map<UUID, UUID> firstPrevious = new HashMap<>();
        Map<UUID, UUID> secondPrevious =  new HashMap<>();

        firstQueue.add(startLocation);
        secondQueue.add(finishLocation);
        firstVisited.add(startLocation);
        secondVisited.add(finishLocation);

        while(!firstQueue.isEmpty() && !secondQueue.isEmpty()){
            UUID firstCurrent = firstQueue.remove();
            UUID secondCurrent = secondQueue.remove();
            if (firstCurrent.equals(secondCurrent)) {
                return constructCrossPath(firstPrevious,secondPrevious,firstCurrent,secondCurrent);
            }
            List<UUID> firstLocationNeighbours = graph.getLocationNeighbours(firstCurrent);
            List<UUID> secondLocationNeighbours = graph.getLocationNeighbours(secondCurrent);

            for(UUID neighbor : firstLocationNeighbours){
                if (!firstVisited.contains(neighbor)) {
                    firstQueue.add(neighbor);
                    firstVisited.add(neighbor);
                    firstPrevious.put(neighbor,firstCurrent);
                }
            }
            for(UUID neighbor : secondLocationNeighbours){
                if (!secondVisited.contains(neighbor)) {
                    secondQueue.add(neighbor);
                    secondVisited.add(neighbor);
                    secondPrevious.put(neighbor,secondCurrent);
                }
            }

        }
        return Collections.emptyList();
    }
    public List<UUID> findResultWithDijkstra(UUID startLocation, UUID destination) {
        Map<UUID, Double> distances = new HashMap<>();
        Map<UUID, Boolean> visited = new HashMap<>();
        Map<UUID, UUID> previous = new HashMap<>();
        PriorityQueue<LocationDistance> priorityQueue = new PriorityQueue<>();

        for (Location location : graph.getLocations()) {
            if (location.getId().equals(startLocation)) {
                distances.put(location.getId(), 0.0);
            } else {
                distances.put(location.getId(), Double.POSITIVE_INFINITY);
            }
            visited.put(location.getId(), false);
            previous.put(location.getId(), null);
            priorityQueue.offer(new LocationDistance(location.getId(), distances.get(location.getId())));
        }

        while (!priorityQueue.isEmpty()) {
            LocationDistance current = priorityQueue.poll();
            UUID currentLocation = current.getLocationId();
            visited.put(currentLocation, true);

            if (currentLocation.equals(destination)) {
                return constructDijkstraPath(previous, destination);
            }

            List<UUID> neighbors = graph.getLocationNeighbours(currentLocation);
            for (UUID neighbor : neighbors) {
                if (!visited.get(neighbor)) {
                    double distanceToNeighbor = distances.get(currentLocation) + graph.getDistanceBetweenLocations(currentLocation, neighbor);

                    if (distanceToNeighbor < distances.get(neighbor)) {
                        distances.put(neighbor, distanceToNeighbor);
                        previous.put(neighbor, currentLocation);
                        priorityQueue.offer(new LocationDistance(neighbor, distanceToNeighbor));
                    }
                }
            }
        }


        return Collections.emptyList();
    }

    private List<UUID> constructDijkstraPath(Map<UUID, UUID> previous, UUID destination) {
        List<UUID> path = new ArrayList<>();
        UUID current = destination;

        do {
            path.add(0, current);
            current = previous.get(current);
        } while (current != null);

        return path;
    }
    private List<UUID> constructCrossPath(Map<UUID,UUID> startPrevious, Map<UUID,UUID> endPrevious,UUID startLocation,UUID endLocation){
        List<UUID> firstPath = new ArrayList<>();
        List<UUID> secondPath = new ArrayList<>();
        Set<UUID> visitedLocations = new HashSet<>();


        UUID current = startLocation;

        do{
            firstPath.add(0,current);
            visitedLocations.add(current);
            current= startPrevious.get(current);
        } while(current!=null);

        current= endLocation;
        do{
            if(!visitedLocations.contains(current)){
            secondPath.add(current);
            }
            current = endPrevious.get(current);
        }while(current !=null);

        firstPath.addAll(secondPath);
        return firstPath;
    }
}
