package DAO;

import DAO.util.ConnectionHolder;
import Generators.SaltMines;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import model.domain.User;
import model.request.LoginRequest;
import model.request.RegisterRequest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class AuthDAO {
    private static final long secondsUntilExpiration = 1000;
    public boolean isAuthorized(User u, String authToken){
        Table table = ConnectionHolder.getTable("Auth");

        Item item = getMostRecentAuthToken(u.getUserName());



        if(item == null){
            return false;
        }
        System.out.println("Found item in database!" + item.toJSONPretty());

        return item.getString("authToken").equals(authToken) &&  item.getLong("expirationSecond") > (System.currentTimeMillis()/1000);
    }

    public void refresh(String authToken,User user){
        long seconds = (System.currentTimeMillis()/1000);

        Table table = ConnectionHolder.getTable("Auth");




        Item item = getMostRecentAuthToken(user.getUserName());
        if(item == null){
            return;
        }




        Long newExpr = (System.currentTimeMillis()/1000) +secondsUntilExpiration;
        Item newItem = new Item().withPrimaryKey("username",user.getUserName(),"expirationSecond",newExpr).withString("authToken",item.getString("authToken"));

        table.putItem(newItem);
    }

    public boolean correctPassword(LoginRequest req){
        //login
        //get salt out of database
        Item passwordItem = ConnectionHolder.getTable("Password").getItem("username",req.getUserName());
        if(passwordItem == null){
            System.out.println("Unable to find user's hash/salt in database");
            return false;
        }
        String hash = passwordItem.getString("hash");

        String salt = passwordItem.getString("salt");
        String theirHash = getHash(req.getPassword(),salt);

        return theirHash.equals(hash);

    }

    public void register(RegisterRequest req){
        if(ConnectionHolder.getTable("Password").getItem("username",req.getUsername()) == null){
            createAuth(req.getUsername(),req.getPassword());
        }

        //add to table
    }

    private void createAuth(String username, String password){
        String salt = SaltMines.getSalt(20);


        String passwordHash = getHash(password,salt);

        Item itemToPut = new Item().withPrimaryKey("username",username)
                .withString("salt",salt)
                .withString("hash",passwordHash);

        ConnectionHolder.getTable("Password").putItem(itemToPut);
        return;

    }

    private String getHash(String password, String salt){
        String toHash = password+salt;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(toHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder builder = new StringBuilder();
            for(byte aByte : bytes){
                builder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return builder.toString();


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "UNABLE TO HASH";

    }

    public String getCreateAuthToken(LoginRequest req) {

        Table table = ConnectionHolder.getTable("Auth");
        Item item = getMostRecentAuthToken(req.getUserName());
        if( item != null){
            return item.getString("authToken");
        }

        String authToken = SaltMines.getSalt(20);
        long expirationTime = (System.currentTimeMillis()/1000) + secondsUntilExpiration;
        Item newItem = new Item().withPrimaryKey("username",req.getUserName())
                .withNumber("expirationSecond",expirationTime)
                .withString("authToken",authToken);
        table.putItem(newItem);
        return authToken;

    }

    private Item getMostRecentAuthToken(String username){
        Table table = ConnectionHolder.getTable("Auth");

        Map<String,String> nameMap = new HashMap<>();
        nameMap.put("#username","username");
        nameMap.put("#expiration","expirationSecond");

        Map<String,Object>valueMap = new HashMap<>();
        valueMap.put(":username",username);
        valueMap.put(":curr",(System.currentTimeMillis()/1000));

        QuerySpec spec = new QuerySpec().withKeyConditionExpression("#username = :username and #expiration > :curr").withNameMap(nameMap).withValueMap(valueMap).withScanIndexForward(false);

        ItemCollection<QueryOutcome> outcomes = table.query(spec);

        for(Item i : outcomes){
            return i; //"for loop" lol, just returns authToken of most recent item
        }
        return null;

    }
}
