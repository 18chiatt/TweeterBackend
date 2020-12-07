package Handlers;

import DAO.UserDAO;
import Services.UserService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.Response.UserResponse;
import model.request.UserRequest;

public class UserHandler implements RequestHandler<UserRequest, UserResponse> {



    @Override
    public UserResponse handleRequest(UserRequest userRequest, Context context) {
        return new UserService(new UserDAO()).getUser(userRequest);
    }
}
