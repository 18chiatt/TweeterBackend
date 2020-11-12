package model.request;


import model.domain.User;

public class FollowingStatusRequest {
    public FollowingStatusRequest(){

    }
    public void setPersonWhoFollowsMaybe(User personWhoFollowsMaybe) {
        this.personWhoFollowsMaybe = personWhoFollowsMaybe;
    }

    public void setPersonWhoIsFollowedMaybe(User personWhoIsFollowedMaybe) {
        this.personWhoIsFollowedMaybe = personWhoIsFollowedMaybe;
    }

    public FollowingStatusRequest(User personWhoFollowsMaybe, User personWhoIsFollowedMaybe) {
        this.personWhoFollowsMaybe = personWhoFollowsMaybe;
        this.personWhoIsFollowedMaybe = personWhoIsFollowedMaybe;
    }

    public User getPersonWhoFollowsMaybe() {
        return personWhoFollowsMaybe;
    }

    public User getPersonWhoIsFollowedMaybe() {
        return personWhoIsFollowedMaybe;
    }

    private User personWhoFollowsMaybe;
    private User personWhoIsFollowedMaybe;

}
