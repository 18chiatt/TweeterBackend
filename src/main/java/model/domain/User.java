package model.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class User implements Serializable {
    public User(String firstName, String lastName, String userName, String imageURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.imageURL = imageURL;
    }
    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Expose(serialize = true, deserialize = true)
    private String firstName;
    @Expose(serialize = true, deserialize = true)
    private String lastName;
    @Expose(serialize = true, deserialize = true)
    @SerializedName("username")
    private String userName;
    @Expose(serialize = true, deserialize = true)
    private String imageURL;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getImageURL() {
        return imageURL;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userName.equals(user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
