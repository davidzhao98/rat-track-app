package kanye2020.gatech.edu.rattrackapp;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * JUnit Test for RegisterActivity confirmPassword method
 *
 * Created by Carissa on 11/13/17.
 */

public class EmailConfirmationTest {
    RegisterActivity activity = new RegisterActivity();

    String email1, email2;

    /**
     * Testing with valid inputs (two emails match)
     *
     */
    @Test public void testEmailConfirmSuccess() {
        email1 = "something@mail.com";
        email2 = "something@mail.com";
        assertTrue(activity.confirmEmail(email1, email2));
    }

    /**
     * Testing with invalid inputs (two emails do not match)
     *
     */
    @Test
    public void testConfirmEmailFailure() {
        email1 = "rat@mail.com";
        email2 = "somethingelse@mail.com";
        assertFalse(activity.confirmEmail(email1, email2));
    }

    /**
     * Testing with blank inputs (null)
     *
     */
    @Test
    public void testConfirmEmailNull() {
        email1 = null;
        email2 = null;
        assertFalse(activity.confirmEmail(email1, email2));
    }
}
