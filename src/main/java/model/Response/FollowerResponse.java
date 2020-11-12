package model.Response;


import model.domain.User;

import java.util.List;

public class FollowerResponse {
    public FollowerResponse(List<User> followers, boolean hasMore) {
        this.followers = followers;
        this.hasMore = hasMore;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public boolean hasMore() {
        return hasMore;
    }

    List<User> followers;
    boolean hasMore;
}
