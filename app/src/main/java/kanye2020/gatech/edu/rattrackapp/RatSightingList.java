package kanye2020.gatech.edu.rattrackapp;

import android.icu.text.SimpleDateFormat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Singleton class to be used by the different search activities and the list view.
 * Contains all the rats stored in the database.
 *
 * Created by David Zhao on 10/6/2017.
 */

public class RatSightingList {
    private static List<RatSighting> rats;
    private ArrayList<RatSighting> sample;
    private static RatSightingList instance;
    private static final int threshold = 300;

    public static RatSightingList getInstance() {
        if (instance == null) {
            instance = new RatSightingList();
        }
        return instance;
    }

    /**
     * on start-up, gets all the rat data and store it into the phone
     */
    private RatSightingList() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ratdata");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> itemsIterable = dataSnapshot.getChildren();
                Iterator<DataSnapshot> items = itemsIterable.iterator();
                if (rats == null) {
                    rats = new ArrayList<>();
                    while (items.hasNext()) {
                        DataSnapshot item = items.next();
                        DataSnapshot dataChild = item.child("Borough");
                        String borough = dataChild.getValue(String.class);
                        dataChild = item.child("City");
                        String city = dataChild.getValue(String.class);
                        dataChild = item.child("Location Type");
                        String locationType = dataChild.getValue(String.class);
                        dataChild = item.child("Created Date");
                        String createdDate = dataChild.getValue(String.class);
                        dataChild = item.child("Incident Address");
                        String incidentAddress = dataChild.getValue(String.class);
                        dataChild = item.child("Incident Zip");
                        String incidentZip = dataChild.getValue(String.class);
                        dataChild = item.child("Latitude");
                        String latitude = dataChild.getValue(String.class);
                        dataChild = item.child("Longitude");
                        String longitude = dataChild.getValue(String.class);
                        dataChild = item.child("Unique Key");
                        String uniqueKey = dataChild.getValue(String.class);
                        rats.add(new RatSighting(borough, city, incidentAddress,
                                incidentZip, locationType, createdDate, latitude, longitude,
                                uniqueKey));
//                        rats.add(entry);
                    }
                    sample = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        sample.add(rats.get(i));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("Unable to read in rat data");
            }
        });
    }

    /**
     * @return the arrayList of rats
     */
    static List<RatSighting> getRats() {
        return RatSightingList.getInstance().rats;
    }

    /**
     *
     * @return returns the size of the arrayList
     */
    private static int getRatsSize() {
        return rats.size();
        }

    /**
     *
     * @return the sample arrayList
     */
    static ArrayList<RatSighting> getSample() { return RatSightingList.getInstance().sample;}

    /**
     * method for searching rats by date given a starting date and ending date
     *
     * @param start the starting date for the query
     * @param end the ending date for the query
     * @return an ArrayList of RatSightings that were reported between Date @start and Date @end
     */
    static List<RatSighting> sortByDate(Date start, Date end) throws IllegalArgumentException {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Start date cannot exceed end date.");
        }
        List<RatSighting> searchResults = new ArrayList<>();
        int size = getRatsSize();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            for (int i = 0; i < threshold; i++) {
                int j = (int) (Math.random() * size);
                RatSighting rat = rats.get(j);
                String ratDateTime = rat.getDateTime();
                String ratDateText = ratDateTime.substring(0, "MM/dd/yyyy".length() + 1);
                Date ratDate = dateFormat.parse(ratDateText);
                if ((ratDate.compareTo(start) >= 0) && (ratDate.compareTo(end) <= 0)) {
                    searchResults.add(rat);
                }
            }
        } catch(Exception e) {
//            System.out.println(e);
        }
        return searchResults;
    }

    static List<RatSighting> sortByBorough(String boroughName) {
        List<RatSighting> searchResults = new ArrayList<>();
        int size = getRatsSize();
        for (int i = 0; i < threshold; i++) {
            int j = (int) (Math.random() * size);
            RatSighting rat = rats.get(j);
            CharSequence borough = rat.getBorough();
            if (borough.equals(boroughName)) {
                searchResults.add(rat);
            }
        }
        return searchResults;
    }

   static List<RatSighting> sortByLocationType(String locationType) {
        List<RatSighting> searchResults = new ArrayList<>();
        int size = getRatsSize();
        for (int i = 0; i < threshold; i++) {
            int j = (int) (Math.random() * size);
            RatSighting rat = rats.get(j);
            CharSequence ratLocationType = rat.getLocationType();
            if (locationType.equals(ratLocationType)) {
                searchResults.add(rat);
            }
        }
        return searchResults;
    }
}
