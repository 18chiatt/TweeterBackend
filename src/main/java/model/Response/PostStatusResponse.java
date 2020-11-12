package model.Response;



public class PostStatusResponse {
    private boolean wasSuccess;


    public PostStatusResponse(boolean wasSuccess) {
        this.wasSuccess = wasSuccess;
    }

    public boolean isWasSuccess() {
        return wasSuccess;
    }
}
