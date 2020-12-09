package Handlers;

import DAO.AuthDAO;
import DAO.BeingFollowedDAO;
import DAO.FollowingDAO;
import DAO.ManipulationDAO;
import Services.FollowManipulationService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.Response.FollowManipulationResult;
import model.request.FollowManipulationRequest;

public class FollowManipulationHandler implements RequestHandler<FollowManipulationRequest,FollowManipulationResult> {



    @Override
    public FollowManipulationResult handleRequest(FollowManipulationRequest followManipulationRequest, Context context) {
        return new FollowManipulationService(new AuthDAO(),new ManipulationDAO()).manipulateFollows(followManipulationRequest);
    }
}
