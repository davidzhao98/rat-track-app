package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by juliachen on 9/24/17.
 */

public class LoginActivity extends AppCompatActivity {

    EditText usernameField;
    EditText passwordField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //Text fields for username and password
        usernameField = (EditText) findViewById(R.id.userNameText);
        passwordField = (EditText) findViewById(R.id.passwordEditText2);

        //Buttons for login, set OnClickListener
        Button login = (Button) findViewById(R.id.loginButton2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginVerification()) {
                    System.out.println("Logging in");
                    resetLogin();
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    System.out.println("Fail");
                    Toast toast = Toast.makeText(view.getContext(), "Incorrect login information!",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                }
            }
        });
    }

    /**
     * verifies that user input the correct username and password
     * @return true if user and password correct
     */
    private boolean loginVerification() {
        return usernameField.getText().toString().equals("user")
                && passwordField.getText().toString().equals("pass");
    }

    /**
     * resets login information so user has to re-login every time, for safety
     */
    public void resetLogin() {
        usernameField.setText("");
        passwordField.setText("");
    }
}
