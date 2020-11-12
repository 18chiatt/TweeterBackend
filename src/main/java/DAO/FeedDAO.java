package DAO;

import DAO.Fake.ServerFakeFactory;
import model.Response.FeedResponse;
import model.request.FeedRequest;

public class FeedDAO {
    public FeedResponse getFeed(FeedRequest req){
        FeedResponse resp = ServerFakeFactory.getInstance().getFeed(req);
        System.out.println(resp.getTheStatus().toString());
        return  resp;
    }
}
