package kanye2020.gatech.edu.rattrackapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by juliachen on 12/2/17.
 */

public class AdminOptionsActivity extends AppCompatActivity {

    private EditText findUser;
    private Button unban;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_options_view);

        findUser = (EditText) findViewById(R.id.adminNameSearch);
        unban = (Button) findViewById(R.id.unbanButton);

        unban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("users");
                DatabaseReference account = myRef.child(findUser.getText().toString());
                if (account != null) {
                    account.child("attempts").setValue(0);
                    account.child("lockedout").setValue(false);
                    Toast toast = Toast.makeText(view.getContext(), "You have unlocked the user",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(view.getContext(), "Please enter a valid username",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                }
            }
        });


    }
}
