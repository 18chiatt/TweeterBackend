package DAO;

import model.Response.FollowerResponse;
import model.domain.User;
import model.request.FollowerRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeingFollowedDAOTest {

    @Test
    public void getFollower() {
        User testUser = new User("Test","User","Chase","https://s3.amazonaws.com/chasehiattbucket/images/Chase.png");
        User bill = new User("Bill","Science","bill","https://s3.amazonaws.com/chasehiattbucket/images/bill.png");
        User bimboJoe = new User("Joe","Bimbo","bimboJoe","https://s3.amazonaws.com/chasehiattbucket/images/bimboJoe.png");

        BeingFollowedDAO toTest = new BeingFollowedDAO();

        FollowerRequest req = new FollowerRequest(testUser,1,null);
        //Chase is followed by both bill and bimboJoe
        FollowerResponse resp1 = toTest.getFollower(req);
        assert(resp1.getFollowers().get(0).equals(bill) ||resp1.getFollowers().get(0).equals(bimboJoe) );

        req.setPreviousLast(resp1.getFollowers().get(0));
        FollowerResponse resp2 = toTest.getFollower(req);
        assert( !resp2.getFollowers().get(0).equals(resp1.getFollowers().get(0)));
        assert(resp2.getFollowers().get(0).equals(bill) ||resp2.getFollowers().get(0).equals(bimboJoe) );


    }

    @Test
    public void getLots(){
        User testUser = new User("Chase","Hiatt","18chiatt","https://s3.amazonaws.com/chasehiattbucket/images/18chiatt.png");
        FollowerRequest newRequest = new FollowerRequest(testUser,100000,null);
        System.out.println(new BeingFollowedDAO().getFollower(newRequest).getFollowers().size()) ;
    }
}