package Handlers;

import DAO.AuthDAO;
import DAO.StoryDAO;
import Services.PostStatusService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.Response.PostStatusResponse;
import model.request.PostStatusRequest;

public class PostStatusHandler  implements RequestHandler<PostStatusRequest, PostStatusResponse> {


    @Override
    public PostStatusResponse handleRequest(PostStatusRequest postStatusRequest, Context context) {
        return new PostStatusService(new AuthDAO(),new StoryDAO()).postStatus(postStatusRequest);
    }
}
