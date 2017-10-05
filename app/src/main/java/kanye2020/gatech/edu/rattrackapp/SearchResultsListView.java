package kanye2020.gatech.edu.rattrackapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by juliachen on 10/5/17.
 */

public class SearchResultsListView extends AppCompatActivity {

    private ListView searchResultsLV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        searchResultsLV = (ListView) findViewById(R.id.searchResultsListView);
        //use unique keys for rat list display
        //i think we need a list view adapter that adapts the data from the database to the format that we need
        //make sure that when we click on a rat it will take us to a new page that has more details about the rat
        //hitting back from that page will take us to the search results
        //https://www.youtube.com/watch?v=2pOCfKYO5Ao

    }
}
