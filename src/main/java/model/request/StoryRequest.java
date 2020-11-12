package model.request;


import model.domain.Status;
import model.domain.User;

public class StoryRequest {
    public StoryRequest(){

    }
    public int getMaxToGet() {
        return maxToGet;
    }

    public User getToGetOf() {
        return toGetOf;
    }

    public Status getPreviousLast() {
        return previousLast;
    }

    int maxToGet;
    User toGetOf;
    Status previousLast;

    public StoryRequest(int maxToGet, User toGetOf, Status previousLast) {
        this.maxToGet = maxToGet;
        this.toGetOf = toGetOf;
        this.previousLast = previousLast;
    }

    public void setMaxToGet(int maxToGet) {
        this.maxToGet = maxToGet;
    }

    public void setToGetOf(User toGetOf) {
        this.toGetOf = toGetOf;
    }

    public void setPreviousLast(Status previousLast) {
        this.previousLast = previousLast;
    }
}
