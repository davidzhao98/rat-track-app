package kanye2020.gatech.edu.rattrackapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by pulakazad on 9/24/17.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText emailET;
    private EditText usernameET;
    private EditText passwordET;
    private EditText confirmPwET;
    private Spinner accountTypeSpinner;
    private Button registerAcct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //initialize all of the text fields, spinner, and button on the register page
        //note: Register button on register.xml has id "createAcctButton"
        emailET = (EditText) findViewById(R.id.emailEditText);
        usernameET = (EditText) findViewById(R.id.usernameEditText);
        passwordET = (EditText) findViewById(R.id.passwordEditText);
        confirmPwET = (EditText) findViewById(R.id.confirmPasswordET);
        accountTypeSpinner = (Spinner) findViewById(R.id.accountTypeSpinner);
        registerAcct = (Button) findViewById(R.id.createAcctButton);

        registerAcct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (confirmPassword() && fieldsNotEmpty()) {
                    //add account to "database"?????
                    //firebase?
                    //switch to new screen/application screen
                    Account newAccount = new Account(usernameET.getText().toString(), passwordET.getText().toString(), emailET.getText().toString());
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("users/" + emailET.getText().toString());
                    myRef.setValue(newAccount);
                }
            }
        });
    }

    /**
     * checks to see if user did not leave fields empty; we should make the spinner default "user"
     * so it'll either be user or admin
     * @return true if all fields are filled
     */
    private boolean fieldsNotEmpty() {
        return !(emailET.getText().toString().isEmpty()
                || usernameET.getText().toString().isEmpty()
                || passwordET.getText().toString().isEmpty());
    }

    /**
     * method checks if user entered the same password both times
     * @return true if passwords match
     */
    private boolean confirmPassword() {
        return passwordET.getText().toString().equals(confirmPwET.getText().toString());
    }
}
