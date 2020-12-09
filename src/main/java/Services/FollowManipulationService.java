package Services;

import DAO.AuthDAO;
import DAO.BeingFollowedDAO;
import DAO.FollowingDAO;
import DAO.ManipulationDAO;
import model.Response.FollowManipulationResult;
import model.request.FollowManipulationRequest;

public class FollowManipulationService {
    AuthDAO authToUse;
    ManipulationDAO manip;

    public FollowManipulationService(AuthDAO authToUse, ManipulationDAO manip) {
        this.authToUse = authToUse;
        this.manip = manip;
    }

    public FollowManipulationResult manipulateFollows(FollowManipulationRequest req){
        if(authToUse.isAuthorized(req.getPersonWhoFollows(),req.getAuthToken())){
            return manip.manipulate(req);
        }
        return new FollowManipulationResult(false,false);
    }
}
