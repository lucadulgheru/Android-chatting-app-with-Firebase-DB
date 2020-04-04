package com.example.chatril.Model;

public class User {

    private String id;
    private String username;
    private String imageURL;
    private String userstatus;

    public User(String id, String username, String imageURL, String userstatus) {
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
        this.userstatus = userstatus;
    }


    public User() {

    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
