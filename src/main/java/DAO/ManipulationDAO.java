package DAO;

import model.Response.FollowManipulationResult;
import model.request.FollowManipulationRequest;

public class ManipulationDAO {

    public FollowManipulationResult manipulate(FollowManipulationRequest req){
        new BeingFollowedDAO().manipulateFollows(req);
        return  new FollowingDAO().manipulateFollows(req);
    }
}
