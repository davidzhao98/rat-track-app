package kanye2020.gatech.edu.rattrackapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Carissa on 12/2/17.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
