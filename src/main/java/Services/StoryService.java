package Services;

import DAO.StoryDAO;
import model.Response.StoryResponse;
import model.request.StoryRequest;

public class StoryService {
    StoryDAO toUse;

    public StoryService(StoryDAO toUse) {
        this.toUse = toUse;
    }

    public StoryResponse getStory(StoryRequest req){
        return toUse.getStory(req);
    }
}
