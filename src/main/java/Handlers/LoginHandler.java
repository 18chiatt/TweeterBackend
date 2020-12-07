package Handlers;

import DAO.AuthDAO;
import DAO.UserDAO;
import Services.LoginService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.Response.LoginResponse;
import model.request.LoginRequest;

public class LoginHandler implements RequestHandler<LoginRequest,LoginResponse> {




    @Override
    public LoginResponse handleRequest(LoginRequest loginRequest, Context context) {
        return new LoginService(new UserDAO(),new AuthDAO()).login(loginRequest);
    }
}
