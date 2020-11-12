package Services;

import DAO.FeedDAO;
import model.Response.FeedResponse;
import model.request.FeedRequest;

public class FeedService {
    private FeedDAO feedDao;

    public FeedService(FeedDAO feedDao) {
        this.feedDao = feedDao;
    }

    public FeedResponse getFeed(FeedRequest req){
        return feedDao.getFeed(req);
    }
}
