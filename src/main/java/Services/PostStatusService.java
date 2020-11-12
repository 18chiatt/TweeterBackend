package Services;

import DAO.AuthDAO;
import DAO.Fake.ServerFakeFactory;
import DAO.StatusDAO;
import model.Response.PostStatusResponse;
import model.request.PostStatusRequest;

public class PostStatusService {
    AuthDAO authToUse;
    StatusDAO statusToUse;

    public PostStatusService(AuthDAO authToUse, StatusDAO statusToUse) {
        this.authToUse = authToUse;
        this.statusToUse = statusToUse;
    }

    public PostStatusResponse postStatus(PostStatusRequest req){
        if(authToUse.isAuthorized(req.getTheStatus().getSaidBy(),req.getAuthToken())){
            return statusToUse.postStatus(req);
        }
        return new PostStatusResponse(false);
    }

}
