package Services;

import DAO.UserStatsDAO;
import model.Response.UserStatsResponse;
import model.request.UserStatsRequest;

public class UserStatsService {
    public UserStatsService(UserStatsDAO toUse) {
        this.toUse = toUse;
    }

    UserStatsDAO toUse;
    public UserStatsResponse getUserStats(UserStatsRequest req){
        return toUse.getUserStats(req);
    }
}
