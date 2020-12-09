package DAO;

import Generators.WordGenerator;
import model.Response.PostStatusResponse;
import model.Response.StoryResponse;
import model.domain.Status;
import model.domain.User;
import model.request.PostStatusRequest;
import model.request.StoryRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoryDAOTest {
    User testUser = new User("Story","Man","Story","https://chasehiattbucket.s3.amazonaws.com/images/Story.png");
    User myUser = new User("Test","User","Chase","https://s3.amazonaws.com/chasehiattbucket/images/Chase.png");
    StoryDAO toTest = new StoryDAO();


    @Test
    public void getStory() {


        StoryRequest req = new StoryRequest(1,myUser,null);
        StoryResponse resp = toTest.getStory(req);
        assertEquals(resp.getTheStatus().get(0).getSaidBy(),myUser);
        assert(resp.getTheStatus().get(0).getMessage().equals("Second post by yours truly :)"));
        req.setPreviousLast(resp.getTheStatus().get(0));

        resp = toTest.getStory(req);
        assertEquals(resp.getTheStatus().get(0).getSaidBy(),myUser);
        assert(resp.getTheStatus().get(0).getMessage().equals("New Post by my favorite user, Test !"));


    }

    @Test
    public void postStatus() {

        StringBuilder builder = new StringBuilder();
        for(int i=0; i< 10; i++){
            builder.append(WordGenerator.getWord());
        }


        Status newStatus = new Status(builder.toString(),System.currentTimeMillis()/1000,testUser);
        PostStatusRequest request = new PostStatusRequest();
        request.setTheStatus(newStatus);

        request.setAuthToken("fakeAuth LOL");
        PostStatusResponse response =  toTest.postStatus(request);
        assert(response.isWasSuccess());

        StoryRequest storyRequest = new StoryRequest(1,testUser,null);
        StoryResponse storyResponse = toTest.getStory(storyRequest);
        assertEquals(storyResponse.getTheStatus().get(0).getSaidBy(),testUser);
        assertEquals(storyResponse.getTheStatus().get(0).getMessage(),builder.toString());


    }
}