package kanye2020.gatech.edu.rattrackapp;

import android.support.annotation.Nullable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created by juliachen on 11/13/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class FieldsNotEmptyTest {

    private final RegisterActivity reg = new RegisterActivity();

    @Nullable
    private
    String email;
    @Nullable
    private
    String username;
    @Nullable
    private
    String password;

    /**
     *
     * testing with parameters that work
     */
    @Test
    public void testFieldsNotEmptySuccessOne() {
        email = "passing";
        username = "passing";
        password = "passing";
        assertTrue(reg.fieldsNotEmpty(email, username, password));
    }

    /**
     *
     * testing with second parameter fail
     */
    @Test
    public void testFieldsNotEmptyFailOne() {
        email = "passing";
        password = "passing";
        assertFalse(reg.fieldsNotEmpty(email, null, password));

    }

    /**
     *
     * testing with first two parameters fail
     */
    @Test
    public void testFieldsNotEmptyFailTwo() {
        email = "";
        password = "passing";
        assertFalse(reg.fieldsNotEmpty(email, null, password));

    }

    /**
     *
     * testing with third parameter empty string fail
     */
    @Test
    public void testFieldsNotEmptyFailThree() {
        email = "passing";
        username = "passing";
        password = "";
        assertFalse(reg.fieldsNotEmpty(email, username, password));
    }

    /**
     * testing with third parameter null fail
     */
    @Test
    public void testFieldsNotEmptyFailFour() {
        email = "passing";
        username = "passing";
        assertFalse(reg.fieldsNotEmpty(email, username, null));
    }

    /**
     * testing with all null parameters fail
     */
    @Test
    public void testFieldsNotEmptyNull() {
        assertFalse(reg.fieldsNotEmpty(null, null, null));
    }

}
