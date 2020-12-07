package Services;


import DAO.FollowingDAO;
import model.Response.FollowingStatusResponse;
import model.request.FollowingStatusRequest;

public class FollowingStatusService {
    FollowingDAO toUse;

    public FollowingStatusService(FollowingDAO toUse) {
        this.toUse = toUse;
    }

    public FollowingStatusResponse getFollowingStatus(FollowingStatusRequest req){
        return toUse.getFollowingStatus(req);
    }
}
