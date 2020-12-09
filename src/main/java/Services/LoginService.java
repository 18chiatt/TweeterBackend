package Services;

import DAO.AuthDAO;
import DAO.Fake.ServerFakeFactory;
import DAO.UserDAO;
import model.Response.LoginResponse;
import model.request.LoginRequest;

public class LoginService {
    public LoginService(UserDAO toUse, AuthDAO toValidateWith) {
        this.toValidateWith = toValidateWith;
        this.toUse = toUse;
    }

    AuthDAO toValidateWith;
    UserDAO toUse;
    public LoginResponse login(LoginRequest req){
        if(toValidateWith.correctPassword(req)){
            LoginResponse resp = toUse.login(req);
            resp.setAuthToken(toValidateWith.getCreateAuthToken(req));
            System.out.println("Should be correct!");
            return resp;
        }
        System.out.println("Password was incorrect");
        return new LoginResponse(null,null,false);

    }
}
