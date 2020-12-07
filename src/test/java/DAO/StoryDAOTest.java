package DAO;

import model.Response.PostStatusResponse;
import model.Response.StoryResponse;
import model.domain.Status;
import model.domain.User;
import model.request.PostStatusRequest;
import model.request.StoryRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoryDAOTest {
    User me = new User("Chase","Hiatt","18chiatt","https://s3.amazonaws.com/chasehiattbucket/images/18chiatt.png");
    User bill = new User("Bill","Science","bill","https://s3.amazonaws.com/chasehiattbucket/images/bill.png");
    User bimboJoe = new User("Joe","Bimbo","bimboJoe","https://s3.amazonaws.com/chasehiattbucket/images/bimboJoe.png");

    @Test
    public void getStory() {
        User myUser = me;
        StoryRequest req = new StoryRequest(1,myUser,null);
        StoryResponse response = new StoryDAO().getStory(req);

        Status previousLast = response.getTheStatus().get(0);
        req = new StoryRequest(1,myUser,previousLast);
        response = new StoryDAO().getStory(req);
        previousLast = response.getTheStatus().get(0);
        req = new StoryRequest(1,myUser,previousLast);
        response = new StoryDAO().getStory(req);
        System.out.println(response.getTheStatus().get(0).getMessage());
    }

    @Test
    public void postStatus() {
        User myUser = me;
        Status toPost = new Status("new message, i love bacon",  650000000L,myUser);
        PostStatusRequest req = new PostStatusRequest(toPost,"asdf");
        //new StoryDAO().postStatus(req);

        toPost = new Status("This bacon really is quite good",65000000000L, myUser);
        req = new PostStatusRequest(toPost,"asdf");
        new StoryDAO().postStatus(req);

        toPost = new Status("YetAnotherPost",700000L, myUser);
        req = new PostStatusRequest(toPost,"asdf");
        //new StoryDAO().postStatus(req);

        toPost = new Status("This is a legitimately new post",70000010L, myUser);
        req = new PostStatusRequest(toPost,"asdf");
        //new StoryDAO().postStatus(req);

        toPost = new Status("OK GOOGLE THIS POST IS EPIC!",70100010L, myUser);
        req = new PostStatusRequest(toPost,"asdf");
        //new StoryDAO().postStatus(req);

        toPost = new Status("finalPost",7000900L, myUser);
        req = new PostStatusRequest(toPost,"asdf");
        //new StoryDAO().postStatus(req);

    }
}