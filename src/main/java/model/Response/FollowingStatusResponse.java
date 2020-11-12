package model.Response;

public class FollowingStatusResponse {
    public FollowingStatusResponse(boolean follows) {
        this.follows = follows;
    }

    public boolean isFollows() {
        return follows;
    }

    private boolean follows;

    public void setFollows(boolean follows) {
        this.follows = follows;
    }
}
