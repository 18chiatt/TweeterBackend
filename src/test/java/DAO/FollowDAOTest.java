package DAO;

import model.domain.User;
import model.request.FollowManipulationRequest;
import model.request.RegisterRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class FollowDAOTest {



    @Test
    public void addFollow(){
        FollowManipulationRequest req = new FollowManipulationRequest();
        User me = new User("Chase","Hiatt","18chiatt","https://s3.amazonaws.com/chasehiattbucket/images/18chiatt.png");
        User bill = new User("Bill","Science","bill","https://s3.amazonaws.com/chasehiattbucket/images/bill.png");
        User bimboJoe = new User("Joe","Bimbo","bimboJoe","https://s3.amazonaws.com/chasehiattbucket/images/bimboJoe.png");
        req.setAddFollow(true);
        req.setPersonWhoFollows(bill);
        req.setPersonWhoIsFollowed(me);
        FollowingDAO toUse = new FollowingDAO();
        toUse.manipulateFollows(req);
        req.setPersonWhoFollows(bimboJoe);
        toUse.manipulateFollows(req);
        req.setPersonWhoIsFollowed(bill);
        toUse.manipulateFollows(req);



    }
}