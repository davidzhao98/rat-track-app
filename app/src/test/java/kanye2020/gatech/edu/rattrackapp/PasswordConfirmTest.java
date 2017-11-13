package kanye2020.gatech.edu.rattrackapp;

import android.content.Context;
import android.test.mock.MockContext;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nick B on 11/13/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class PasswordConfirmTest {

    RegisterActivity activity = new RegisterActivity();

    String string1;
    String string2;

    @Before
    public void setup() {

    }

    @Test
    public void testConfirmPassSuccess() {
        string1 = "pass";
        string2 = "pass";
        assertTrue(activity.confirmPassword(string1, string2));
    }

    @Test
    public void testConfirmPassFail() {
        string1 = "pass";
        string2 = "pass2";
        assertFalse(activity.confirmPassword(string1, string2));
    }

    @Test
    public void testConfirmPassNull() {
        string1 = null;
        string2 = null;
        assertFalse(activity.confirmPassword(string1, string2));
    }



}
