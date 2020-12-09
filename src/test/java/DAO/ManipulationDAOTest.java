package DAO;

import model.Response.FollowManipulationResult;
import model.domain.User;
import model.request.FollowManipulationRequest;
import model.request.FollowingStatusRequest;
import org.junit.Test;

public class ManipulationDAOTest {
    User testUser = new User("Test","User","Chase","https://s3.amazonaws.com/chasehiattbucket/images/Chase.png");
    User bill = new User("Bill","Science","bill","https://s3.amazonaws.com/chasehiattbucket/images/bill.png");
    User bimboJoe = new User("Joe","Bimbo","bimboJoe","https://s3.amazonaws.com/chasehiattbucket/images/bimboJoe.png");
    ManipulationDAO dao = new ManipulationDAO();

    @Test
    public void manipulate() {



        FollowManipulationRequest req = new FollowManipulationRequest();
        req.setAddFollow(true);
        req.setPersonWhoFollows(bill);
        req.setPersonWhoIsFollowed(testUser);
        dao.manipulate(req);

        req.setPersonWhoFollows(bimboJoe);
        dao.manipulate(req);

        // make bill and joe follow Chase
        //make sure they are following

        assert(isFollowing(bill,testUser));
        assert(isFollowing(bimboJoe,testUser));

        //remove the follows

        req.setAddFollow(false);
        req.setPersonWhoFollows(bill);
        req.setPersonWhoIsFollowed(testUser);
        FollowManipulationResult resp = dao.manipulate(req);

        assert(!isFollowing(bill,testUser));
        req.setAddFollow(true);
        dao.manipulate(req);
        assert(isFollowing(bill,testUser));



    }

    private boolean isFollowing(User follower, User beingFollowed){
        FollowingStatusRequest req = new FollowingStatusRequest(follower,beingFollowed);
        return new FollowingDAO().getFollowingStatus(req).isFollows();

    }


}