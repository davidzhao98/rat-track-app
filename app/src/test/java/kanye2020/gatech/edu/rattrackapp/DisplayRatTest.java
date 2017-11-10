package kanye2020.gatech.edu.rattrackapp;

import com.google.android.gms.maps.GoogleMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;



/**
 * JUnit test for displayRat()
 * Created by David Zhao on 11/07/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DisplayRatTest {

    GoogleMap mMap;

    @Mock
    MapsActivity testActivity = mock(MapsActivity.class);

    @Mock
    ArrayList<RatSighting> mockedList = mock(ArrayList.class);

    @Before
    public void setup() {
//        RatSighting rat = new RatSighting("BROOKLYN", "NEW YORK", "FAKE", "FAKE", "FAKE", "IDK", "40.00", "40.00", "123");
//        mockedList.add(rat);
    }

    @Test
    public void testDisplayRatFAILURE() {
        RatSighting rat = new RatSighting("BROOKLYN", "NEW YORK", "FAKE", "FAKE", "FAKE", "IDK", "asdf", "asdf", "123");
        assertTrue(!testActivity.displayRat(mMap, rat));
    }

    @Test
    public void testDisplayRatSUCCESS() {
        RatSighting rat = new RatSighting("BROOKLYN", "NEW YORK", "FAKE", "FAKE", "FAKE", "IDK", "40.00", "40.00", "123");
        assertTrue(testActivity.displayRat(mMap, rat));
    }

}
