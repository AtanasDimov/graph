package graph.connection;

import graph.connection.rules.TrafficRule;
import graph.location.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class Connection {

    private UUID startLocationId;
    private UUID finishLocationId;
    private double distance;
    private double anglePercent;
    private List<TrafficRule> ruleList = new ArrayList<>();
    private double densityPercent;
    private int trafficValue;

    public Connection(UUID startLocationId, UUID finishLocationId, int trafficValue) {
        this.startLocationId = startLocationId;
        this.finishLocationId = finishLocationId;
        this.trafficValue = trafficValue;
    }
}
