package graph;

import graph.connection.Connection;
import graph.location.Location;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    Random random = new Random();
    public List<Connection> generateConnections(List<Location> graphLocations) {
        Set<Connection> generatedConnections = new HashSet<>();
        int locationsCount = graphLocations.size();
        int locationsChecker = locationsCount;
        int counter = 0;
        while (locationsChecker != 0) {
            locationsChecker /= 10;
            counter++;
        }
        int connectionBase = counter;
        int connectionLimit = counter + (counter / 2);

        for (Location location : graphLocations) {
            int connectionsCount = random.nextInt(connectionLimit - connectionBase + 1) + connectionBase;
            for (int i = 0; i < connectionsCount; i++) {
                int startIndex = random.nextInt(locationsCount);
                int endIndex = random.nextInt(locationsCount);
                int traffic = random.nextInt(0,10);
                if (startIndex != endIndex) {
                    Connection connection = new Connection(graphLocations.get(startIndex).getId(), graphLocations.get(endIndex).getId(),traffic);
                    generatedConnections.add(connection);
                }
            }
        }

        return new ArrayList<>(generatedConnections);
    }

}
