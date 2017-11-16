package kanye2020.gatech.edu.rattrackapp;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by juliachen on 9/24/17.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private ArrayList<Account> entries = new ArrayList<>();
    private int loginAttempts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //Text fields for username and password
        usernameField = (EditText) findViewById(R.id.userNameText);
        passwordField = (EditText) findViewById(R.id.passwordEditText2);

        //Populates the array of Accounts
        getUsers();

        //Buttons for login, set OnClickListener
        Button login = (Button) findViewById(R.id.loginButton2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginAttempts >= 3) {
                    lockoutUser();
                }

                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                if (username.isEmpty()) {
                    usernameField.setError("Enter username!");
                }
                if (password.isEmpty()) {
                    passwordField.setError("Enter password!");
                }

                if (loginVerification()) {
                    System.out.println("Logging in");
                    resetLogin();
                    Intent intent = new Intent(view.getContext(), ApplicationActivity.class);
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
     * locks user out after 3 failed attempts at login in
     */
    private void lockoutUser() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

    }

    /**
     * verifies that user input the correct username and password
     * @return true if user and password correct
     */
    private boolean loginVerification() {
        for (Account entry : entries) {
            if (usernameField.getText().toString().equals(entry.getUsername()) && passwordField.getText().toString().equals(entry.getPassword())) {
                return true;
            }
        }
        loginAttempts++;
        return false;
    }

    /**
     * resets login information so user has to re-login every time, for safety
     */
    public void resetLogin() {
        usernameField.setText("");
        passwordField.setText("");
    }

    /**
     * populates the ArrayList with all Accounts stored in the database
     */
    private void getUsers() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                Toast.makeText(LoginActivity.this, "Total Users: " + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();
                entries.clear();
                while (items.hasNext()) {
                    DataSnapshot item = items.next();

                    String username, password, email;
                    boolean admin;
                    username = item.child("username").getValue().toString();
                    password = item.child("password").getValue().toString();
                    email = item.child("email").getValue().toString();
                    admin = item.child("admin").getValue().toString().equals("true");
                    Account entry = new Account(username, password, email, admin);
                    entries.add(entry);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
