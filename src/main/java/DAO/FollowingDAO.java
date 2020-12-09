package DAO;

import DAO.util.ConnectionHolder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.google.gson.Gson;
import model.Response.FollowManipulationResult;
import model.Response.FollowingResponse;
import model.Response.FollowingStatusResponse;
import model.domain.User;
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
        List<User> toReturn = new ArrayList<>();
        Gson gson = ConnectionHolder.getGson();
        for(Item item : items){
            String json = item.getString("BeingFollowedJSON");
            User newUser = gson.fromJson(json,User.class);
            toReturn.add(newUser);
        }
        System.out.println(usersToReturn.toString());

        return new FollowingResponse(toReturn,usersToReturn.size() == req.getLimit());
    }

    public FollowManipulationResult manipulateFollows(FollowManipulationRequest req){
        String following = req.getPersonWhoFollows().getUserName();
        String beingFollowed = req.getPersonWhoIsFollowed().getUserName();

        Table table = ConnectionHolder.getTable("Following");

        Map<String,String> nameMap = new HashMap<>();
        nameMap.put("#follower","Follower");
        nameMap.put("#beingFollowed","BeingFollowed");

        Map<String,Object> valueMap = new HashMap<>();
        valueMap.put(":followerAlias",req.getPersonWhoFollows().getUserName());
        valueMap.put(":beingFollowedAlias",req.getPersonWhoIsFollowed().getUserName());
        QuerySpec spec = new QuerySpec().withKeyConditionExpression("#follower = :followerAlias and #beingFollowed = :beingFollowedAlias").withNameMap(nameMap).withValueMap(valueMap);

        ItemCollection<QueryOutcome> items = table.query(spec);

        int count =0;
        for(Item i : items){
            count +=1;
            break;
        }

        if(req.isAddFollow()){



            if(count != 0){
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
            if(count == 0){
                System.out.println("Unable to find the follow!");
                return new FollowManipulationResult(false,false);
            }


            DeleteItemSpec delSpec = new DeleteItemSpec().withPrimaryKey("Follower",following,"BeingFollowed",beingFollowed);
            table.deleteItem(delSpec);

            new UserDAO().decrementFollowers(beingFollowed);
            new UserDAO().decrementFollowing(following);
        }


        return new FollowManipulationResult(req.isAddFollow(),true);
    }

    public FollowingStatusResponse getFollowingStatus(FollowingStatusRequest req){
        User followsMaybe= req.getPersonWhoFollowsMaybe();
        User followedMaybe = req.getPersonWhoIsFollowedMaybe();
        Table table = ConnectionHolder.getTable("Following");

        Map<String,String> nameMap = new HashMap<>();
        nameMap.put("#follower","Follower");
        nameMap.put("#beingFollowed","BeingFollowed");

        Map<String,Object> valueMap = new HashMap<>();
        valueMap.put(":followerAlias",followsMaybe.getUserName());
        valueMap.put(":beingFollowedAlias",followedMaybe.getUserName());
        QuerySpec spec = new QuerySpec().withKeyConditionExpression("#follower = :followerAlias and #beingFollowed = :beingFollowedAlias").withNameMap(nameMap).withValueMap(valueMap);

        ItemCollection<QueryOutcome> items = table.query(spec);
        for(Item item : items){
            return new FollowingStatusResponse(true);
        }
        return new FollowingStatusResponse(false);



    }







}
