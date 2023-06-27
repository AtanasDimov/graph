package graph.connection.rules.builder;

import graph.connection.rules.TrafficRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrafficRuleBuilder {
    private double beginAt;
    private double validityDistance;
    private double value;

    private HashMap<Integer,String> ruleType = new HashMap<Integer,String>();
    private List<Integer> vehicleTypes = new ArrayList<>();

    public TrafficRuleBuilder beginAt(double beginAt){
        this.beginAt = beginAt;
        return this;

    }
    public TrafficRuleBuilder validityDistance(double validityDistance){
        this.validityDistance=validityDistance;
        return this;

    }
    public TrafficRuleBuilder value(double value){
        this.value=value;
        return this;

    }
    public TrafficRuleBuilder ruleType(HashMap<Integer,String>ruleType){
        this.ruleType=ruleType;
        return this;

    }
    public TrafficRuleBuilder vehicleTypes(List<Integer> vehicleTypes){
        this.vehicleTypes=vehicleTypes;
        return this;
    }

    public TrafficRule build(){
        return new TrafficRule(beginAt,validityDistance,value,ruleType,vehicleTypes);
    }
}
