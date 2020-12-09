package DAO;

import DAO.util.ConnectionHolder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Response.FeedResponse;
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



    // follower,incoming.getUser(),incoming.getMessage(),incoming.getTimestamp()

    public void postStatusToFeed(User follower, User personWhoIsFollowed, String whatTheySaid, Long whenTheySaidIt, Table table){

        Item newItem = new Item().withPrimaryKey("username",follower.getUserName()).withLong("timestamp",whenTheySaidIt)
                .withString("message",whatTheySaid)
                .with("saidByJSON",gson.toJson(personWhoIsFollowed))
                .with("followerJSON",gson.toJson(follower))
                .with("saidBy",personWhoIsFollowed.getUserName());

        table.putItem(newItem);
    }

    public void postStatusToFeed(List<User> users, User personWhoIsFollowed, String whatTheySaid, Long whenTheySaidIt, DynamoDB db)  {

        if(users.size() > 25){
            System.out.println("BAD ERROR");
        }
        List<Item> toPut =  new ArrayList<>();

        for(User curr : users){
            Item newItem  = new Item().withPrimaryKey("username",curr.getUserName()).withLong("timestamp",whenTheySaidIt)
                    .withString("message",whatTheySaid)
                    .with("saidByJSON",gson.toJson(personWhoIsFollowed))
                    .with("followerJSON",gson.toJson(curr))
                    .with("saidBy",personWhoIsFollowed.getUserName());
            toPut.add(newItem);

        }


        TableWriteItems batchWrite = new TableWriteItems("Feed").withItemsToPut(toPut);
        BatchWriteItemOutcome outcome = db.batchWriteItem(batchWrite);
        long toSleep = 5;

        while(outcome.getUnprocessedItems().size() > 0){
            try {
                Thread.sleep(toSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            outcome = db.batchWriteItemUnprocessed(outcome.getUnprocessedItems());
            toSleep *=2;
        }



    }
}
