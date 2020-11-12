package Services;

import DAO.AuthDAO;
import DAO.Fake.ServerFakeFactory;
import DAO.FollowingDAO;
import model.Response.FollowManipulationResult;
import model.request.FollowManipulationRequest;

public class FollowManipulationService {
    AuthDAO authToUse;
    FollowingDAO followingToUse;

    public FollowManipulationService(AuthDAO authToUse, FollowingDAO followingToUse) {
        this.authToUse = authToUse;
        this.followingToUse = followingToUse;
    }

    public FollowManipulationResult manipulateFollows(FollowManipulationRequest req){
        if(authToUse.isAuthorized(req.getPersonWhoFollows(),req.getAuthToken())){
            return followingToUse.manipulateFollows(req);
        }
        return new FollowManipulationResult(false,false);
    }
}
