package DAO;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.*;
import model.Response.FollowerResponse;
import model.domain.User;
import model.request.FollowManipulationRequest;
import model.request.FollowerRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeingFollowedDAO {

    public FollowerResponse getFollower(FollowerRequest req){
        Table table = ConnectionHolder.getTable("BeingFollowed");

        Map<String,String> nameMap = new HashMap<>();
        nameMap.put("#bf","BeingFollowed");



        Map<String,Object> valueMap = new HashMap<>();
        valueMap.put(":abc",req.getWhoTheyFollow().getUserName());
        if(req.getPreviousLast() != null){
            valueMap.put(":pl",req.getPreviousLast().getUserName());
        }

        QuerySpec spec = new QuerySpec().withKeyConditionExpression("#bf = :abc").withNameMap(nameMap).withValueMap(valueMap).withMaxResultSize(req.getMaxToGet());

        if(req.getPreviousLast() != null){
            System.out.println(valueMap.get(":pl"));
            spec.withKeyConditionExpression("#bf = :abc and Following > :pl");
        }




        ItemCollection<QueryOutcome> items =  table.query(spec);
        List<String> usersToReturn = new ArrayList<>();
        for(Item item : items){
            usersToReturn.add((String) item.get("Following"));
        }
        System.out.println(usersToReturn.toString());


        return new FollowerResponse(new UserDAO().getUsers(usersToReturn),usersToReturn.size() == req.getMaxToGet());
    }

    public void manipulateFollows(FollowManipulationRequest req){

        Table table = ConnectionHolder.getTable("BeingFollowed");
        String following = req.getPersonWhoFollows().getUserName();
        String beingFollowed = req.getPersonWhoIsFollowed().getUserName();
        Item item = new Item().withPrimaryKey("BeingFollowed",beingFollowed).withString("Following",following)
                .with("beingFollowedJSON",ConnectionHolder.getGson().toJson(req.getPersonWhoIsFollowed()) )
                .with("followingObject", ConnectionHolder.getGson().toJson(req.getPersonWhoFollows()));
        if(req.isAddFollow()){
            table.putItem(item);


        } else {
            //
            // DeleteItemSpec spec = new DeleteItemSpec().withPrimaryKey("BeingFollowed",beingFollowed).with
            AmazonDynamoDB amazon = ConnectionHolder.getAmazon();
            Map<String,AttributeValue> key =  new HashMap<>();
            key.put("BeingFollowed",new AttributeValue(beingFollowed));
            key.put("Following",new AttributeValue(following));
            DeleteItemRequest delete = new DeleteItemRequest().withTableName("BeingFollowed").withKey(key);
            amazon.deleteItem(delete);
        }

    }
}
