package Services;

import DAO.AuthDAO;
import DAO.StoryDAO;
import model.Response.PostStatusResponse;
import model.request.PostStatusRequest;

public class PostStatusService {
    AuthDAO authToUse;
    StoryDAO statusToUse;

    public PostStatusService(AuthDAO authToUse, StoryDAO statusToUse) {
        this.authToUse = authToUse;
        this.statusToUse = statusToUse;
    }

    public PostStatusResponse postStatus(PostStatusRequest req){
        if(authToUse.isAuthorized(req.getTheStatus().getSaidBy(),req.getAuthToken())){
            System.out.println("Authorized!");
            return statusToUse.postStatus(req);
        }
        System.out.println("Is not authorized!");
        return new PostStatusResponse(false);
    }

}
