package Handlers;

import DAO.AuthDAO;
import DAO.UserDAO;
import Services.UserStatsService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.Response.UserStatsResponse;
import model.request.UserStatsRequest;

public class UserStatsHandler implements RequestHandler<UserStatsRequest, UserStatsResponse> {


    @Override
    public UserStatsResponse handleRequest(UserStatsRequest userStatsRequest, Context context) {
        return new UserStatsService(new UserDAO(),new AuthDAO()).getUserStats(userStatsRequest);
    }
}
