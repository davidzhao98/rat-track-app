package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Acts as the activity for one single rat when rat is selected from a searched list
 */
public class SingleRatInfoActivity extends AppCompatActivity {
    private RatSighting sighting;

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

        Intent intent = getIntent();
        sighting = getSighting(intent);





        uniqueKeyTV.setText((sighting != null) ? sighting.getUniqueKey() : null);
        createdDateTV.setText(sighting.getDateTime());
        locationTypeTV.setText(sighting.getLocationType());
        incidentZipTV.setText(sighting.getZipcode());
        incidentAddressTV.setText(sighting.getAddress());
        cityTV.setText(sighting.getCity());
        boroughTV.setText(sighting.getBorough());
        longitudeTV.setText(sighting.getLongitude());
        latitudeTV.setText(sighting.getLatitude());
    }

    private RatSighting getSighting(Intent intent) {
        String caller = intent.getStringExtra("caller");
        if ("SearchResultsListView".equals(caller)) {
            try {
                sighting = intent.getParcelableExtra("sighting");
            } catch(Exception e) {
                Log.e("Exception", e.getMessage());
            }
        } else if ("MapsActivity".equals(caller)) {
            try {
                Bundle extras = intent.getExtras();
                sighting = extras.getParcelable("rat");
            } catch(Exception e) {
                Log.e("Exception", e.getMessage());
            }
        }
        return sighting;
    }
}
