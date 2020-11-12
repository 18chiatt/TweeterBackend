package model.Response;



public class FollowManipulationResult {


    public boolean isNowFollowing() {
        return isNowFollowing;
    }


    public FollowManipulationResult( boolean isNowFollowing,boolean wasSuccess) {
        this.wasSuccess = wasSuccess;
        this.isNowFollowing = isNowFollowing;
    }

    public boolean isWasSuccess() {
        return wasSuccess;
    }

    private boolean wasSuccess;
    private boolean isNowFollowing;

    public void setWasSuccess(boolean wasSuccess) {
        this.wasSuccess = wasSuccess;
    }

    public void setNowFollowing(boolean nowFollowing) {
        isNowFollowing = nowFollowing;
    }
}
