package edu.utsa.cs3443.rowdystudenthealth.model;

public class userCredential {
    private String username;
    private String password;

    public userCredential(String userName, String password){
        this.username = userName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
