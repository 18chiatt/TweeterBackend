package model.request;


import model.domain.Status;

public class PostStatusRequest {
    public PostStatusRequest(){

    }
    public Status getTheStatus() {
        return theStatus;
    }


    public PostStatusRequest(Status theStatus, String authToken) {
        this.theStatus = theStatus;
        this.authToken = authToken;
    }

    private Status theStatus;

    public void setTheStatus(Status theStatus) {
        this.theStatus = theStatus;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    private String authToken;
}
