package DAO;

import model.Response.FeedResponse;
import model.domain.Status;
import model.domain.User;
import model.request.FeedRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class FeedDAOTest {

    @Test
    public void getFeed() {
        User me = new User("Chase","Hiatt","18chiatt","https://s3.amazonaws.com/chasehiattbucket/images/18chiatt.png");
        User bill = new User("Bill","Science","bill","https://s3.amazonaws.com/chasehiattbucket/images/bill.png");
        User bimboJoe = new User("Joe","Bimbo","bimboJoe","https://s3.amazonaws.com/chasehiattbucket/images/bimboJoe.png");
        FeedRequest req = new FeedRequest(bill,1,null);
        FeedResponse resp =  new FeedDAO().getFeed(req);
        System.out.println(resp.getTheStatus().get(0).getMessage());

        Status previousLast = resp.getTheStatus().get(0);

        req.setPreviousLast(previousLast);
        //resp = new FeedDAO().getFeed(req);
        //System.out.println(resp.getTheStatus().get(0).getMessage());
    }
}