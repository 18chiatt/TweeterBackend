package DAO;

import model.domain.User;
import model.request.FollowManipulationRequest;
import model.request.FollowerRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeingFollowedDAOTest {

    @Test
    public void manipulateFollows() {
        FollowManipulationRequest req = new FollowManipulationRequest();
        req.setPersonWhoIsFollowed(new User("Chase","Hiatt","18chiatt","https://s3.amazonaws.com/chasehiattbucket/images/18chiatt.png"));

    }

    @Test
    public void getFollowers(){
        FollowerRequest req = new FollowerRequest();
        req.setMaxToGet(1);
        req.setWhoTheyFollow(new User("Chase","Hiatt","18chiatt","https://s3.amazonaws.com/chasehiattbucket/images/18chiatt.png"));
        //new BeingFollowedDAO().getFollower(req);
        req.setPreviousLast(new User(null,null,"bill",null));
        System.out.println("Hopefully sugma");
        //new BeingFollowedDAO().getFollower(req);
    }
}