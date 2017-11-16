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

class RatSightingList {
    private ArrayList<RatSighting> rats;
    private ArrayList<RatSighting> sample;
    private static RatSightingList instance;

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
                System.out.println("Reading in rat data");
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                //Toast.makeText(RatSightingList.this, "Rats: " + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();

                //apparently rats is null
                if (rats == null) {
                    rats = new ArrayList<>();
                    while (items.hasNext()) {
                        DataSnapshot item = items.next();
                        String borough, city, createdDate, incidentAddress, incidentZip, latitude,
                                locationType, longitude, uniqueKey;
                        borough = item.child("Borough").getValue().toString();
                        city = item.child("City").getValue(String.class);
                        locationType = item.child("Location Type").getValue().toString();
                        createdDate = item.child("Created Date").getValue().toString();
                        incidentAddress = item.child("Incident Address").getValue().toString();
                        incidentZip = item.child("Incident Zip").getValue().toString();
                        latitude = item.child("Latitude").getValue().toString();
                        longitude = item.child("Longitude").getValue().toString();
                        uniqueKey = item.child("Unique Key").getValue().toString();
                        RatSighting entry = new RatSighting(borough, city, incidentAddress,
                                incidentZip, locationType, createdDate, latitude, longitude, uniqueKey);
                        rats.add(entry);
                    }
                    sample = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        sample.add(rats.get(i));
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Unable to read in rat data");
            }
        });
    }

    /**
     * @return the arrayList of rats
     */
    static ArrayList<RatSighting> getRats() {
        return getInstance().rats;
    }

    /**
     *
     * @return returns the size of the arrayList
     */
    int getRatsSize() {
        return rats.size();
        }

    /**
     *
     * @return the sample arrayList
     */
    static ArrayList<RatSighting> getSample() { return getInstance().sample;}

    /**
     * method for searching rats by date given a starting date and ending date
     *
     * @param start the starting date for the query
     * @param end the ending date for the query
     * @return an ArrayList of RatSightings that were reported between Date @start and Date @end
     */
    List<RatSighting> sortByDate(Date start, Date end) throws IllegalArgumentException {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Start date cannot exceed end date.");
        }
        ArrayList<RatSighting> searchResults = new ArrayList<>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            for (int i = 0; i < 300; i++) {
                int j = (int) (Math.random() * 100000);
                RatSighting rat = rats.get(j);
                String ratDateText = rat.getDateTime().substring(0, 11);
                Date ratDate = dateFormat.parse(ratDateText);
                if (ratDate.compareTo(start) >= 0 && ratDate.compareTo(end) <= 0) {
                    searchResults.add(rat);
                }
            }
        } catch(Exception e) {
//            System.out.println(e);
        }
        return searchResults;
    }
}
