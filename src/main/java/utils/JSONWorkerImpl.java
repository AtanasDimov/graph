package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graph.GeneratorRules;
import graph.Graph;
import graph.connection.Connection;
import graph.location.Location;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSONWorkerImpl implements JSONWorker{//todo:google library
    private final String directoryPath = "C:\\Users\\Atanas\\IdeaProjects\\GraphProject\\src\\json\\";
    public void WriteGraphToJSON(String fileName, Graph graph) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(directoryPath + fileName);
            gson.toJson(graph,writer);
            writer.close();
        }catch (Exception e){}


    }

    ;

    public Graph ReadGraphFromJSON(String fileName){
        Gson gson = new GsonBuilder().create();
        try (BufferedReader reader = new BufferedReader(new FileReader(directoryPath+fileName))){
            Graph graph = gson.fromJson(reader, Graph.class);
            return graph;
        }catch (Exception e){}
        return null;
    }
    public GeneratorRules ReadRulesFromJSON(String fileName){
        Gson gson = new GsonBuilder().create();
        try (BufferedReader reader = new BufferedReader(new FileReader(directoryPath+fileName))){
            GeneratorRules rules = gson.fromJson(reader, GeneratorRules.class);
            return rules;
        }catch (Exception e){}
        return null;
    }
}
/* List<Location> locationsList = graph.getLocations();
        List<Connection> connectionsList = graph.getConnections();
        JSONObject object = new JSONObject();
        JSONArray locationsJSON = new JSONArray();

        for(Location location : locationsList) {
            JSONObject locationJSON = new JSONObject();
            locationJSON.put("name", location.getName());
            JSONArray locationConnections = new JSONArray();
            for (Connection connection : location.getConnections()) {
                JSONObject locationConnectionJSON = new JSONObject();
                locationConnectionJSON.put("startLocation", connection.getStartLocationId().getName());
                locationConnectionJSON.put("finishLocation", connection.getFinishLocation().getName());
                locationConnections.add(locationConnectionJSON);
            }
            locationJSON.put("connections",locationConnections);
            locationsJSON.add(locationJSON);
        }
            JSONArray connectionsJSON = new JSONArray();
            for(Connection connection: connectionsList){
                JSONObject connectionJSON = new JSONObject();
                connectionJSON.put("startLocation", connection.getStartLocationId().getName());
                connectionJSON.put("finishLocation", connection.getFinishLocation().getName());
                connectionsJSON.add(connectionJSON);
            }
            object.put("locations",locationsJSON);
            object.put("connections",connectionsJSON);


           try {
            FileWriter writer = new FileWriter(directoryPath + fileName, false);
            writer.write(object.toJSONString());
            writer.close();
        }catch (Exception e){}
        }

 */