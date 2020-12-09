package DAO;

import DAO.util.ConnectionHolder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.google.gson.Gson;
import model.Response.FollowerResponse;
import model.domain.User;
import model.request.FollowManipulationRequest;
import model.request.FollowerRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeingFollowedDAO {

    public FollowerResponse getFollower(FollowerRequest req) {
        Table table = ConnectionHolder.getTable("BeingFollowed");
        List<User> toReturn = new ArrayList<>();

        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("#bf", "BeingFollowed");


        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put(":abc", req.getWhoTheyFollow().getUserName());

        boolean doLoop = true;


        if (req.getPreviousLast() != null) {
            valueMap.put(":pl", req.getPreviousLast().getUserName());
        }

        QuerySpec spec = new QuerySpec().withKeyConditionExpression("#bf = :abc").withNameMap(nameMap).withValueMap(valueMap).withMaxResultSize(req.getMaxToGet());

        if (req.getPreviousLast() != null) {
            System.out.println(valueMap.get(":pl"));
            spec.withKeyConditionExpression("#bf = :abc and Following > :pl");
        }


        ItemCollection<QueryOutcome> items = table.query(spec);
        Gson gson = ConnectionHolder.getGson();

        for (Item item : items) {
            String json = item.getString("followingObject");
            User user = gson.fromJson(json, User.class);
            toReturn.add(user);

        }


        return new FollowerResponse(toReturn, toReturn.size() == req.getMaxToGet());
    }

    public void manipulateFollows(FollowManipulationRequest req) {

        Table table = ConnectionHolder.getTable("BeingFollowed");
        String following = req.getPersonWhoFollows().getUserName();
        String beingFollowed = req.getPersonWhoIsFollowed().getUserName();
        Item item = new Item().withPrimaryKey("BeingFollowed", beingFollowed, "Following", following)
                .with("beingFollowedJSON", ConnectionHolder.getGson().toJson(req.getPersonWhoIsFollowed()))
                .with("followingObject", ConnectionHolder.getGson().toJson(req.getPersonWhoFollows()));
        if (req.isAddFollow()) {
            table.putItem(item);


        } else {
            //
            // DeleteItemSpec spec = new DeleteItemSpec().withPrimaryKey("BeingFollowed",beingFollowed).with
            System.out.println("Being followed" + beingFollowed);
            System.out.println("Following " + following);
            DeleteItemSpec spec = new DeleteItemSpec().withPrimaryKey("BeingFollowed", beingFollowed, "Following", following);
            table.deleteItem(spec);
        }

    }


}
