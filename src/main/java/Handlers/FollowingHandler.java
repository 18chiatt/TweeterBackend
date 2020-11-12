package Handlers;

import DAO.FollowingDAO;
import Services.FollowerService;
import Services.FollowingService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.Response.FollowingResponse;
import model.request.FollowingRequest;


public class FollowingHandler implements RequestHandler<FollowingRequest,FollowingResponse> {







    @Override
    public FollowingResponse handleRequest(FollowingRequest followingRequest, Context context) {
        return new FollowingService(new FollowingDAO()).getFollowing(followingRequest);
    }
}