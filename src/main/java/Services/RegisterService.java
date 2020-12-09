package Services;

import DAO.AuthDAO;
import DAO.ImageDAO;
import DAO.UserDAO;
import model.Response.RegisterResponse;
import model.request.RegisterRequest;

import java.awt.*;
import java.util.Base64;

public class RegisterService {
    UserDAO toUse;
    ImageDAO imageDAO;
    AuthDAO authDao;

    public RegisterService(UserDAO toUse, ImageDAO imageDao,AuthDAO authDao) {
        this.toUse = toUse;
        this.imageDAO = imageDao;
        this.authDao = authDao;
    }

    public RegisterResponse register(RegisterRequest req){

        if(!toUse.isFree(req.getUsername())){
            return new RegisterResponse(false);
        }

        byte[] bytes = Base64.getDecoder().decode(req.getImage());
        String URL = imageDAO.uploadImage(bytes, req.getUsername());


        RegisterResponse resp = toUse.register(req,URL);
        authDao.register(req);


        return resp;
    }
}
