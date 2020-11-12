package Services;

import DAO.Fake.ServerFakeFactory;
import DAO.UserDAO;
import model.Response.LoginResponse;
import model.request.LoginRequest;

public class LoginService {
    public LoginService(UserDAO toUse) {
        this.toUse = toUse;
    }

    UserDAO toUse;
    public LoginResponse login(LoginRequest req){
        return toUse.login(req);
    }
}
