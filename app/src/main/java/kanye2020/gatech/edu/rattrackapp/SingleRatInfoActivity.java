package kanye2020.gatech.edu.rattrackapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Acts as the activity for one single rat when rat is selected from a searched list
 */
public class SingleRatInfoActivity extends AppCompatActivity {
    RatSighting sighting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rat_sighting_info);
        TextView uniqueKeyTV = (TextView) findViewById(R.id.UniqueKey);
        TextView createdDateTV = (TextView) findViewById(R.id.CreatedDate);
        TextView locationTypeTV = (TextView) findViewById(R.id.LocationType);
        TextView incidentZipTV = (TextView) findViewById(R.id.IncidentZip);
        TextView incidentAddressTV = (TextView) findViewById(R.id.IncidentAddress);
        TextView cityTV = (TextView) findViewById(R.id.City);
        TextView boroughTV = (TextView) findViewById(R.id.Borough);
        TextView longitudeTV = (TextView) findViewById(R.id.Longitude);
        TextView latitudeTV = (TextView) findViewById(R.id.Latitude);

        try {
            int pos = getIntent().getIntExtra("position", -1);
            ArrayList<RatSighting> sightingList = RatSightingList.getInstance().getSample();
            sighting = sightingList.get(pos);
            System.out.print(sighting.toString());
        } catch(Exception e) {
            System.out.println(e);
        }

        try {
            sighting = (RatSighting) getIntent().getExtras().getParcelable("rat");
        } catch(Exception e) {
            System.out.print(e);
        }

        uniqueKeyTV.setText(sighting.getUniqueKey());
        createdDateTV.setText(sighting.getDateTime());
        locationTypeTV.setText(sighting.getLocationType());
        incidentZipTV.setText(sighting.getZipcode());
        incidentAddressTV.setText(sighting.getAddress());
        cityTV.setText(sighting.getCity());
        boroughTV.setText(sighting.getBorough());
        longitudeTV.setText(sighting.getLongitude());
        latitudeTV.setText(sighting.getLatitude());
    }
}
