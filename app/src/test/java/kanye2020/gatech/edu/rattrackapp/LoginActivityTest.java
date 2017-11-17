package kanye2020.gatech.edu.rattrackapp;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 *
 * Created by pulakazad on 11/6/17.
 */
public class LoginActivityTest {

    private final ArrayList<Account> entries = new ArrayList<>();

    private final LoginActivity activity = new LoginActivity();

    private String validUsername;
    private String validPass;

    private String invalidPass;
    private String invalidUsername;

    /**
     * sets up to add accounts to entries
     */
    @Before
    public void setup() {
        entries.add(new Account("bob", "abc", "bob@gmail.com", false, false));
        entries.add(new Account("clout", "pulak", "pulak@gmail.com", false, false));
        entries.add(new Account("notLit", "oy", "farhan@gmail.com", false, false));
        entries.add(new Account());

        validUsername = "bob";
        validPass = "abc";

        invalidUsername = "notBob";
        invalidPass = "pass2";

    }


    /**
     * supposed to test login with a valid password and valid username
     */
    @Test
    public void testLoginSuccessWithValidPassAndValidUsername() {
        assertTrue(activity.loginVerification(validUsername, validPass, entries));
    }

    /**
     * supposed to test login with a invalid password and valid username
     */
    @Test
    public void testLoginWithInvalidPassFailAndValidUsername() {
        assertFalse(activity.loginVerification(validUsername, invalidPass, entries));
    }

    /**
     * supposed to test login with a valid password and invalid username
     */
    @Test
    public void testLoginWithInvalidUsernameAndValidPassword() {
        assertFalse(activity.loginVerification(invalidUsername, validPass, entries));
    }

    /**
     * supposed to test login with a invalid password and invalid username
     */
    @Test
    public void testLoginWithInvalidUsernameAndInvalidPassword() {
        assertFalse(activity.loginVerification(invalidUsername, invalidPass, entries));
    }

    /**
     * supposed to test login with null username
     */
    @Test
    public void testLoginWithNullUsername() {
        assertFalse(activity.loginVerification(null, validPass, entries));
    }

    /**
     * supposed to test login with null password
     */
    @Test
    public void testLoginWithNullPassword() {
        assertFalse(activity.loginVerification(validUsername, null, entries));
    }

    /**
     * supposed to test login with null username and null password
     */
    @Test
    public void testLoginWithNullUsernameAndNullPassword() {
        assertFalse(activity.loginVerification(null, null, entries));
    }

    /**
     * supposed to test login with null everything
     */
    @Test
    public void testLoginWithNullEverything() {
        assertFalse(activity.loginVerification(null, null, null));
    }

}