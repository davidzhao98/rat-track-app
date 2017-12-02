package kanye2020.gatech.edu.rattrackapp;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by David Zhao on 10/1/2017.
 */

class Account {
    //Firebase requires public modifier
    @SuppressWarnings({"WeakerAccess", "CanBeFinal", "PublicField"})
    public String username;
    @SuppressWarnings({"WeakerAccess", "CanBeFinal", "PublicField"})
    public String password;
    @SuppressWarnings({"WeakerAccess", "CanBeFinal", "PublicField"})
    public String email;
    @SuppressWarnings({"WeakerAccess", "CanBeFinal", "PublicField"})
    public boolean admin;
    @SuppressWarnings({"WeakerAccess", "CanBeFinal", "PublicField"})
    public boolean lockedout;
    public int attempts;

    final static List<String> accountTypes = Arrays.asList("USER", "ADMIN");


    /**
     * default constructor for Account
     */
    Account() {
        this(null, null, null, false, false);
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
        this.attempts = 0;
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
     * getter for login attempts
     * @return login attempts
     */
    int getAttempts() {
        return attempts;
    }

    /**
     * setter for login attempts
     * @param attempts current number of attempts
     */
    public void setAttempts(int attempts) {
        this.attempts = attempts + 1;
    }

// --Commented out by Inspection START (11/16/17, 8:50 PM):
//    /**
//     * getter for email
//     * @return return email
//     */
//    String getEmail() {
//        return email;
//    }
// --Commented out by Inspection STOP (11/16/17, 8:50 PM)

// --Commented out by Inspection START (11/16/17, 8:50 PM):
//    /**
//     * getter for admin status
//     * @return admin status
//     */
//    Boolean isAdmin() {
//        return admin;
//    }
// --Commented out by Inspection STOP (11/16/17, 8:50 PM)

// --Commented out by Inspection START (11/16/17, 8:50 PM):
//    /**
//     * getter for locked out status
//     * @return lockedout status
//     */
//    Boolean isLockedout() { return lockedout;}
// --Commented out by Inspection STOP (11/16/17, 8:50 PM)


}
