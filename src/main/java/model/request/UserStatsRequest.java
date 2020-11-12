package model.request;


import model.domain.User;

public class UserStatsRequest {
    public UserStatsRequest(){

    }
    public UserStatsRequest(User toFindOf) {
        this.toFindOf = toFindOf;
    }

    public User getToFindOf() {
        return toFindOf;
    }

    public void setToFindOf(User toFindOf) {
        this.toFindOf = toFindOf;
    }

    private User toFindOf;

}
