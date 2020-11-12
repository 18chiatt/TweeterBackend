package DAO;

import DAO.Fake.ServerFakeFactory;
import model.Response.StoryResponse;
import model.request.StoryRequest;

public class StoryDAO {
    public StoryResponse getStory(StoryRequest req){
        return ServerFakeFactory.getInstance().getStory(req);
    }
}
