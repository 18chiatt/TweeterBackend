package Services;

import DAO.FollowerDAO;
import model.Response.FollowerResponse;
import model.request.FollowerRequest;

public class FollowerService {
    public FollowerService(FollowerDAO toUse) {
        this.toUse = toUse;
    }

    private FollowerDAO toUse;
    public FollowerResponse getFollower(FollowerRequest req){

        return toUse.getFollower(req);
    }
}
