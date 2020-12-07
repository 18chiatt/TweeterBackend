package DAO;

import DAO.Fake.ServerFakeFactory;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Response.FeedResponse;
import model.Response.FollowerResponse;
import model.domain.Status;
import model.domain.User;
import model.request.FeedRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedDAO {
    private static final Gson gson = new GsonBuilder().create();

    public FeedResponse getFeed(FeedRequest req){


        Table table = ConnectionHolder.getTable("Feed");

        Map<String,String> nameMap = new HashMap<>();
        nameMap.put("#username","username");




        Map<String,Object> valueMap = new HashMap<>();
        valueMap.put(":username",req.getToGetFeedOf().getUserName());
        if(req.getPreviousLast() != null){
            valueMap.put(":plt",req.getPreviousLast().getTimeOfPost());
            nameMap.put("#ts","timestamp");
        }

        QuerySpec spec = new QuerySpec().withKeyConditionExpression("#username = :username").withNameMap(nameMap).withValueMap(valueMap).withMaxResultSize(req.getMaxToGet()).withScanIndexForward(false);

        if(req.getPreviousLast() != null){
            spec.withKeyConditionExpression("#username = :username and #ts < :plt");
        }




        ItemCollection<QueryOutcome> items =  table.query(spec);
        List<Status> toReturn = new ArrayList<>();

        for(Item item : items){
            Status newStatus = new Status();
            newStatus.setMessage(item.getString("message"));
            User user = gson.fromJson(item.getString("saidByJSON"),User.class);
            Long timestamp = item.getLong("timestamp");
            newStatus.setTimeOfPost(timestamp);
            newStatus.setSaidBy(user);
            toReturn.add(newStatus);
        }



        return new FeedResponse(toReturn,toReturn.size() == req.getMaxToGet());

    }

    public void removePosts(String usedToFollow,String usedToBeFollowed){
        
        //FIXME delete all feed instances written by usedToBeFollowed
        //delete via SQS
    }

    // follower,incoming.getUser(),incoming.getMessage(),incoming.getTimestamp()

    public void postStatusToFeed(User follower, User personWhoIsFollowed, String whatTheySaid, Long whenTheySaidIt, Table table){

        Item newItem = new Item().withPrimaryKey("username",follower.getUserName()).withLong("timestamp",whenTheySaidIt)
                .withString("message",whatTheySaid)
                .with("saidByJSON",gson.toJson(personWhoIsFollowed))
                .with("followerJSON",gson.toJson(follower));

        table.putItem(newItem);
    }
}
