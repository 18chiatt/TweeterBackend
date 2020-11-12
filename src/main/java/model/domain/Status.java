package model.domain;


import java.util.Objects;

public class Status implements Comparable<Status>{
    public Status(String message, Long timeOfPost, User saidBy) {
        this.message = message;
        this.timeOfPost = timeOfPost;
        this.saidBy = saidBy;
    }

    public Status(){

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimeOfPost(Long timeOfPost) {
        this.timeOfPost = timeOfPost;
    }

    public void setSaidBy(User saidBy) {
        this.saidBy = saidBy;
    }

    public String getMessage() {
        return message;
    }

    public Long getTimeOfPost() {
        return timeOfPost;
    }


    @Override
    public String toString() {
        return "Status{" +
                "message='" + message + '\'' +
                ", timeOfPost=" + timeOfPost +
                ", saidBy=" + saidBy +
                '}';
    }

    private String message;
    private Long timeOfPost;

    public User getSaidBy() {
        return saidBy;
    }

    private User saidBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return message.equals(status.message) &&
                timeOfPost.equals(status.timeOfPost) &&
                saidBy.equals(status.saidBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, timeOfPost, saidBy);
    }

    @Override
    public int compareTo(Status o) {
        return o.timeOfPost.compareTo(timeOfPost);
    }
}
