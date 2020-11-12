package model.Response;



import model.domain.Status;

import java.util.List;

public class FeedResponse {
    public FeedResponse(List<Status> theStatus, boolean hasMore) {
        this.theStatus = theStatus;
        this.hasMore = hasMore;
    }

    public List<Status> getTheStatus() {
        return theStatus;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    private List<Status> theStatus;
    private boolean hasMore;

   public FeedResponse(){

   }

    public void setTheStatus(List<Status> theStatus) {
        this.theStatus = theStatus;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
