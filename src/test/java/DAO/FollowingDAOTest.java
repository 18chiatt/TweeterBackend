package DAO;

import model.Response.FollowingResponse;
import model.domain.User;
import model.request.FollowingRequest;
import model.request.FollowingStatusRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class FollowingDAOTest {
    User testUser = new User("Test","User","Chase","https://s3.amazonaws.com/chasehiattbucket/images/Chase.png");
    User bill = new User("Bill","Science","bill","https://s3.amazonaws.com/chasehiattbucket/images/bill.png");
    User bimboJoe = new User("Joe","Bimbo","bimboJoe","https://s3.amazonaws.com/chasehiattbucket/images/bimboJoe.png");
    FollowingDAO toTest = new FollowingDAO();

    @Test
    public void getFollowing() {
        FollowingRequest req = new FollowingRequest();
        req.setPersonWhoFollows(bill);
        req.setLimit(1);
        FollowingResponse resp = toTest.getFollowing(req);
        assertEquals(resp.getUsersTheyAreFollowing().get(0),testUser);

        req.setPersonWhoFollows(bimboJoe);
        resp = toTest.getFollowing(req);
        assertEquals(resp.getUsersTheyAreFollowing().get(0),testUser);
    }

    @Test
    public void getFollowingStatus() {
        FollowingStatusRequest request = new FollowingStatusRequest();
        request.setPersonWhoFollowsMaybe(testUser);
        request.setPersonWhoIsFollowedMaybe(bill);
        assertFalse(toTest.getFollowingStatus(request).isFollows()); //Chase doesn't follow bill

        request.setPersonWhoIsFollowedMaybe(bimboJoe);
        assertFalse(toTest.getFollowingStatus(request).isFollows()); //bimboJoe doesn't follow Bill

        request.setPersonWhoIsFollowedMaybe(bimboJoe);
        request.setPersonWhoFollowsMaybe(testUser);
        assertFalse(toTest.getFollowingStatus(request).isFollows()); //test user doesn't follow bimboJoe

        request.setPersonWhoIsFollowedMaybe(testUser);
        request.setPersonWhoFollowsMaybe(bimboJoe);
        assert(toTest.getFollowingStatus(request).isFollows()); //bimboJoe follows testUser

        request.setPersonWhoFollowsMaybe(bill);
        assert(toTest.getFollowingStatus(request).isFollows()); //bill follows Chase


    }
}