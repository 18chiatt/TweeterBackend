package Services;

import DAO.BeingFollowedDAO;
import DAO.FollowingDAO;
import model.Response.FollowerResponse;
import model.request.FollowerRequest;

public class FollowerService {
    public FollowerService(BeingFollowedDAO toUse) {
        this.toUse = toUse;
    }

    private BeingFollowedDAO toUse;
    public FollowerResponse getFollower(FollowerRequest req){

        return toUse.getFollower(req);
    }
}
