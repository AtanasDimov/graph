package graph;

import graph.connection.Connection;
import graph.location.GeoCoordinates;
import graph.location.Location;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Graph {


    private List<Location> locations;
    private List<Connection> connections;

    public Graph(List<Location> locations, List<Connection> connections) {
        this.locations = locations;
        this.connections = connections;
    }
    public void addConnection(Connection connectionToAdd){
        connections.add(connectionToAdd);
    }
    public void addMatchingConnections() {
        for (Location location : locations) {
            List<HashMap<UUID, UUID>> idList = new ArrayList<>();
            for (Connection connection : connections) {
                if (connection.getStartLocationId().equals(location.getId()) ||
                        connection.getFinishLocationId().equals(location.getId())) {
                    HashMap<UUID, UUID> idObject = new HashMap<>();
                    idObject.put(connection.getStartLocationId(), connection.getFinishLocationId());
                    idList.add(idObject);
                }
            }
            location.setConnections(idList);
        }
    }
    public Location getLocationById(UUID id){
        for(Location location : locations){
         if(location.getId().equals(id)){
             return location;
         }
        }
        return null;
    }
    private double[] getCoordinates(Location location){
        GeoCoordinates coordinates = location.getCoordinates();
        return new double[]{coordinates.getxPosition(), coordinates.getyPosition(), coordinates.getzPosition()};
    }
    private double calculateDistance(double x1,double x2,double y1,double y2,double z1,double z2){
        double deltaX = x1 - x2;
        double deltaY = y1 - y2;
        double deltaZ = z1 - z2;
        return Math.hypot(Math.hypot(deltaX, deltaY), deltaZ);
    }
    private double calculateSlope(double x1, double x2, double y1, double y2, double z1, double z2) {
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;
        double deltaZ = z2 - z1;
        double distance = Math.hypot(Math.hypot(deltaX, deltaY), deltaZ);
        return Math.round((deltaZ / distance) * 100);
    }

    public void setDistanceBetweenPoints(){
        for(Connection connection : connections){
            Location startCoordinates = getLocationById(connection.getStartLocationId());
            Location finishCoordinates = getLocationById(connection.getFinishLocationId());

            double[] arrStartCoordinates = getCoordinates(startCoordinates);
            double[] arrFinishCoordinates = getCoordinates(finishCoordinates);

            connection.setDistance(calculateDistance(arrStartCoordinates[0],arrFinishCoordinates[0],arrStartCoordinates[1],arrFinishCoordinates[1],arrStartCoordinates[2],arrFinishCoordinates[2]));

            }
    }
    public void setAngleSlope(){
        for(Connection connection : connections){
            Location startCoordinates = getLocationById(connection.getStartLocationId());
            Location finishCoordinates = getLocationById(connection.getFinishLocationId());

            double[] arrStartCoordinates = getCoordinates(startCoordinates);
            double[] arrFinishCoordinates = getCoordinates(finishCoordinates);

            connection.setAnglePercent(calculateSlope(arrStartCoordinates[0],arrFinishCoordinates[0],arrStartCoordinates[1],arrFinishCoordinates[1],arrStartCoordinates[2],arrFinishCoordinates[2]));

        }
    }
    public List<UUID> getLocationNeighbours(UUID currentLocation) {
        List<UUID> neighbors = new ArrayList<>();
        for (Connection connection : connections) {
            if (connection.getStartLocationId().equals(currentLocation)) {
                neighbors.add(connection.getFinishLocationId());
            } else if (connection.getFinishLocationId().equals(currentLocation)) {
                neighbors.add(connection.getStartLocationId());
            }
        }
        return neighbors;
    }

    public double getDistanceBetweenLocations(UUID startLocationId, UUID finishLocationId) {
        for (Connection connection : connections) {
            if ((connection.getStartLocationId().equals(startLocationId) && connection.getFinishLocationId().equals(finishLocationId)) ||
                    (connection.getStartLocationId().equals(finishLocationId) && connection.getFinishLocationId().equals(startLocationId))) {
                return connection.getDistance();
            }
        }
        return Double.POSITIVE_INFINITY;
    }

}



