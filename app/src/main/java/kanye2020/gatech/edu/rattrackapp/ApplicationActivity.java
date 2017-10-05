package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by juliachen on 9/24/17.
 */

public class ApplicationActivity extends AppCompatActivity {

    Button logout;
    Button admin;
    Button reportNewRat;
    Button searchSightings;
    Button viewMap;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_view);

        logout = (Button) findViewById(R.id.logoutButton);
        admin = (Button) findViewById(R.id.adminButton);
        reportNewRat = (Button) findViewById(R.id.reportButton);
        searchSightings = (Button) findViewById(R.id.searchButton);
        viewMap = (Button) findViewById(R.id.viewMapButton);

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

    }
}
