package graph.distances;

import graph.location.Location;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class LocationDistance implements Comparable<LocationDistance> {
    private UUID locationId;
    private double distance;

    public LocationDistance(UUID locationId, double distance) {
        this.locationId = locationId;
        this.distance = distance;
    }

    public UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(LocationDistance otherLocation) {
        return Double.compare(this.distance, otherLocation.getDistance());

    }
}
