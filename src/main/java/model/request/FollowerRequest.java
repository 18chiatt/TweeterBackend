package model.request;


import model.domain.User;

public class FollowerRequest {
    public void setWhoTheyFollow(User whoTheyFollow) {
        this.whoTheyFollow = whoTheyFollow;
    }
    public FollowerRequest(){

    }

    public void setMaxToGet(int maxToGet) {
        this.maxToGet = maxToGet;
    }

    public void setPreviousLast(User previousLast) {
        this.previousLast = previousLast;
    }

    public FollowerRequest(User whoTheyFollow, int maxToGet, User lastOneGotten) {
        this.whoTheyFollow = whoTheyFollow;
        this.maxToGet = maxToGet;
        this.previousLast = lastOneGotten;
    }

    public User getWhoTheyFollow() {
        return whoTheyFollow;
    }

    public int getMaxToGet() {
        return maxToGet;
    }

    private User whoTheyFollow;
    private int maxToGet;

    public User getPreviousLast() {
        return previousLast;
    }

    private User previousLast;
}
