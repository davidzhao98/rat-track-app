package kanye2020.gatech.edu.rattrackapp;

import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by juliachen on 11/13/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class FieldsNotEmptyTest {

    RegisterActivity reg = new RegisterActivity();

    String email;
    String username;
    String password;

    @Before
    public void setup() {

    }

    @Test
    public void testFieldsNotEmptySuccessOne() {
        email = "passing";
        username = "passing";
        password = "passing";
        assertTrue(reg.fieldsNotEmpty(email, username, password));
    }

    @Test
    public void testFieldsNotEmptyFailOne() {
        email = "passing";
        username = null;
        password = "passing";
        assertFalse(reg.fieldsNotEmpty(email, username, password));

    }

    @Test
    public void testFieldsNotEmptyFailTwo() {
        email = "";
        username = null;
        password = "passing";
        assertFalse(reg.fieldsNotEmpty(email, username, password));

    }

    @Test
    public void testFieldsNotEmptyFailThree() {
        email = "passing";
        username = "passing";
        password = "";
        assertFalse(reg.fieldsNotEmpty(email, username, password));
    }

    @Test
    public void testFieldsNotEmptyFailFour() {
        email = "passing";
        username = "passing";
        password = null;
        assertFalse(reg.fieldsNotEmpty(email, username, password));
    }

    @Test
    public void testFieldsNotEmptyNull() {
        email = null;
        username = null;
        password = null;
        assertFalse(reg.fieldsNotEmpty(email, username, password));
    }

}
