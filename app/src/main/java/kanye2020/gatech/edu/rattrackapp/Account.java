package kanye2020.gatech.edu.rattrackapp;

import java.util.Arrays;
import java.util.List;

/**
 * Created by David Zhao on 10/1/2017.
 */

public class Account {
    String username;
    String password;
    String email;
    boolean admin;

    public static List<String> accountTypes = Arrays.asList("USER", "ADMIN");


    /**
     * default constructor for Account
     */
    public Account() {
        this.username = null;
        this.password = null;
        this.email = null;
        this.admin = false;
    }

    /**
     * constructor for Account
     * @param username user's username
     * @param password user's password
     * @param email user's email address
     * @param admin user's admin status
     */
    public Account (String username, String password, String email, boolean admin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }

    /**
     * getter for username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter for password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * getter for email
     * @return return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter for admin status
     * @return admin status
     */
    public Boolean isAdmin() {
        return admin;
    }
}
