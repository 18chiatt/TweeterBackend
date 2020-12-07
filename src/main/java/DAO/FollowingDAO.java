package DAO;

import DAO.Fake.ServerFakeFactory;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import model.Response.FollowManipulationResult;
import model.Response.FollowerResponse;
import model.Response.FollowingResponse;
import model.Response.FollowingStatusResponse;
import model.request.*;

import java.util.*;

public class FollowingDAO {
    public FollowingResponse getFollowing(FollowingRequest req){

        Table table = ConnectionHolder.getTable("Following");

        Map<String,String> nameMap = new HashMap<>();
        nameMap.put("#follower","Follower");



        Map<String,Object> valueMap = new HashMap<>();
        valueMap.put(":personWhoFollows",req.getPersonWhoFollows().getUserName());
        if(req.getLastOneGotten() != null){
            valueMap.put(":pl",req.getLastOneGotten().getUserName());
        }

        QuerySpec spec = new QuerySpec().withKeyConditionExpression("#follower = :personWhoFollows").withNameMap(nameMap).withValueMap(valueMap).withMaxResultSize(req.getLimit());

        if(req.getLastOneGotten() != null){
            System.out.println(valueMap.get(":pl"));
            spec.withKeyConditionExpression("#bf = :abc and BeingFollowed > :pl");
        }




        ItemCollection<QueryOutcome> items =  table.query(spec);
        List<String> usersToReturn = new ArrayList<>();
        for(Item item : items){
            usersToReturn.add((String) item.get("Following"));
        }
        System.out.println(usersToReturn.toString());

        return new FollowingResponse(new UserDAO().getUsers(usersToReturn),usersToReturn.size() == req.getLimit());
    }

    public FollowManipulationResult manipulateFollows(FollowManipulationRequest req){
        AmazonDynamoDB client = ConnectionHolder.getAmazon();
        Table table = ConnectionHolder.getTable("Following");
        String beingFollowed = req.getPersonWhoIsFollowed().getUserName();
        String following = req.getPersonWhoFollows().getUserName();

        Map<String,String> nameMap = new HashMap<>();
        nameMap.put("#f","Follower");
        nameMap.put("#bf","BeingFollowed");

        Map<String,Object> valueMap = new HashMap<>();
        valueMap.put(":fa",following);
        valueMap.put(":bfa",beingFollowed);
        QuerySpec spec = new QuerySpec().withKeyConditionExpression("#f = :fa and #bf = :bfa").withNameMap(nameMap).withValueMap(valueMap);

        ItemCollection<QueryOutcome> items = table.query(spec);
        if(req.isAddFollow()){



            if(items.getAccumulatedItemCount() != 0){
                //if that follow already exists
                return new FollowManipulationResult(true,true);
            }

            Item newItem = new Item().withPrimaryKey("Follower",following).withString("BeingFollowed",beingFollowed)
                    .with("FollowerJSON",ConnectionHolder.getGson().toJson(req.getPersonWhoFollows()) )
                    .with("BeingFollowedJSON",ConnectionHolder.getGson().toJson(req.getPersonWhoIsFollowed()) );
            table.putItem(newItem);
            new UserDAO().incrementFollowers(beingFollowed);
            new UserDAO().incrementFollowing(following);

            

        } else {
            if(items.getAccumulatedItemCount() == 0){
                return new FollowManipulationResult(false,false);
            }

            Map<String, AttributeValue> key = new HashMap<>();
            key.put("Follower", new AttributeValue(following) );
            key.put("BeingFolowed", new AttributeValue(beingFollowed) );
            DeleteItemRequest delete = new DeleteItemRequest().withTableName("Following").withKey(key);
            client.deleteItem(delete);

            new UserDAO().decrementFollowers(beingFollowed);
            new UserDAO().decrementFollowing(following);
        }


        return new FollowManipulationResult(req.isAddFollow(),true);
    }

    public FollowingStatusResponse getFollowingStatus(FollowingStatusRequest req){
        return ServerFakeFactory.getInstance().getFollowingStatus(req);
    }

    public FollowerResponse getFollower(FollowerRequest req) {
        return ServerFakeFactory.getInstance().getFollowers(req);
    }





}
