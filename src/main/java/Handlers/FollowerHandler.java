package Handlers;

import DAO.BeingFollowedDAO;
import DAO.FollowingDAO;
import Services.FollowerService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.Response.FollowerResponse;
import model.request.FollowerRequest;

public class FollowerHandler implements RequestHandler<FollowerRequest,FollowerResponse> {




    @Override
    public FollowerResponse handleRequest(FollowerRequest followerRequest, Context context) {
        return new FollowerService(new BeingFollowedDAO()).getFollower(followerRequest);
    }
}
