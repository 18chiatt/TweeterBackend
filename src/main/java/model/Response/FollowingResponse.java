package model.Response;


import model.domain.User;

import java.util.List;

public class FollowingResponse {
    public FollowingResponse(List<User> usersTheyAreFollowing, boolean hasMore) {
        this.usersTheyAreFollowing = usersTheyAreFollowing;
        this.hasMore = hasMore;
    }

    public List<User> getUsersTheyAreFollowing() {
        return usersTheyAreFollowing;
    }

    public boolean hasMore() {
        return hasMore;
    }

    List<User> usersTheyAreFollowing;
    boolean hasMore;

    public void setUsersTheyAreFollowing(List<User> usersTheyAreFollowing) {
        this.usersTheyAreFollowing = usersTheyAreFollowing;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
