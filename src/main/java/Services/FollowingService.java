package Services;

import DAO.FollowingDAO;
import model.Response.FollowingResponse;
import model.request.FollowingRequest;

public class FollowingService {
    private FollowingDAO toUse;

    public FollowingService(FollowingDAO toUse) {
        this.toUse = toUse;
    }

    public FollowingResponse getFollowing(FollowingRequest req){
        return toUse.getFollowing(req);
    }
}
