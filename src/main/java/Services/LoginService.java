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
            return toUse.login(req);
        }
        return new LoginResponse(null,null,false);


    }
}
