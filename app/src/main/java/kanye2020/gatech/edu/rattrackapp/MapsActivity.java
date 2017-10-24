package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
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

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        ArrayList<RatSighting> rats = RatSightingList.getInstance().getRats();
        searchResults = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            int j = (int) (Math.random() * 100000);
            RatSighting rat = rats.get(j);
            searchResults.add(rat);
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
                startActivity(intent);
            }
        });
    }
}
