package kanye2020.gatech.edu.rattrackapp;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nick B on 10/29/2017.
 */

public class GraphActivity extends AppCompatActivity {

    ArrayList<RatSighting> searchResults;
    RatSightingList ratz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        double x,y;
        x = 0.0;

        GraphView graph = (GraphView) findViewById(R.id.graphView);
        HashMap<Double, Double> months = new HashMap<>();


        //
        ArrayList<RatSighting> rats = RatSightingList.getInstance().getRats();
        searchResults = new ArrayList<>();
        String callingActivity = getIntent().getStringExtra("from");
        if (callingActivity.equals("date")) {
            try {
                String startDateText = getIntent().getStringExtra("startDate");
                String endDateText = getIntent().getStringExtra("endDate");
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date startDate = dateFormat.parse(startDateText);
                Date endDate = dateFormat.parse(endDateText);

                Calendar calendar = new GregorianCalendar();
                calendar.setTime(startDate);
                int startMonth = calendar.get(Calendar.MONTH);
                int startYear = calendar.get(Calendar.YEAR);
                calendar.setTime(endDate);
                int endMonth = calendar.get(Calendar.MONTH);
                int endYear = calendar.get(Calendar.YEAR);

                //ArrayList<Double> months = new ArrayList<>();
                for (int i = 0; i < (endMonth - startMonth) + (endYear - startYear) * 12; i++) {
                    Double d = i + 0.0;
                    months.put(d, 0.0);
                }
                searchResults = ratz.sortByDate(startDate, endDate);
                for (int i = 0; i < searchResults.size(); i++) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");

                    Date checker = sdf.parse(searchResults.get(i).getDateTime());
                    calendar = new GregorianCalendar();

                    calendar.setTime(checker);
                    int month = calendar.get(Calendar.MONTH);
                    int year = calendar.get(Calendar.YEAR);
                    x = (month - startMonth) + (year - startYear) * 12;
                    months.put(x, months.get(x) + 1.0);
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }

            } else if (callingActivity.equals("viewAllMap")) {
            //should never be called as of M9
            for (int i = 0; i < 300; i++) {
                int j = (int) (Math.random() * 100000);
                RatSighting rat = rats.get(j);
                searchResults.add(rat);
                String id = rat.getUniqueKey();
                String latitude = rat.getLatitude();
                String longitude = rat.getLongitude();
                try {
                    Double lat = Double.parseDouble(latitude);
                    Double lng = Double.parseDouble(longitude);
//                    LatLng ratLocation = new LatLng(lat, lng);
//                    mMap.addMarker(new MarkerOptions().position(ratLocation).title("Marker of Rat " + id));
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ratLocation, 11));
                } catch(Exception e) {
                    System.out.println("One of the rats can't be displayed");
                }
            }
        }

        DataPoint[] dp = new DataPoint[months.size()];
        for (int i = 0; i < months.size(); i++) {
            dp[i] = new DataPoint(i + 0.0, months.get(i + 0.0));
//            series.appendData(dataPoint, false, months.size());
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dp);
        graph.addSeries(series);

        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Month");
        gridLabel.setVerticalAxisTitle("Number of Sightings");
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

    }
}
