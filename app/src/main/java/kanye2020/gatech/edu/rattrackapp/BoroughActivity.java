package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Arrays;

/**
 *
 * Class for selecting and searching Boroughs.
 */
public class BoroughActivity extends AppCompatActivity {

    private Spinner boroughSpinner;
//    private Button searchBoroughButton;
//    private Button viewMapButton;
//    private Button viewGraphButton;

    private String borough;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borough);

        boroughSpinner = (Spinner) findViewById(R.id.boroughSearchSpinner);
        Button searchBoroughButton = (Button) findViewById(R.id.searchBoroughButton);
        Button viewMapButton = (Button) findViewById(R.id.viewMapButton);
        Button viewGraphButton = (Button) findViewById(R.id.viewGraphButton);

        List<String> boroughs = Arrays.asList("MANHATTAN", "STATEN ISLAND", "QUEENS", "BROOKLYN",
                "BRONX");

        ArrayAdapter<String> boroughAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, boroughs);
        boroughAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(boroughAdapter);


        searchBoroughButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borough = (String) boroughSpinner.getSelectedItem();
                // how do you filter search results
                Intent intent = new Intent(view.getContext(), SearchResultsListView.class);
                intent.putExtra("from", "borough");
                intent.putExtra("borough", borough);
                startActivity(intent);
            }
        });

        viewMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), MapsActivity.class);
//                startActivity(intent);
                borough = (String) boroughSpinner.getSelectedItem();
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                intent.putExtra("borough", borough);
                intent.putExtra("from", "borough");
                startActivity(intent);
            }
        });

        viewGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), GraphActivity.class);
//                startActivity(intent);
            }
        });

    }
}
