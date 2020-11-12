package DAO;

import DAO.Fake.ServerFake;
import DAO.Fake.ServerFakeFactory;
import model.Response.LoginResponse;
import model.Response.RegisterResponse;
import model.Response.UserResponse;
import model.request.LoginRequest;
import model.request.RegisterRequest;
import model.request.UserRequest;

public class UserDAO {
    //private String alias;

    public UserResponse getUser(UserRequest req){
        System.out.println("Attempting to instantiate serverFake");
        return ServerFakeFactory.getInstance().getUser(req);
    }

    public LoginResponse login(LoginRequest req){
        return ServerFakeFactory.getInstance().login(req);
    }

    public RegisterResponse register (RegisterRequest req){
        return ServerFakeFactory.getInstance().registerUser(req);
    }

}
