package DAO;

import model.domain.User;

public class AuthDAO {
    public boolean isAuthorized(User u, String authToken){
        return true;
    }
}
