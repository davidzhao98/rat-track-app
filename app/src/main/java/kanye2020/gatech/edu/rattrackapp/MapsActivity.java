package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;
import java.util.Date;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<RatSighting> searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
}


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ArrayList<RatSighting> rats = RatSightingList.getInstance().getRats();
        searchResults = new ArrayList<>();
        String callingActivity = getIntent().getStringExtra("from");
        if (callingActivity.equals("date")) {
            try {
                String startDateText = getIntent().getStringExtra("startDate");
                String endDateText = getIntent().getStringExtra("endDate");
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date startDate = dateFormat.parse(startDateText);
                Date endDate = dateFormat.parse(endDateText);
//                searchResults = RatSightingList.getInstance().sortByDate(startDate, endDate);
//                displayMap(mMap, searchResults);
                for (int i = 0; i < 300; i++) {
                    int j = (int) (Math.random() * 100000);
                    RatSighting rat = rats.get(j);
                    String ratDateText = rat.getDateTime().substring(0, 11);
                    Date ratDate = dateFormat.parse(ratDateText);
                    if (ratDate.compareTo(startDate) >= 0 && ratDate.compareTo(endDate) <= 0) {
                        searchResults.add(rat);
                        displayRat(mMap, rat);
                    }
                }
            } catch(Exception e) {
                System.out.println(e);
            }
        } else if (callingActivity.equals("viewAllMap")) {
            for (int i = 0; i < 300; i++) {
                int j = (int) (Math.random() * 100000);
                RatSighting rat = rats.get(j);
                searchResults.add(rat);
                displayRat(mMap, rat);
            }
        } else if (callingActivity.equals("borough")) {
            for (int i = 0; i < 300; i++) {
                int j = (int) (Math.random() * 100000);
                RatSighting rat = rats.get(j);
                String borough = getIntent().getStringExtra("borough");
                if (rat.getBorough() != null) {
                    if (borough.equals(rat.getBorough())) {
                        searchResults.add(rat);
                        displayRat(mMap, rat);
                    }
                }
            }
        } else if (callingActivity.equals("locationType")) {
            for (int i = 0; i < 300; i++) {
                int j = (int) (Math.random() * 100000);
                RatSighting rat = rats.get(j);
                String locationType = getIntent().getStringExtra("locationType");
                if (locationType.equals(rat.getLocationType())) {
                    searchResults.add(rat);
                    displayRat(mMap, rat);
                }
            }
        }
//        mMap.setMinZoomPreference((float) 11);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getApplicationContext(), SingleRatInfoActivity.class);
                String id = marker.getTitle().substring(14);
                RatSighting clicked = null;
                for (RatSighting rat : searchResults) {
                    if (rat.getUniqueKey().equals(id)) {
                        clicked = rat;
                        break;
                    }
                }

                intent.putExtra("rat", clicked);
                intent.putExtra("caller", "MapsActivity");
                startActivity(intent);
            }
        });
    }

    public void displayRat(GoogleMap mMap, RatSighting rat) {
        String id = rat.getUniqueKey();
        String latitude = rat.getLatitude();
        String longitude = rat.getLongitude();
        try {
            Double lat = Double.parseDouble(latitude);
            Double lng = Double.parseDouble(longitude);
            LatLng ratLocation = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(ratLocation).title("Marker of Rat " + id));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ratLocation, 11));
        } catch(Exception e) {
            System.out.println("One of the rats can't be displayed");
        }
    }
}
