package DAO;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConnectionHolder {
    private static DynamoDB client;
    private static AmazonDynamoDB amazon;
    private static Gson gson;

    public static DynamoDB getDB(){
        if(client == null){
            amazon = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
            client = new DynamoDB(amazon);
        }
        return client;
    }

    public static AmazonDynamoDB getAmazon(){
        if(amazon == null){
            amazon = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
            client = new DynamoDB(amazon);
        }
        return amazon;
    }

    public static Table getTable(String tableName){
        return getDB().getTable(tableName);
    }

    public static Gson getGson(){
        if(gson == null){
            gson = new GsonBuilder().create();
        }
        return gson;
    }

}
