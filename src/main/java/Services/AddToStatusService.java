package Services;

import DAO.BeingFollowedDAO;
import DAO.util.ConnectionHolder;
import DAO.FeedDAO;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import model.Response.FollowerResponse;
import model.domain.User;
import model.request.FollowerRequest;
import model.request.SQSIncoming;

import java.util.ArrayList;
import java.util.List;

public class AddToStatusService {
    public AddToStatusService(BeingFollowedDAO dao, FeedDAO toUse) {
        this.dao = dao;
        this.toUse = toUse;
    }

    BeingFollowedDAO dao;
    FeedDAO toUse;

    public void handleIncoming(SQSIncoming incoming){
        DynamoDB db = ConnectionHolder.getDB();
        FollowerRequest req = new FollowerRequest(incoming.getUser(),100000,null);
        FollowerResponse followers = dao.getFollower(req);
        List<User> followersList = followers.getFollowers();
        if(followersList.size() == 0){
            return;
        }
        int maxSize = 25;
        int index = 0;
        int numIterations = followersList.size() / maxSize;

        for(int i=0; i< numIterations; i++){
            List<User> currUsers = new ArrayList<>();
            for(int j=0; j< maxSize; j++){
                currUsers.add(followersList.get(index));

                index++;
            }
            toUse.postStatusToFeed(currUsers,incoming.getUser(),incoming.getMessage(),incoming.getTimestamp(),db);
        }



        Table table = ConnectionHolder.getTable("Feed");

        while(index <= followersList.size()-1){
            toUse.postStatusToFeed(followersList.get(index),incoming.getUser(),incoming.getMessage(),incoming.getTimestamp(),table);

            index++;
        }

        if(index != followersList.size()){
            System.out.println("index:"+index);
            System.out.println("Size:"+followersList.size());
        }
    }
}
