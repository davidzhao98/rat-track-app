package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by pulakazad on 9/24/17.
 * Updated by Carissa 10/1/17
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText emailET;
    private EditText confirmEmailET;
    private EditText usernameET;
    private EditText passwordET;
    private EditText confirmPwET;
    private Spinner accountTypeSpinner;
    private Button registerAcct;
//    private ArrayList<Account> accountList;
//    private static final String TAG = "RegisterActivity";
//    private List<Account> realAccountList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //initialize all of the text fields, spinner, and button on the register page
        //note: Register button on register.xml has id "createAcctButton"
        emailET = (EditText) findViewById(R.id.emailEditText);
        confirmEmailET = (EditText) findViewById(R.id.emailConfirmEditText);
        usernameET = (EditText) findViewById(R.id.usernameEditText);
        passwordET = (EditText) findViewById(R.id.passwordEditText);
        confirmPwET = (EditText) findViewById(R.id.confirmPasswordET);
        accountTypeSpinner = (Spinner) findViewById(R.id.accountTypeSpinner);
        registerAcct = (Button) findViewById(R.id.createAcctButton);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Account.accountTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);

        //Button for cancel, OnClickListener has been set to point to mainActivity
        Button cancel = (Button) findViewById(R.id.cancelAcctButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        registerAcct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwString = passwordET.getText().toString();
                String confirmString = confirmPwET.getText().toString();
                String emailString = emailET.getText().toString();
                String emailStringConfirm = confirmEmailET.getText().toString();
                String usernameString = usernameET.getText().toString();
                if (fieldsNotEmpty(emailString, usernameString, pwString)
                        && confirmPassword(pwString, confirmString)
                        && confirmEmail(emailString, emailStringConfirm)) {
                    //add account to "database"?????
                    //firebase?
                    //switch to new screen/application screen
//                    accountList = new ArrayList<>();
                    final Account newAccount =
                            new Account(usernameET.getText().toString(),
                                    passwordET.getText().toString(),
                                    emailET.getText().toString(),
                                    accountTypeSpinner.getSelectedItem().equals("ADMIN"),
                                    false);
//                    accountList.add(newAccount);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("users");
                    myRef.child(newAccount.getUsername()).setValue(newAccount);
//                    myRef.setValue(accountList);
                    /*myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            realAccountList = (List<Account>) dataSnapshot.getValue();
                            Log.d(TAG, "Value is: " + value);
                            realAccountList.add(newAccount);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w(TAG, "Failed to read value.", error.toException());
                        }
                    });
                    myRef.setValue(realAccountList);*/

                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * checks to see if user did not leave fields empty; we should make the spinner default "user"
     * so it'll either be user or admin
     * @param email the entered email
     * @param username the entered username
     * @param password the entered password
     * @return true if all fields are filled
     */
    public boolean fieldsNotEmpty(String email, String username, String password) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        if (username == null || username.isEmpty()) {
            return false;
        }
        if (password == null || password.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * method checks if user entered the same password both times
     * @param et1 first password entry
     * @param et2 second password entry
     * @return true if passwords match
     */
    public boolean confirmPassword(String et1, String et2) {
        try {
            if (et1.equals(et2)) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * method to check that both email entry fields match
     * @param email1 the first entered email
     * @param email2 the second entered email
     * @return whether or not the emails match as a boolean
     */
    public boolean confirmEmail(String email1, String email2) {
        try {
            if (email1.equals(email2)) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * method checks if user entered a valid email address
     * @param email the entered email to check
     * @return true if email address is valid, false if not.
     */
    public boolean emailValidCheck(String email) {
        if (!email.startsWith("@")) {
            if (email.contains("@") && email.split("@", 2)[1].contains(".")) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
}
