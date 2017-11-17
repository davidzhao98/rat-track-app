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
import java.util.List;

/**
Constructor for MapsActivity.
Creates the GoogleMap, and makes it available to use.
 **/
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

//    private GoogleMap mMap;
    private List<RatSighting> searchResults;
    private static final int zoomFactor = 11;
    private static final int threshold = 300;

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
    public void onMapReady(GoogleMap mMap) {
//        GoogleMap mMap = googleMap;
        List<RatSighting> rats = RatSightingList.getRats();
        int size = rats.size();
        searchResults = new ArrayList<>();
        Intent intent = getIntent();
        String callingActivity = intent.getStringExtra("from");
        switch (callingActivity) {
            case "date":
//        if (callingActivity.equals("date")) {
                try {
                    String startDateText = intent.getStringExtra("startDate");
                    String endDateText = intent.getStringExtra("endDate");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    Date startDate = dateFormat.parse(startDateText);
                    Date endDate = dateFormat.parse(endDateText);
    //                searchResults = RatSightingList.getInstance().sortByDate(startDate, endDate);
    //                displayMap(mMap, searchResults);
                    for (int i = 0; i < threshold; i++) {
                        int j = (int) (Math.random() * size);
                        RatSighting rat = rats.get(j);
                        String ratDateTime = rat.getDateTime();
                        String ratDateText = ratDateTime.substring(0, "MM/dd/yyyy".length() + 1);
                        Date ratDate = dateFormat.parse(ratDateText);
                        if ((ratDate.compareTo(startDate) >= 0)
                                && (ratDate.compareTo(endDate) <= 0)) {
                            searchResults.add(rat);
                            displayRat(mMap, rat);
                        }
                    }
                } catch(Exception e) {
    //                System.out.println(e);
                }
                break;
            case "viewAllMap":
                for (int i = 0; i < threshold; i++) {
                    int j = (int) (Math.random() * size);
                    RatSighting rat = rats.get(j);
                    searchResults.add(rat);
                    displayRat(mMap, rat);
                }
                break;
            case "borough":
                for (int i = 0; i < threshold; i++) {
                    int j = (int) (Math.random() * size);
                    RatSighting rat = rats.get(j);
                    String borough = intent.getStringExtra("borough");
                    if (rat.getBorough() != null) {
                        if (borough.equals(rat.getBorough())) {
                            searchResults.add(rat);
                            displayRat(mMap, rat);
                        }
                    }
                }
                break;
            case "locationType":
                for (int i = 0; i < threshold; i++) {
                    int j = (int) (Math.random() * size);
                    RatSighting rat = rats.get(j);
                    String locationType = intent.getStringExtra("locationType");
                    if (locationType.equals(rat.getLocationType())) {
                        searchResults.add(rat);
                        displayRat(mMap, rat);
                    }
                }
        }
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getApplicationContext(), SingleRatInfoActivity.class);
                String title = marker.getTitle();
                String id = title.substring("Marker of Rat ".length());
                RatSighting clicked = null;
                for (RatSighting rat : searchResults) {
                    String key = rat.getUniqueKey();
                    if (key.equals(id)) {
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

    private void displayRat(GoogleMap mMap, RatSighting rat) {
        String id = rat.getUniqueKey();
        String latitude = rat.getLatitude();
        String longitude = rat.getLongitude();
        try {
            Double lat = Double.parseDouble(latitude);
            Double lng = Double.parseDouble(longitude);
            LatLng ratLocation = new LatLng(lat, lng);
            MarkerOptions marker = new MarkerOptions();
            marker = marker.position(ratLocation);
            marker = marker.title("Marker of Rat " + id);
            mMap.addMarker(marker);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ratLocation, zoomFactor));
        } catch(Exception e) {
//            System.out.println("One of the rats can't be displayed");
        }
    }
}
