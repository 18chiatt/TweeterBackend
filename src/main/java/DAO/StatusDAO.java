package DAO;

import DAO.Fake.ServerFakeFactory;
import model.Response.PostStatusResponse;
import model.request.PostStatusRequest;

public class StatusDAO {

    public PostStatusResponse postStatus(PostStatusRequest req){
        return ServerFakeFactory.getInstance().postStatus(req);
    }
}
