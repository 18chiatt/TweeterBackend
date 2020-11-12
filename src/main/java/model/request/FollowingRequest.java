package model.request;


import model.domain.User;

public class FollowingRequest {
    public FollowingRequest(User personWhoFollows, int limit, User previousLast) {
        this.lastOneGotten = previousLast;
        this.personWhoFollows = personWhoFollows;
        this.limit = limit;
    }

    public FollowingRequest(){

    }

    public User getPersonWhoFollows() {
        return personWhoFollows;
    }

    public int getLimit() {
        return limit;
    }

    private User personWhoFollows;

    public User getLastOneGotten() {
        return lastOneGotten;
    }

    private User lastOneGotten;

    public void setPersonWhoFollows(User personWhoFollows) {
        this.personWhoFollows = personWhoFollows;
    }

    public void setLastOneGotten(User lastOneGotten) {
        this.lastOneGotten = lastOneGotten;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    private int limit;
}
