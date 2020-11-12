package DAO;

import DAO.Fake.ServerFakeFactory;
import model.Response.UserStatsResponse;
import model.request.UserStatsRequest;

public class UserStatsDAO {
    public UserStatsResponse getUserStats(UserStatsRequest req){
        return ServerFakeFactory.getInstance().getUserStats(req);
    }
}
