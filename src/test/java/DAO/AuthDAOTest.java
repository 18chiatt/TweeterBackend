package DAO;

import model.Response.RegisterResponse;
import model.domain.User;
import model.request.LoginRequest;
import model.request.RegisterRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthDAOTest {
    @Test
    public void register() {
        RegisterRequest req = new RegisterRequest("Chase","password",null,null,null);
        new AuthDAO().register(req);
    }

    @Test
    public void isAuthorized() {
        User myUser = new User(null,null,"Chase",null);
        String oldAuthToken = "p;w0$TY#.9JR83nM-#tq";
        AuthDAO toUse = new AuthDAO();
        assert(!toUse.isAuthorized(myUser, oldAuthToken));
        String validAuthToken = toUse.getCreateAuthToken(new LoginRequest("Chase","password"));
        assert(toUse.isAuthorized(myUser,validAuthToken));
    }


    @Test
    public void correctPassword() {
        AuthDAO toUse = new AuthDAO();

        LoginRequest req = new LoginRequest("18chiatt","asdf");
        assert(!toUse.correctPassword(req));
        req = new LoginRequest("18chiatt","password");
        assert(toUse.correctPassword(req));
    }




    @Test
    public void getCreateAuthToken(){
        LoginRequest req = new LoginRequest("Chase","password");
        AuthDAO toUse = new AuthDAO();
        String authToken = toUse.getCreateAuthToken(req);
        System.out.println(authToken);
    }

    @Test
    public void refresh(){
        User myUser = new User(null,null,"Chase",null);
        LoginRequest req = new LoginRequest("Chase","password");
        AuthDAO toUse = new AuthDAO();
        String authToken = toUse.getCreateAuthToken(req);
        toUse.refresh(authToken,myUser);

    }
}