package DAO;

import DAO.Fake.ServerFakeFactory;
import model.Response.FollowerResponse;
import model.request.FollowerRequest;

public class FollowerDAO {
    public FollowerResponse getFollower(FollowerRequest req) {
        return ServerFakeFactory.getInstance().getFollowers(req);
    }
}
