package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button login;
    Button register;
    RatSightingList ratDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Is this where we should get all the rat data as well?
        //getRats();
        //loading all the data into the model
        ratDB = RatSightingList.getInstance();

        //Buttons for login screen
        login = (Button) findViewById(R.id.loginButton);
        register = (Button) findViewById(R.id.registerButton);


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
