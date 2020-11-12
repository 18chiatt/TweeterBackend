package Handlers;

import DAO.AuthDAO;
import DAO.FollowingDAO;
import Services.FollowManipulationService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.Response.FollowManipulationResult;
import model.request.FollowManipulationRequest;

public class FollowManipulationHandler implements RequestHandler<FollowManipulationRequest,FollowManipulationResult> {



    @Override
    public FollowManipulationResult handleRequest(FollowManipulationRequest followManipulationRequest, Context context) {
        return new FollowManipulationService(new AuthDAO(),new FollowingDAO()).manipulateFollows(followManipulationRequest);
    }
}
