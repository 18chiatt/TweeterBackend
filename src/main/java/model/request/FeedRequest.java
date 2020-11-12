package model.request;


import model.domain.Status;
import model.domain.User;

public class FeedRequest {
    public FeedRequest(User toGetFeedOf, int maxToGet, Status previousLast) {
        this.toGetFeedOf = toGetFeedOf;
        this.maxToGet = maxToGet;
        this.previousLast = previousLast;
    }
    public FeedRequest(){

    }

    public User getToGetFeedOf() {
        return toGetFeedOf;
    }

    public int getMaxToGet() {
        return maxToGet;
    }

    public Status getPreviousLast() {
        return previousLast;
    }

    public void setToGetFeedOf(User toGetFeedOf) {
        this.toGetFeedOf = toGetFeedOf;
    }

    public void setMaxToGet(int maxToGet) {
        this.maxToGet = maxToGet;
    }

    public void setPreviousLast(Status previousLast) {
        this.previousLast = previousLast;
    }

    private User toGetFeedOf;
    private int maxToGet;
    private Status previousLast;
}
