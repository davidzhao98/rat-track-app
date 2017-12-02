package kanye2020.gatech.edu.rattrackapp;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Handles the Display of Data on a Graph
 * Created by Nick B on 10/29/2017.
 */

public class GraphActivity extends AppCompatActivity {

    private final RatSightingList ratList = RatSightingList.getInstance();
    private static final int MONTHS_IN_YEAR = 12;
    private static final int LABEL_ANGLE = 45;
    String title = "Graph";
    private static final int MINIMUM_X = 0;
    private static final int MINIMUM_Y = 0;
    int xRange = 100;
    int yRange = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        GraphView graph = (GraphView) findViewById(R.id.graphView);
        HashMap<Double, Double> months;
        //int startMonth = 0;
        Intent intent = getIntent();
        String callingActivity = intent.getStringExtra("from");
        if ("date".equals(callingActivity)) {
            title = "All Rat Sightings Sorted by Date";
            try {
                String startDateText = intent.getStringExtra("startDate");
                String endDateText = intent.getStringExtra("endDate");
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date startDate = dateFormat.parse(startDateText);
                Date endDate = dateFormat.parse(endDateText);
                List<RatSighting> searchResults = RatSightingList.sortByDate(startDate, endDate);
                months = getMonths(startDate, endDate, searchResults);
                plotOnGraph(months, graph, startDate);
            } catch(Exception e) {
                Log.e("Exception", e.getMessage());
            }

        } else if("borough".equals(callingActivity)) {
            String borough = intent.getStringExtra("borough");
            title = "Rat Sightings in " +borough;
            List<RatSighting> searchResults = RatSightingList.sortByBorough(borough);
            RatSightingList.sortByDate(searchResults);
            RatSighting first = searchResults.get(0);
            RatSighting last = searchResults.get(searchResults.size() - 1);
            String startDateTime = first.getDateTime();
            String endDateTime = last.getDateTime();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String startDateText = startDateTime.substring(0, "MM/dd/yyyy".length() + 1);
                intent.putExtra("startDate", startDateText);
                Date startDate = dateFormat.parse(startDateText);
                String endDateText = endDateTime.substring(0, "MM/dd/yyyy".length() + 1);
                Date endDate = dateFormat.parse(endDateText);
                months = getMonths(startDate, endDate, searchResults);
                plotOnGraph(months, graph, startDate);
            }catch (ParseException e) {
                Log.e("uhhh", e.getMessage());
            }

        } else if ("locationType".equals(callingActivity)) {
            String location = intent.getStringExtra("locationType");
            title = "Rat Sightings in " +location;
            List<RatSighting> searchResults = RatSightingList.sortByLocationType(location);
            RatSightingList.sortByDate(searchResults);
            RatSighting first = searchResults.get(0);
            RatSighting last = searchResults.get(searchResults.size() - 1);
            String startDateTime = first.getDateTime();
            String endDateTime = last.getDateTime();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String startDateText = startDateTime.substring(0, "MM/dd/yyyy".length() + 1);
                intent.putExtra("startDate", startDateText);
                Date startDate = dateFormat.parse(startDateText);
                String endDateText = endDateTime.substring(0, "MM/dd/yyyy".length() + 1);
                Date endDate = dateFormat.parse(endDateText);
                months = getMonths(startDate, endDate, searchResults);
                plotOnGraph(months, graph, startDate);
            }catch (ParseException e) {
                Log.e("uhhh", e.getMessage());
            }
        } else if ("all".equals(callingActivity)) {
            //String location = intent.getStringExtra("locationType");
            title = "All Rat Sightings";
            List<RatSighting> searchResults = RatSightingList.getSample();
            RatSightingList.sortByDate(searchResults);
            RatSighting first = searchResults.get(0);
            RatSighting last = searchResults.get(searchResults.size() - 1);
            String startDateTime = first.getDateTime();
            String endDateTime = last.getDateTime();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String startDateText = startDateTime.substring(0, "MM/dd/yyyy".length() + 1);
                intent.putExtra("startDate", startDateText);
                Date startDate = dateFormat.parse(startDateText);
                String endDateText = endDateTime.substring(0, "MM/dd/yyyy".length() + 1);
                Date endDate = dateFormat.parse(endDateText);
                months = getMonths(startDate, endDate, searchResults);
                plotOnGraph(months, graph, startDate);
            }catch (ParseException e) {
                Log.e("uhhh", e.getMessage());
            }
        }






        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    Intent intent = getIntent();
                    String startDateText = intent.getStringExtra("startDate");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    Date startDate = null;
                    try {
                        startDate = dateFormat.parse(startDateText);
                    } catch (ParseException e) {
                        Log.e("Parse Exception", e.getMessage());
                    }
                    if (startDate == null) {
                        Log.wtf("Can i get uhhhhhhhhhh", "BONelesS");
                    }
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(startDate);
                    calendar.set(Calendar.MONTH, (int)value);
                    String month = calendar.getDisplayName(
                            Calendar.MONTH, Calendar.SHORT, Locale.US);
                    int year = calendar.get(Calendar.YEAR);
                    return month + "/" + year;
                } else {
                    // show currency for y values
                    return super.formatLabel(value, false);
                }
            }
        });
        gridLabel.setHorizontalAxisTitle("Month/Year");
        gridLabel.setVerticalAxisTitle("Number of Sightings");
        gridLabel.setHorizontalLabelsAngle(LABEL_ANGLE);
        gridLabel.setHumanRounding(false);
        graph.setTitle(title);
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(MINIMUM_Y);
        viewport.setMaxY(10);
        viewport.setXAxisBoundsManual(true);
        viewport.setMinX(MINIMUM_X);
        viewport.setMaxX(xRange);
        viewport.setScrollable(true);
        viewport.setScrollableY(true);
        //viewport.setScalable(true);
        //viewport.setScalableY(true);


    }

    /**
     *
     * @param startDate the start date for the query
     * @param endDate the end date for the query
     * @return a hash map containing the months from startDate to endDate
     * and the number of Rat Sightings in each month.
     */
    private HashMap<Double, Double> getMonths(Date startDate, Date endDate, List<RatSighting> list) {
        HashMap<Double, Double> months = new HashMap<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        int startMonth = calendar.get(Calendar.MONTH);
        int startYear = calendar.get(Calendar.YEAR);
        calendar.setTime(endDate);
        int endMonth = calendar.get(Calendar.MONTH);
        int endYear = calendar.get(Calendar.YEAR);

        for (int i = startMonth; i <=
                ((endMonth) + ((endYear - startYear) * MONTHS_IN_YEAR)); i++) {
            Double d = i + 0.0;
            months.put(d, 0.0);
        }
        xRange = months.size();
        //List<RatSighting> searchResults = ratList.sortByDate(startDate, endDate);
        for (int i = 0; i < list.size(); i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            RatSighting result = list.get(i);
            try {
                String dateTime = result.getDateTime();
                Date checker = sdf.parse(dateTime);
                calendar.setTime(checker);
            } catch (ParseException e) {
                Log.e("ParseException", e.getMessage());
            }
            //calendar = new GregorianCalendar();
            double x;
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            x = (month) + ((year - startYear) * MONTHS_IN_YEAR);
            months.put(x, months.get(x) + 1.0);
        }
        return months;
    }
    private void plotOnGraph(HashMap<Double, Double> data, GraphView graph, Date startDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        int startMonth = calendar.get(Calendar.MONTH);
        DataPoint[] dp = new DataPoint[data.size()];
        for (int i = 0; i < data.size(); i++) {
            dp[i] = new DataPoint(i + startMonth + 0.0, data.get(i + startMonth + 0.0));
        }
        Series<DataPoint> series = new LineGraphSeries<>(dp);
        graph.addSeries(series);
    }
}
