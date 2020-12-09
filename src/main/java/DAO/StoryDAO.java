package DAO;

import DAO.util.ConnectionHolder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import model.Response.PostStatusResponse;
import model.Response.StoryResponse;
import model.domain.Status;
import model.domain.User;
import model.request.PostStatusRequest;
import model.request.StoryRequest;

import java.util.*;

public class StoryDAO {
    private final String queueURL = "https://sqs.us-east-1.amazonaws.com/085816419805/StoryToFeed";
    public StoryResponse getStory(StoryRequest req){

        Table table = ConnectionHolder.getTable("Story");
        Map<String,String> nameMap =  new HashMap<>();
        nameMap.put("#username","username");


        Map<String,Object> valueMap = new HashMap<>();
        valueMap.put(":username",req.getToGetOf().getUserName());
        if(req.getPreviousLast() != null){
            valueMap.put(":plt",req.getPreviousLast().getTimeOfPost());
            nameMap.put("#timestamp","timestamp");
        }

        QuerySpec spec = new QuerySpec().withKeyConditionExpression("#username = :username").withNameMap(nameMap) .withValueMap(valueMap).withMaxResultSize(req.getMaxToGet()).withScanIndexForward(false);

        if(req.getPreviousLast() != null){
            spec.withKeyConditionExpression("#username = :username and #timestamp < :plt");
        }




        ItemCollection<QueryOutcome> items =  table.query(spec);
        List<User> users = new ArrayList<>();

        for(Item item : items){
            User newUser = new User(item.getString("firstName"),item.getString("lastName"),item.getString("username"),item.getString("imageURL"));
            users.add(newUser);
        }

        List<Status> toReturn = new ArrayList<>();
        int index =0;

        for(Item item : items){
            Long timestamp = item.getLong("timestamp");
            String message = item.getString("message");
            Status toPush = new Status(message,timestamp,users.get(index));
            toReturn.add(toPush);
            index++;
        }


        return new StoryResponse(toReturn,toReturn.size() == req.getMaxToGet());

    }

    public PostStatusResponse postStatus(PostStatusRequest validRequest){
        Map<String,String> nameMap =  new HashMap<>();
        Map<String,Object> valueMap = new HashMap<>();
        nameMap.put("#u","username");
        nameMap.put("#ts","timestamp");

        valueMap.put(":user",validRequest.getTheStatus().getSaidBy().getUserName());
        valueMap.put(":ts",validRequest.getTheStatus().getTimeOfPost());

        QuerySpec spec = new QuerySpec().withKeyConditionExpression("#u = :user and #ts = :ts").withNameMap(nameMap).withValueMap(valueMap);

        Table table = ConnectionHolder.getTable("Story");
        ItemCollection<QueryOutcome> items = table.query(spec);
        for(Item item : items){
            System.out.println(item.toJSONPretty());
        }

         if(items.getAccumulatedItemCount() > 0){
             System.out.println("we've seen this item before! it's already here");
             return new PostStatusResponse(false);
         }
         User user = validRequest.getTheStatus().getSaidBy();

         Long timestamp = validRequest.getTheStatus().getTimeOfPost();
         Item item = new Item().withPrimaryKey("username",user.getUserName()).with("timestamp",timestamp)
                 .with("message",validRequest.getTheStatus().getMessage())
                 .with("firstName", user.getFirstName())
                 .with("lastName",user.getLastName())
                 .with("imageURL",user.getImageURL());


         table.putItem(item);
        AmazonSQS client = AmazonSQSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

        SendMessageRequest req = new SendMessageRequest()
                .withQueueUrl(queueURL)
                .withMessageBody(item.toJSON());
        System.out.println("Send message " +item.toJSON());
        client.sendMessage(req);


        return new PostStatusResponse(true);
    }


}
