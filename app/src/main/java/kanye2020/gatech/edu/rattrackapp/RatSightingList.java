package kanye2020.gatech.edu.rattrackapp;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Singleton class to be used by the different search activities and the list view.
 * Contains all the rats stored in the database.
 *
 * Created by David Zhao on 10/6/2017.
 */

public class RatSightingList {
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
        //TODO
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
                    rats = new ArrayList<RatSighting>();
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
                    sample = new ArrayList<RatSighting>();
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

    public ArrayList<RatSighting> getRats() {
        return rats;
    }

    public int getRatsSize() {
        return rats.size();
    }

    public ArrayList<RatSighting> getSample() { return sample;}
}
