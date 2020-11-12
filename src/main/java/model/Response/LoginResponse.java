package model.Response;


import model.domain.User;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    public static final String LOGIN_RESPONSE_KEY = "Login_Response";
    public LoginResponse(User loggedInAs, String authToken, boolean success) {
        this.success = success;
        this.loggedInAs = loggedInAs;
        this.authToken = authToken;
    }

    public User getLoggedInAs() {
        return loggedInAs;
    }

    public String getAuthToken() {
        return authToken;
    }

    private User loggedInAs;
    private boolean success;
    private String authToken;

    public boolean isSuccess() {
        return success;
    }

    public void setLoggedInAs(User loggedInAs) {
        this.loggedInAs = loggedInAs;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
