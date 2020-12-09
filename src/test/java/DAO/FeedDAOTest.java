package DAO;

import model.Response.FeedResponse;
import model.domain.Status;
import model.domain.User;
import model.request.FeedRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class FeedDAOTest {
    FeedDAO toTest = new FeedDAO();
    User testUser = new User("Test","User","Chase","https://s3.amazonaws.com/chasehiattbucket/images/Chase.png");
    User bill = new User("Bill","Science","bill","https://s3.amazonaws.com/chasehiattbucket/images/bill.png");
    User bimboJoe = new User("Joe","Bimbo","bimboJoe","https://s3.amazonaws.com/chasehiattbucket/images/bimboJoe.png");

    @Test
    public void getFeed() {

        FeedRequest req = new FeedRequest(bill,1,null);
        FeedResponse resp =  new FeedDAO().getFeed(req);
        assertEquals(resp.getTheStatus().get(0).getSaidBy(),testUser);
        assertEquals(resp.getTheStatus().get(0).getMessage(),"Second post by yours truly :)");


        Status previousLast = resp.getTheStatus().get(0);

        req.setPreviousLast(previousLast);
        resp = new FeedDAO().getFeed(req);
        assertEquals(resp.getTheStatus().get(0).getSaidBy(),testUser);
        assertEquals(resp.getTheStatus().get(0).getMessage(), "New Post by my favorite user, Test !");
    }
}