package kanye2020.gatech.edu.rattrackapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by pulakazad on 11/6/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginActivityTest {

    @Mock
    private ArrayList<Account> accounts;

    @Before
    public void setup() {
        accounts.add(new Account("NotBob", "abc", "bob@gmail.com", false));
        accounts.add(new Account("NotBob", "cba", "bob@gmail.com", false));
        accounts.add(new Account("NotBob", "abc", "bob@gmail.com", true));

    }

    @Test
    public void loginValidator_CorrectPassword_ReturnsTrue() {
        for (Account each : accounts) {
            assertTrue(LoginActivity.loginVerification());
        }
    }
}