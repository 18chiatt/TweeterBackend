package Services;

import DAO.UserDAO;
import model.Response.UserResponse;
import model.request.UserRequest;

public class UserService {
    UserDAO toUse;

    public UserService(UserDAO toUse) {
        this.toUse = toUse;
    }

    public UserResponse getUser(UserRequest req){
        return toUse.getUser(req);
    }
}
