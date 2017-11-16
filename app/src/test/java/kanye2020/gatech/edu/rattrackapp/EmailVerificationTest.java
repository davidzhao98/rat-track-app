package kanye2020.gatech.edu.rattrackapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * JUnit test for emailValidCheck()
 *
 * Created by David Zhao on 11/07/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmailVerificationTest {
    List<String> correct = new ArrayList<>();
    List<String> incorrect = new ArrayList<>();



    @Spy
    RegisterActivity testActivity = spy(RegisterActivity.class);



    @Before
    public void setup() {
        correct.add("bhadbhaby@gmail.com");
        correct.add("david_zhao98@msn.com");
        correct.add("helpo@hotmail.com");
        correct.add("2340.is.my.favorite.class@yahoo.com");
        correct.add("a@b.com");
        incorrect.add("a;sdflkjlwea;flk");
        incorrect.add("david@gmail@gmail.com");
        incorrect.add("@@@.com");
        incorrect.add("@.com");
        incorrect.add("ta2222");
    }

    @Test
    public void testCorrectEmails() {
        for (String email : correct) {
            assertEquals(true, testActivity.emailValidCheck(email));
        }
    }

    @Test
    public void testIncorrectEmails() {
        for (String email : incorrect) {
            assertEquals(false, testActivity.emailValidCheck(email));
        }
    }

}
