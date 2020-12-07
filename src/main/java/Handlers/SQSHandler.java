package Handlers;

import DAO.BeingFollowedDAO;
import DAO.ConnectionHolder;
import DAO.FeedDAO;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.google.gson.Gson;
import model.Response.FollowerResponse;
import model.domain.User;
import model.request.FollowerRequest;
import model.request.SQSIncoming;

public class SQSHandler implements RequestHandler<SQSEvent,Void> {

    @Override
    public Void handleRequest(SQSEvent sqsEvent, Context context) {
        Gson gson = ConnectionHolder.getGson();

        for(SQSEvent.SQSMessage message : sqsEvent.getRecords()){
             String body = message.getBody();
             System.out.println(body);
             SQSIncoming incoming = gson.fromJson(body,SQSIncoming.class);
             handleBody(incoming);



        }
        return null;
    }
    public void handleBody(SQSIncoming incoming){
        BeingFollowedDAO dao =  new BeingFollowedDAO();
        FollowerRequest req = new FollowerRequest(incoming.getUser(),100000,null);
        FollowerResponse followers = dao.getFollower(req);

        FeedDAO toUse = new FeedDAO();
        Table table = ConnectionHolder.getTable("Feed");
        for(User follower : followers.getFollowers()){
            toUse.postStatusToFeed(follower,incoming.getUser(),incoming.getMessage(),incoming.getTimestamp(),table);
        }




        //get all users who follow incoming.username
        //create new "Feed" entry of
        //username = follower
        //timestamp = timeOf incoming status
        // message = text
        //saidBy = JSON of saidBy
    }

}

