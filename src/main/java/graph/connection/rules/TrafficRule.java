package graph.connection.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class TrafficRule {
    private final double beginAt;
    private final double validityDistance;
    private final double value;

    private HashMap<Integer,String> ruleType = new HashMap<Integer,String>();;
    private  List<Integer> vehicleTypes = new ArrayList<>();

    public TrafficRule(double beginAt, double validityDistance, double value, HashMap<Integer,String> ruleType, List<Integer> vehicleTypes) {
        this.beginAt = beginAt;
        this.validityDistance = validityDistance;
        this.value = value;
        this.ruleType = ruleType;
        this.vehicleTypes = vehicleTypes;
    }
}
