package kanye2020.gatech.edu.rattrackapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
//import android.support.v7.app.AlertController;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Structures the search results for ALL RAT data
 * May be adapted to use different adapters to show different search results,
 * this way we don't have to make a new class for every different kind of search results,
 * in other words, just changing the data the Adapter gets will allow us to use this search results
 * for different kinds of searches
 * Created by juliachen on 10/5/17.
 */

public class SearchResultsListView extends AppCompatActivity {

    private RecyclerView searchResultsRV;
    private ListView searchResultsLV;
    private ArrayList<RatSighting> demoArrayList;
    //different adapters for different search results
    private RecyclerView.Adapter adapter;
    private ArrayAdapter demoAdapter;

//    /**
//     * constructor that has an adapter passed in from subclass that tells us what type of list we
//     * are getting
//     * @param adapter
//     */
//    public SearchResultsListView(ArrayAdapter<String> adapter) {
//        this.adapter = adapter;
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        /**
         * this shit is too much work so we can leave it for later
        searchResultsRV = (RecyclerView) findViewById(R.id.searchResultsRecyclerView);

        adapter = new RVAdapter(RatSightingList.getInstance().getRats());
        //adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        searchResultsRV.setAdapter(adapter);
        */

        //demo view
        demoArrayList = RatSightingList.getInstance().getSample();
        searchResultsLV = (ListView) findViewById(R.id.searchResultsListView);
        ArrayAdapter<RatSighting> demoAdapter = new ArrayAdapter(this,android.R.layout.simple_selectable_list_item, demoArrayList);
        demoAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        searchResultsLV.setAdapter(demoAdapter);
        //use unique keys for rat list display
        //i think we need a list view adapter that adapts the data from the database to the format that we need
        //make sure that when we click on a rat it will take us to a new page that has more details about the rat
        //hitting back from that page will take us to the search results
        //https://www.youtube.com/watch?v=2pOCfKYO5Ao

    }


}
