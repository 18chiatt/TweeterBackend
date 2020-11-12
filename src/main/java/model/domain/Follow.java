package model.domain;

public class Follow {
    public Follow(User follower, User personBeingFollowed) {
        this.follower = follower;
        this.personBeingFollowed = personBeingFollowed;
    }
    public Follow(){

    }

    public User getFollower() {
        return follower;
    }

    public User getPersonBeingFollowed() {
        return personBeingFollowed;
    }

    private User follower;
    private User personBeingFollowed;

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public void setPersonBeingFollowed(User personBeingFollowed) {
        this.personBeingFollowed = personBeingFollowed;
    }
}
