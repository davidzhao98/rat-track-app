package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 *
 * Activity to manage searching options
 */
public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button dateRange = (Button) findViewById(R.id.dateRangeButton);
        Button borough = (Button) findViewById(R.id.boroughButton);
        Button locationType = (Button) findViewById(R.id.locationTypeButton);
        Button viewAll = (Button) findViewById(R.id.viewAllButton);

        dateRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DateRangeActivity.class);
                startActivity(intent);
            }
        });

        borough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BoroughActivity.class);
                intent.putExtra("from", "borough");
                startActivity(intent);
            }
        });

        locationType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LocationTypeActivity.class);
                startActivity(intent);
            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SearchResultsListView.class);
                intent.putExtra("from", "all");
                startActivity(intent);
            }
        });
    }


}
