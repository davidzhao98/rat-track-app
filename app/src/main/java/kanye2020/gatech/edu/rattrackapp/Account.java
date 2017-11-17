package kanye2020.gatech.edu.rattrackapp;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by David Zhao on 10/1/2017.
 */

class Account {
    //Firebase requires public modifier
    @SuppressWarnings("WeakerAccess")
    public String username;
    @SuppressWarnings("WeakerAccess")
    public String password;
    @SuppressWarnings("WeakerAccess")
    public String email;
    @SuppressWarnings("WeakerAccess")
    public boolean admin;
    @SuppressWarnings("WeakerAccess")
    public boolean lockedout;

    static List<String> accountTypes = Arrays.asList("USER", "ADMIN");


    /**
     * default constructor for Account
     */
    Account() {
        this.username = null;
        this.password = null;
        this.email = null;
        this.admin = false;
        this.lockedout = false;
    }

    /**
     * constructor for Account
     * @param username user's username
     * @param password user's password
     * @param email user's email address
     * @param admin user's admin status
     */
    Account (String username, String password, String email, boolean admin, boolean lockedout) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
        this.lockedout = lockedout;
    }

    /**
     * getter for username
     * @return username
     */
    String getUsername() {
        return username;
    }

    /**
     * getter for password
     * @return password
     */
    String getPassword() {
        return password;
    }

    /**
     * getter for email
     * @return return email
     */
    String getEmail() {
        return email;
    }

    /**
     * getter for admin status
     * @return admin status
     */
    Boolean isAdmin() {
        return admin;
    }

    /**
     * getter for locked out status
     * @return lockedout status
     */
    Boolean isLockedout() { return lockedout;}


}
