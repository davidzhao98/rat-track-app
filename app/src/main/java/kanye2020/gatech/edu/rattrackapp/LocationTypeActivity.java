package kanye2020.gatech.edu.rattrackapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.widget.ArrayAdapter;
import android.content.Intent;

import java.util.List;
import java.util.Arrays;

/**
 *
 * Activity that handles searching by Location Type
 * Created by Carissa Ghazalie
 */
public class LocationTypeActivity extends AppCompatActivity {

    private Spinner locationTypeSpinner;

    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_type);

        locationTypeSpinner = (Spinner) findViewById(R.id.locationTypeSpinner);
        Button searchLocationTypeButton = (Button) findViewById(R.id.locationTypeButton);
        Button viewMapButton = (Button) findViewById(R.id.viewMapButton);
        Button viewGraphButton = (Button) findViewById(R.id.viewGraphButton);

        List<String> locationTypes = Arrays.asList("1-2 Family Dwelling", "3+ Family Apt. Building",
                "3+ Family Mixed Use Building", "Commercial Building", "Vacant Lot",
                "Construction Site", "Hospital", "Catch Basin/Sewer");

        ArrayAdapter<String> locationTypeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locationTypes);
        locationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTypeSpinner.setAdapter(locationTypeAdapter);

        searchLocationTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = (String) locationTypeSpinner.getSelectedItem();
                Intent intent = new Intent(view.getContext(), SearchResultsListView.class);
                intent.putExtra("locationType", location);
                intent.putExtra("from", "locationType");
                startActivity(intent);
            }
        });

        viewMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), MapsActivity.class);
//                startActivity(intent);
                location = (String) locationTypeSpinner.getSelectedItem();
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                intent.putExtra("locationType", location);
                intent.putExtra("from", "locationType");
                startActivity(intent);
            }
        });

        viewGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = (String) locationTypeSpinner.getSelectedItem();
                Intent intent = new Intent(view.getContext(), GraphActivity.class);
                intent.putExtra("locationType", location);
                intent.putExtra("from", "locationType");
                startActivity(intent);
            }
        });
    }
}
