package DAO;

import DAO.Fake.ServerFakeFactory;
import model.Response.FollowManipulationResult;
import model.Response.FollowingResponse;
import model.Response.FollowingStatusResponse;
import model.request.FollowManipulationRequest;
import model.request.FollowingRequest;
import model.request.FollowingStatusRequest;

public class FollowingDAO {
    public FollowingResponse getFollowing(FollowingRequest req){
        return ServerFakeFactory.getInstance().getFollowing(req);
    }

    public FollowManipulationResult manipulateFollows(FollowManipulationRequest req){
        return ServerFakeFactory.getInstance().manipulateFollow(req);
    }

    public FollowingStatusResponse getFollowingStatus(FollowingStatusRequest req){
        return ServerFakeFactory.getInstance().getFollowingStatus(req);
    }
}
