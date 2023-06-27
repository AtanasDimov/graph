package graph.connection.rules.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrafficRuleDirector {
    private HashMap<Integer,String > ruleTypeList;
    private List<Integer> vehicleTypeList;

    public void buildSpeedLimit(TrafficRuleBuilder builder,double speedLimit){
        ruleTypeList =new HashMap<>();
        ruleTypeList.put(1,"Ogranichena Skorost");
        builder.ruleType(ruleTypeList);
        builder.value(speedLimit);
    }
    public void buildNoTakeovers(TrafficRuleBuilder builder){
        ruleTypeList = new HashMap<>();
        ruleTypeList.put(2,"Zabraneno izprevarvaneto");
        builder.ruleType(ruleTypeList);
    }
    public void buildVehicleRestriction(TrafficRuleBuilder builder,int numberOfVehicles,int vehicleId){
        vehicleTypeList=new ArrayList<>();
        for(int i=0;i<numberOfVehicles;i++) {
            vehicleTypeList.add(vehicleId);
        }
        builder.vehicleTypes(vehicleTypeList);

    }
}
