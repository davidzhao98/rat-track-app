package kanye2020.gatech.edu.rattrackapp;

/**
 * Created by David Zhao on 10/1/2017.
 */

public class Account {
    String username;
    String password;
    String email;
    boolean admin;


    public Account() {
        this.username = null;
        this.password = null;
        this.email = null;
        this.admin = false;
    }

    public Account (String username, String password, String email, boolean admin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Boolean isAdmin() {
        return admin;
    }
}
