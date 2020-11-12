package Handlers;

import DAO.FollowingDAO;
import Services.FollowingStatusService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.Response.FollowingStatusResponse;
import model.request.FollowingStatusRequest;

public class FollowingStatusHandler implements RequestHandler<FollowingStatusRequest,FollowingStatusResponse> {



    @Override
    public FollowingStatusResponse handleRequest(FollowingStatusRequest followingStatusRequest, Context context) {
        return new FollowingStatusService(new FollowingDAO()).getFollowingStatus(followingStatusRequest);
    }
}
