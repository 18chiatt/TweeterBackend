package Services;

import DAO.AuthDAO;
import DAO.UserDAO;
import model.Response.UserStatsResponse;
import model.request.UserStatsRequest;

public class UserStatsService {
    public UserStatsService(UserDAO toUse, AuthDAO toRefresh) {
        this.toUse = toUse;
        this.toRefreshWith = toRefresh;
    }

    UserDAO toUse;
    AuthDAO toRefreshWith;
    public UserStatsResponse getUserStats(UserStatsRequest req){

        toRefreshWith.refresh(req.getAuthToken(),req.getWhoAsked());
        return toUse.getUserStats(req);
    }
}
