package Handlers;

import DAO.StoryDAO;
import Services.StoryService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.Response.StoryResponse;
import model.request.StoryRequest;

public class StoryHandler implements RequestHandler<StoryRequest, StoryResponse> {


    @Override
    public StoryResponse handleRequest(StoryRequest storyRequest, Context context) {
        return new StoryService(new StoryDAO()).getStory(storyRequest);

    }
}
