package Services;

import DAO.UserDAO;
import model.Response.RegisterResponse;
import model.request.RegisterRequest;

public class RegisterService {
    UserDAO toUse;

    public RegisterService(UserDAO toUse) {
        this.toUse = toUse;
    }

    public RegisterResponse register(RegisterRequest req){
        return toUse.register(req);
    }
}
