package graph.location;

import graph.connection.Connection;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class Location {
    private GeoCoordinates coordinates;
    private String name;
    private UUID id;
    private List<HashMap<UUID,UUID>> connections;
    private double radius;
    private double densityPercent;

    public Location(GeoCoordinates coordinates, String name, UUID id, List<HashMap<UUID,UUID>> connections, double radius, double densityPercent) {
        this.coordinates = coordinates;
        this.name = name;
        this.id = id;
        this.connections = connections;
        this.radius = radius;
        this.densityPercent = densityPercent;
    }


    public void addConnection(HashMap<UUID,UUID> connectionToAddId){
        connections.add(connectionToAddId);
    }


}
