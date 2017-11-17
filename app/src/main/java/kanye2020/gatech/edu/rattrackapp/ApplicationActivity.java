package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 *
 * main application activity, all user options located here
 */
public class ApplicationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_view);

        Button logout = (Button) findViewById(R.id.logoutButton);
        //Button admin = (Button) findViewById(R.id.adminButton);
        Button reportNewRat = (Button) findViewById(R.id.reportButton);
        Button searchSightings = (Button) findViewById(R.id.searchButton);
        Button viewMap = (Button) findViewById(R.id.viewMapButton);
        Button viewGraph = (Button) findViewById(R.id.viewGraphButton);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        //sets OnClickListeners for searchSightings so it will do things when clicked
        searchSightings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        reportNewRat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ReportRatActivity.class);
                startActivity(intent);
            }
        });

        viewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                intent.putExtra("from", "viewAllMap");
                startActivity(intent);
            }
        });

        viewGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
