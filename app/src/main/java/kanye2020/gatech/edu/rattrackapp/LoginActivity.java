package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * Created by juliachen on 9/24/17.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private String username;
    private String password;
    private int loginAttempts;
    private boolean lockd;
    private final Collection<Account> entries = new ArrayList<>();

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
                Editable nameUser = usernameField.getText();
                username = nameUser.toString();
                Editable fieldPass = passwordField.getText();
                password = fieldPass.toString();
                //boolean lockedOut = firebaseGetLockedOut();


                Editable temp = usernameField.getText();
                String username = temp.toString();
                Editable temp2 = passwordField.getText();
                String password = temp2.toString();

                if (username.isEmpty()) {
                    usernameField.setError("Enter username!");
                }
                if (password.isEmpty()) {
                    passwordField.setError("Enter password!");
                }

                if (loginVerification(username, password, entries)) {
                    //login successful
                    resetLogin(username);
                    Intent intent = new Intent(view.getContext(), ApplicationActivity.class);
                    startActivity(intent);
                } else if (lockd == true) {
                    //locked out
                    Toast toast = Toast.makeText(view.getContext(), "You are locked out!",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                } else {
                    //login failed
                    Toast toast = Toast.makeText(view.getContext(), "Incorrect login information!",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                }

                if (checkUsernameExistence()) {
                    if (loginAttempts == 1) {
                        Toast toast = Toast.makeText(view.getContext(), "You have ome more" +
                                        " attempt left!",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM, 0, 0);
                        toast.show();
                    }
                    if (loginAttempts >= 3) {
                        lockoutUser();
                        lockd = true;
                    }
                }
                System.out.println(loginAttempts);

            }
        });
    }

    /**
     * checks to see if username exists
     * @return the existence of the username as a boolean
     */
    private boolean checkUsernameExistence() {
        for (Account entry : entries) {
            if (username.equals(entry.getUsername())) {
                return true;
            }
        }
        return false;
    }


    /**
     * locks user out after 3 failed attempts at login in
     */
    private void lockoutUser() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        try {
            DatabaseReference chainLocked = myRef.child(username);
            DatabaseReference chainLocked2 = chainLocked.child("lockedout");
            chainLocked2.setValue(true);
        } catch (Exception e) {
//            System.out.println("user not found");
        }
    }

    /**
     * verifies that user input the correct username and password
     * @param username the user's username
     * @param password the user's password
     * @param entries the accounts
     * @return true if user and password correct
     */
    public boolean loginVerification(String username, String password, Iterable<Account> entries) {
        if ((username == null) || (password == null) || (entries == null)) {
            return false;
        }

        for (Account entry : entries) {
            if (username.equals(entry.getUsername())
                    && password.equals(entry.getPassword())
                    && !entry.lockedout) {
                return true;
            } else if (username.equals(entry.getUsername())
                    && !password.equals(entry.getPassword())
                    && !entry.lockedout) {
                increaseLoginAttempts(entry);
            }
        }
        return false;
    }

    /**
     * increase the number of failed login attempts that the user has
     * @param entry the current username
     */
    private void increaseLoginAttempts(Account entry) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(entry.getUsername());
        int currentLoginAttempts = entry.getAttempts();
        loginAttempts = entry.getAttempts();
        myRef.child("attempts").setValue(currentLoginAttempts + 1);
        loginAttempts++;
    }

    /**
     * resets login information so user has to re-login every time, for safety
     * @param username
     */
    private void resetLogin(String username) {
        usernameField.setText("");
        passwordField.setText("");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user").child(username);
        myRef.child("attempts").setValue(0);
        myRef.child("lockedout").setValue(false);
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
                Iterable<DataSnapshot> chain = dataSnapshot.getChildren();
                Iterator<DataSnapshot> items = chain.iterator();
                Toast temp = Toast.makeText(LoginActivity.this, "Total Users: "
                        + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT);
                temp.show();
                entries.clear();
                while (items.hasNext()) {
                    DataSnapshot item = items.next();

                    String username;
                    String password;
                    String email;
                    boolean admin;


                    DataSnapshot outLocked = item.child("lockedout");
                    boolean lockedout = (boolean) outLocked.getValue();

                    DataSnapshot nameUser = item.child("username");
                    Object userWord = nameUser.getValue();
                    username = userWord.toString();

                    DataSnapshot wordPass = item.child("password");
                    Object word = wordPass.getValue();
                    password = word.toString();

                    DataSnapshot mail = item.child("email");
                    Object snail = mail.getValue();
                    email = snail.toString();

                    DataSnapshot administration = item.child("admin");
                    Object word2 = administration.getValue();
                    String administrator = word2.toString();

                    DataSnapshot att = item.child("attempts");
                    int attem = (int) (long) att.getValue();

                    admin = "true".equals(administrator);

                    Account entry = new Account(username, password, email, admin, lockedout, attem);
                    entries.add(entry);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
