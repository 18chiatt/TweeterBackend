package DAO;

import DAO.util.ConnectionHolder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import model.Response.LoginResponse;
import model.Response.RegisterResponse;
import model.Response.UserResponse;
import model.Response.UserStatsResponse;
import model.domain.User;
import model.request.LoginRequest;
import model.request.RegisterRequest;
import model.request.UserRequest;
import model.request.UserStatsRequest;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    //private String alias;

    public UserResponse getUser(UserRequest req){
        Table table = ConnectionHolder.getTable("User");
        Item item = table.getItem("username",req.getAlias());
        if(item == null){
            return new UserResponse(null,false);
        }
        UserResponse toRespondWith = new UserResponse(ConnectionHolder.getGson().fromJson(item.toJSON(),User.class),true);
        return toRespondWith;
    }

    public LoginResponse login(LoginRequest req){
        Table table = ConnectionHolder.getTable("User");
        System.out.println(table.getTableName());

        System.out.println(req.getUserName());
        Item item = table.getItem("username",req.getUserName());
        if(item == null){
            LoginResponse resp = new LoginResponse(null,null,false);
            return resp;
        }



        String json = item.toJSON();

        User toRespondUser = ConnectionHolder.getGson().fromJson(json,User.class);

        LoginResponse success = new LoginResponse(toRespondUser,null,true);
        System.out.println(toRespondUser.toString());
        return success;
    }

    public RegisterResponse register (RegisterRequest req,String url){
        //if(!isFree(req.getUsername())){
        //    return new RegisterResponse(false); //FIXME reenable checking
        //}
        Table table = ConnectionHolder.getTable("User");


        //username first name last name
        PutItemOutcome outcome;
        try {
            outcome = table.putItem(new Item().withPrimaryKey("username",req.getUsername())
                    .with("imageURL",url).with("firstName",req.getFirstName()).with("lastName",req.getLastName()).with("numFollowers",0).with("numPeopleFollowing",0));
        } catch(Exception e){
            System.out.println(e.getMessage());
            "".charAt(5);
        }

        return new RegisterResponse(true);
    }


    public boolean isFree(String alias){
        Table table = ConnectionHolder.getTable("User");

        Item itemMaybe = table.getItem("username",alias);
        if(itemMaybe != null){
            System.out.println("Username is taken");
            return false;
        }
        return true;
    }

    public UserStatsResponse getUserStats(UserStatsRequest req){
        Table table = ConnectionHolder.getTable("User");

        Item theItem = table.getItem("username",req.getToFindOf().getUserName());
        UserStatsResponse resp = ConnectionHolder.getGson().fromJson(theItem.toJSON(),UserStatsResponse.class);
        return resp;

    }

   /* public List<User> getUsers(List<String> usernames){
        List<User> toReturn = new ArrayList<>();
        Table table = ConnectionHolder.getTable("User");
        Gson gson = ConnectionHolder.getGson();
        for(String s : usernames){
            System.out.println(s);
            Item item = table.getItem("username",s);
            if(item == null){
                System.out.println("Unable to find correct users!");

            }
            User u = gson.fromJson(item.toJSON(),User.class);
            toReturn.add(u);
        }
        return toReturn;
    }*/

    public void incrementFollowers(String alias){
        Table table = ConnectionHolder.getTable("User");
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("username", alias)
                .withUpdateExpression("set numFollowers = numFollowers + :val")
                .withValueMap(new ValueMap().withNumber(":val", 1));
        table.updateItem(updateItemSpec);
    }

    public void incrementFollowing(String alias){
        Table table = ConnectionHolder.getTable("User");
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("username", alias)
                .withUpdateExpression("set numPeopleFollowing = numPeopleFollowing + :val")
                .withValueMap(new ValueMap().withNumber(":val", 1));
        table.updateItem(updateItemSpec);
    }

    public void decrementFollowers(String alias){
        Table table = ConnectionHolder.getTable("User");
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("username", alias)
                .withUpdateExpression("set numFollowers = numFollowers + :val")
                .withValueMap(new ValueMap().withNumber(":val", -1));
        table.updateItem(updateItemSpec);

    }

    public void decrementFollowing(String alias){
        Table table = ConnectionHolder.getTable("User");
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("username", alias)
                .withUpdateExpression("set numPeopleFollowing = numPeopleFollowing + :val")
                .withValueMap(new ValueMap().withNumber(":val", -1));
        table.updateItem(updateItemSpec);
    }

    public void registerBunch(List<RegisterRequest> toProcess, String theURL){
        List<Item> toSend = new ArrayList<>();

        for(RegisterRequest registerRequest : toProcess){
            Item newItem = new Item().withPrimaryKey("username",registerRequest.getUsername())
                    .with("imageURL",theURL).with("firstName",registerRequest.getFirstName()).with("lastName",registerRequest.getLastName()).with("numFollowers",0).with("numPeopleFollowing",0);
            toSend.add(newItem);
        }

        TableWriteItems batchWrite = new TableWriteItems("User").withItemsToPut(toSend);



    }

}
