package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ReportRatActivity extends AppCompatActivity {

    private Spinner locationTypeSpinner;
    private EditText boroughText;
    private EditText zipcodeText;
    private EditText addressText;
    private EditText cityText;
    private Button addRSButton;
    private Button cancelRSButton;

    private String borough;
    private String zipcode;
    private String address;
    private String city;

    public ReportRatActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_rat);

        //initialize all of the content on the .xml file
        boroughText = (EditText) findViewById(R.id.boroughText);
        zipcodeText = (EditText) findViewById(R.id.zipcodeText);
        addressText = (EditText) findViewById(R.id.addressText);
        cityText = (EditText) findViewById(R.id.cityText);
        addRSButton = (Button) findViewById(R.id.addRSButton);
        cancelRSButton = (Button) findViewById(R.id.cancelRSButton);

        borough = boroughText.getText().toString();
        zipcode = zipcodeText.getText().toString();
        address = addressText.getText().toString();
        city = cityText.getText().toString();

        addRSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //user has to fill out the entire form
                //yo we are missing borough in the .xml
                if (!(borough.isEmpty() && zipcode.isEmpty() && address.isEmpty() && city.isEmpty())) {
                    RatSighting newEntry = new RatSighting(borough, city, address, zipcode, "locationtype", "datetime", "lat", "long", "key");
                } else {
                    Toast.makeText(view.getContext(), "You have to fill out everything", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelRSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ApplicationActivity.class);
                startActivity(intent);
            }
        });
    }
}
