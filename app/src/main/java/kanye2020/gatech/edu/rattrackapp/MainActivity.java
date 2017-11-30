package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by pulakazad on 9/24/17
 */
public class MainActivity extends AppCompatActivity {

//    private Button login;
//    private Button register;
//    private RatSightingList ratDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Is this where we should get all the rat data as well?
        //getRats();
        //loading all the data into the model
        RatSightingList ratDB = RatSightingList.getInstance();

        //Buttons for login screen
        Button login = (Button) findViewById(R.id.loginButton);
        Button register = (Button) findViewById(R.id.registerButton);


        //sets OnClickListeners for login so it will do things when clicked
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //sets OnClickListeners for register so it will do things when clicked
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}
