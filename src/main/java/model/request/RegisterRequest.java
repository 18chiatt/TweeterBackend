package model.request;



public class RegisterRequest {
    public RegisterRequest(){

    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public byte[] getImage() {
        return image;
    }

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private byte[] image;

    public RegisterRequest(String username, String password, String firstName, String lastName, byte[] image) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
