package Handlers;

import DAO.AuthDAO;
import DAO.ImageDAO;
import DAO.UserDAO;
import Services.RegisterService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.Response.RegisterResponse;
import model.request.RegisterRequest;

public class RegisterHandler implements RequestHandler<RegisterRequest, RegisterResponse> {


    @Override
    public RegisterResponse handleRequest(RegisterRequest registerRequest, Context context) {
        return new RegisterService(new UserDAO(),new ImageDAO()).register(registerRequest);

    }
}
