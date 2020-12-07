package Services;

import DAO.ImageDAO;
import DAO.UserDAO;
import model.Response.RegisterResponse;
import model.request.RegisterRequest;

import java.awt.*;
import java.util.Base64;

public class RegisterService {
    UserDAO toUse;
    ImageDAO imageDAO;

    public RegisterService(UserDAO toUse, ImageDAO imageDao) {
        this.toUse = toUse;
        this.imageDAO = imageDao;
    }

    public RegisterResponse register(RegisterRequest req){

        if(!toUse.isFree(req.getUsername())){
            return new RegisterResponse(false);
        }

        byte[] bytes = Base64.getDecoder().decode(req.getImage());
        String URL = imageDAO.uploadImage(bytes, req.getUsername());


        RegisterResponse resp = toUse.register(req,URL);


        return resp;
    }
}
