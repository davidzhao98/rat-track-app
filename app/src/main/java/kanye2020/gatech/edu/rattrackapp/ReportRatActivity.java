package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.widget.ArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class ReportRatActivity extends AppCompatActivity {

    private Spinner locationTypeSpinner;
    private Spinner boroughSpinner;
    private Spinner monthSpinner;
    private Spinner daySpinner;
    private Spinner yearSpinner;
    private EditText zipcodeText;
    private EditText addressText;
    private EditText cityText;

    @PropertyName("Longitude")
    private String longitude;

    @PropertyName("Latitude")
    private String latitude;

    @PropertyName("Created Date")
    private String time;

    private Button addRSButton;
    private Button cancelRSButton;

    @PropertyName("Borough")
    private String borough;

    @PropertyName("Incident Zip")
    private String zipcode;

    @PropertyName("Incident Address")
    private String address;

    @PropertyName("City")
    private String city;

    @PropertyName("Location Type")
    private String locationType;

    private String month, day, year;

    public ReportRatActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_rat);

        //making new GPSGetter and getting long and lat
        GetGPSLocationActivity GPS =  new GetGPSLocationActivity();
        longitude = GPS.getLongitude();
        latitude = GPS.getLatitude();

        //getting current time
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);
        String hr = Integer.toString(hour);
        String min = Integer.toString(minutes);
        time = hr + ":" + min;

        //initialize all of the content on the .xml file
        locationTypeSpinner = (Spinner) findViewById(R.id.locationTypeSpinner);
        boroughSpinner = (Spinner) findViewById(R.id.boroughSpinner);
        zipcodeText = (EditText) findViewById(R.id.zipcodeText);
        addressText = (EditText) findViewById(R.id.addressText);
        cityText = (EditText) findViewById(R.id.cityText);
        monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
        daySpinner = (Spinner) findViewById(R.id.daySpinner);
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        addRSButton = (Button) findViewById(R.id.addRSButton);
        cancelRSButton = (Button) findViewById(R.id.cancelRSButton);

        //Creates lists for boroughs, location types, months, days, and years
        List<String> boroughs = Arrays.asList("MANHATTAN", "STATEN ISLAND", "QUEENS", "BROOKLYN", "BRONX");
        List<String> locationTypes = Arrays.asList("1-2 Family Dwelling", "3+ Family Apt. Building",
                "3+ Family Mixed Use Building", "Commercial Building", "Vacant Lot", "Construction Site",
                "Hospital", "Catch Basin/Sewer");
        List<String> months = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        List<String> days = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
                "28", "29", "30", "31");
        List<String> years = Arrays.asList("2015", "2016", "2017", "2018", "2019", "2020");

        //Adapter for borough Spinner
        ArrayAdapter<String> boroughAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, boroughs);
        boroughAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(boroughAdapter);

        //Adapter for locationType spinner
        ArrayAdapter<String> locationTypeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, locationTypes);
        locationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTypeSpinner.setAdapter(locationTypeAdapter);

        //Adapter for Month Spinner
        ArrayAdapter<String> monthAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, months);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        //Adapter for day Spinner
        ArrayAdapter<String> dayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, days);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        //Adapter for year Spinner
        ArrayAdapter<String> yearAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);


        //add ratsighting button and makes sure entire form is complete
        addRSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //user has to fill out the entire form
                //gets the data from the user
                locationType = (String) locationTypeSpinner.getSelectedItem();
                borough = (String) boroughSpinner.getSelectedItem();
                month = (String) monthSpinner.getSelectedItem();
                day = (String) daySpinner.getSelectedItem();
                year = (String) yearSpinner.getSelectedItem();
                zipcode = zipcodeText.getText().toString();
                address = addressText.getText().toString();
                city = cityText.getText().toString();
                if (!(zipcodeText.getText().toString().equals("")) && !(addressText.getText().toString().equals("")) && !(cityText.getText().toString().equals(""))) {
//                    RatSighting newEntry = new RatSighting(borough, city, address, zipcode, locationType, (month + "/" + day + "/" + year + " " + time), latitude, longitude, "key");
//                    ArrayList<RatSighting> ratList = RatSightingList.getInstance().getRats();
//                    ratList.add(newEntry);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("ratdata");
//                    ArrayList<String> list = new ArrayList<String>();
//                    list.add("Borough");
//                    list.add("City");
//                    list.add("Created Date");
//                    list.add("Incident Address");
//                    list.add("Incident Zip");
//                    list.add("Latitude");
//                    list.add("Location Type");
//                    list.add("Longitude");
//                    list.add("Unique Key");
//                    list.setTitle()
                    int index = RatSightingList.getInstance().getRatsSize();
                    System.out.println(index);
                    String ind = "99100";

                    myRef.child(ind).setValue(null);
                    myRef.child(ind).child("Borough").setValue(borough);
                    myRef.child(ind).child("City").setValue(city);
                    myRef.child(ind).child("Created Date").setValue(month + "/" + day + "/" + year + " " + time);
                    myRef.child(ind).child("Incident Address").setValue(address);
                    myRef.child(ind).child("Incident Zip").setValue(zipcode);
                    myRef.child(ind).child("Latitude").setValue(latitude);
                    myRef.child(ind).child("Location Type").setValue(locationType);
                    myRef.child(ind).child("Longitude").setValue(longitude);
                    myRef.child(ind).child("Unique Key").setValue("key");

                    System.out.println("RAT ADDED");
                    Toast.makeText(view.getContext(), "Your Rat Sighting was successfully entered!", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(view.getContext(), ApplicationActivity.class);
//                    startActivity(intent);

                } else {
                    Toast.makeText(view.getContext(), "One or more fields are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //cancelling makes the app go back to previous screen which is the homescreen AppActivity
        cancelRSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ApplicationActivity.class);
                startActivity(intent);
            }
        });
    }

}
