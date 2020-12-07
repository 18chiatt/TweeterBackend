package DAO;

import model.domain.User;
import model.request.FollowManipulationRequest;
import org.junit.Test;

public class ManipulationDAOTest {

    @Test
    public void manipulate() {

        User me = new User("Chase","Hiatt","18chiatt","https://s3.amazonaws.com/chasehiattbucket/images/18chiatt.png");
        User bill = new User("Bill","Science","bill","https://s3.amazonaws.com/chasehiattbucket/images/bill.png");
        User bimboJoe = new User("Joe","Bimbo","bimboJoe","https://s3.amazonaws.com/chasehiattbucket/images/bimboJoe.png");
        ManipulationDAO dao = new ManipulationDAO();
        FollowManipulationRequest req = new FollowManipulationRequest();
        req.setAddFollow(true);
        req.setPersonWhoFollows(bill);
        req.setPersonWhoIsFollowed(me);
        dao.manipulate(req);

        req.setPersonWhoFollows(bimboJoe);
        dao.manipulate(req);

    }
}