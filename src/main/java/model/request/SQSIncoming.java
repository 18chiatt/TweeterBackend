package model.request;

import model.domain.User;

public class SQSIncoming {
    private String username;
    private Long timestamp;
    private String message;
    private String firstName;
    private String lastName;
    private String imageURL;
    private User user;

    public String getUsername() {
        return username;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public User getUser(){
        if(user == null){
            user = new User(firstName,lastName,username,imageURL);
        }
        return user;
    }
}
