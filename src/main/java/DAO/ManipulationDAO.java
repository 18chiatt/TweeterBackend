package DAO;

import model.Response.FollowManipulationResult;
import model.request.FollowManipulationRequest;
import model.request.FollowingStatusRequest;

public class ManipulationDAO {

    public FollowManipulationResult manipulate(FollowManipulationRequest req){

        if(req.getPersonWhoFollows().equals(req.getPersonWhoIsFollowed())){
            return new FollowManipulationResult(false,false);
        }

        if(req.getPersonWhoFollows().getUserName().equals(req.getPersonWhoIsFollowed().getUserName())){
            return new FollowManipulationResult(false,false);
        }


        boolean currentlyFollows = new FollowingDAO().getFollowingStatus(new FollowingStatusRequest(req.getPersonWhoFollows(),req.getPersonWhoIsFollowed())).isFollows();
        if(req.isAddFollow() && currentlyFollows){
            System.out.println("Attempting to add existing follow");
            return new FollowManipulationResult(true,true);
        }

        if(!req.isAddFollow() && !currentlyFollows){
            System.out.println("Attempting to remove non-existant follow");
            return new FollowManipulationResult(false,true);
        }

        new BeingFollowedDAO().manipulateFollows(req);
        return  new FollowingDAO().manipulateFollows(req);
    }


}
