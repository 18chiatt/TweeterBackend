package model.request;


import model.domain.User;

public class FollowManipulationRequest {
    public FollowManipulationRequest(){

    }


    public User getPersonWhoFollows() {
        return personWhoFollows;
    }

    public User getPersonWhoIsFollowed() {
        return personWhoIsFollowed;
    }

    public boolean isAddFollow() {
        return addFollow;
    }

    private User personWhoFollows;
    private User personWhoIsFollowed;
    private boolean addFollow;

    public FollowManipulationRequest(User personWhoFollows, User personWhoIsFollowed, boolean addFollow, String authToken) {
        this.personWhoFollows = personWhoFollows;
        this.personWhoIsFollowed = personWhoIsFollowed;
        this.addFollow = addFollow;
        this.authToken = authToken;
    }

    public void setPersonWhoFollows(User personWhoFollows) {
        this.personWhoFollows = personWhoFollows;
    }

    public void setPersonWhoIsFollowed(User personWhoIsFollowed) {
        this.personWhoIsFollowed = personWhoIsFollowed;
    }

    public void setAddFollow(boolean addFollow) {
        this.addFollow = addFollow;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    private String authToken;
}
