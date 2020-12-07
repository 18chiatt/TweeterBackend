package DAO;

import model.Response.LoginResponse;
import model.domain.User;
import model.request.LoginRequest;

public class AuthDAO {
    public boolean isAuthorized(User u, String authToken){


        return true;
    }

    public void refresh(String authToken){

    }

    public boolean correctPassword(LoginRequest req){
        return true;
    }
}
