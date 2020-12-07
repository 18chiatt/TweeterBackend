package Services;

import DAO.AuthDAO;
import DAO.BeingFollowedDAO;
import DAO.FollowingDAO;
import model.Response.FollowManipulationResult;
import model.request.FollowManipulationRequest;

public class FollowManipulationService {
    AuthDAO authToUse;
    FollowingDAO followingToUse;
    BeingFollowedDAO beingFollowedToUse;

    public FollowManipulationService(AuthDAO authToUse, FollowingDAO followingToUse, BeingFollowedDAO beingFollowed) {
        this.authToUse = authToUse;
        this.followingToUse = followingToUse;
        this.beingFollowedToUse = beingFollowed;
    }

    public FollowManipulationResult manipulateFollows(FollowManipulationRequest req){
        if(authToUse.isAuthorized(req.getPersonWhoFollows(),req.getAuthToken())){
            beingFollowedToUse.manipulateFollows(req);
            return followingToUse.manipulateFollows(req);
        }
        return new FollowManipulationResult(false,false);
    }
}
