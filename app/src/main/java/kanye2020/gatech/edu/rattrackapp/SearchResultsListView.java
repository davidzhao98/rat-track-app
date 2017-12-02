package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
//import android.support.v7.app.AlertController;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Structures the search results for ALL RAT data
 * May be adapted to use different adapters to show different search results,
 * this way we don't have to make a new class for every different kind of search results,
 * in other words, just changing the data the Adapter gets will allow us to use this search results
 * for different kinds of searches
 * Created by juliachen on 10/5/17.
 */

public class SearchResultsListView extends AppCompatActivity {

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


        //fragment stuff
        Intent intent = getIntent();
        String callingActivity = intent.getStringExtra("from");
        ListView searchResultsLV = (ListView) findViewById(R.id.searchResultsListView);
        RatSightingList ratSightingList = RatSightingList.getInstance();
        switch(callingActivity) {
            case "all":
                //demo view
                //RatSightingList demoRatList = RatSightingList.getInstance();

                ArrayAdapter<RatSighting> demoAdapter = new ArrayAdapter<>(
                        this, android.R.layout.simple_selectable_list_item, RatSightingList.getSample());
                demoAdapter.setDropDownViewResource(
                        android.R.layout.simple_selectable_list_item);
                searchResultsLV.setChoiceMode(
                        AbsListView.CHOICE_MODE_SINGLE);
                searchResultsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {

                        final List<RatSighting> sample = RatSightingList.getSample();
                        Intent intent = new Intent(view.getContext(), SingleRatInfoActivity.class);
                        intent.putExtra("clicked", sample.get(position));
                        intent.putExtra("position", position);
                        intent.putExtra("sighting", sample.get(position));
                        intent.putExtra("caller", "SearchResultsListView");
                        startActivity(intent);
                        //Toast.makeText(getBaseContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    }

                });
                searchResultsLV.setAdapter(demoAdapter);
                break;
            case "borough":
                String borough = intent.getStringExtra("borough");
                final List<RatSighting> boroughList = RatSightingList.sortByBorough(borough);
                ArrayAdapter<RatSighting> boroughAdapter = new ArrayAdapter<>(
                        this, android.R.layout.simple_selectable_list_item, boroughList);
                boroughAdapter.setDropDownViewResource(
                        android.R.layout.simple_selectable_list_item);
                searchResultsLV.setChoiceMode(
                        AbsListView.CHOICE_MODE_SINGLE);
                searchResultsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {

                        //final List<RatSighting> sample = RatSightingList.getSample();
                        Intent intent = new Intent(view.getContext(), SingleRatInfoActivity.class);
                        intent.putExtra("clicked", boroughList.get(position));
                        intent.putExtra("position", position);
                        //intent.putExtra("sighting", sample.get(position));
                        intent.putExtra("caller", "SearchResultsListView");
                        startActivity(intent);
                        //Toast.makeText(getBaseContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    }

                });
                searchResultsLV.setAdapter(boroughAdapter);
                break;
            case "locationType":
                String location = intent.getStringExtra("locationType");
                final List<RatSighting> locationList = RatSightingList.sortByLocationType(location);
                ArrayAdapter<RatSighting> locationAdapter = new ArrayAdapter<>(
                        this, android.R.layout.simple_selectable_list_item, locationList);
                locationAdapter.setDropDownViewResource(
                        android.R.layout.simple_selectable_list_item);
                searchResultsLV.setChoiceMode(
                        AbsListView.CHOICE_MODE_SINGLE);
                searchResultsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {

                        final List<RatSighting> sample = RatSightingList.getSample();
                        Intent intent = new Intent(view.getContext(), SingleRatInfoActivity.class);
                        intent.putExtra("clicked", locationList.get(position));
                        intent.putExtra("position", position);
                        intent.putExtra("sighting", sample.get(position));
                        intent.putExtra("caller", "SearchResultsListView");
                        startActivity(intent);
                        //Toast.makeText(getBaseContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    }

                });
                searchResultsLV.setAdapter(locationAdapter);
                break;
        }
        //use unique keys for rat list display
        //i think we need a list view adapter that adapts the data
        // from the database to the format that we need
        //make sure that when we click on a rat it will
        // take us to a new page that has more details about the rat
        //hitting back from that page will take us to the search results
        //https://www.youtube.com/watch?v=2pOCfKYO5Ao

    }


}
