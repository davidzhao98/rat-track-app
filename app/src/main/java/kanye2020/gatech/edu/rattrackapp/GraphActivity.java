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
            try {
                String startDateText = intent.getStringExtra("startDate");
                String endDateText = intent.getStringExtra("endDate");
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date startDate = dateFormat.parse(startDateText);
                Date endDate = dateFormat.parse(endDateText);
                months = getMonths(startDate, endDate);
                plotOnGraph(months, graph, startDate);

                /*Calendar calendar = new GregorianCalendar();
                calendar.setTime(startDate);
                startMonth = calendar.get(Calendar.MONTH);
                int startYear = calendar.get(Calendar.YEAR);
                calendar.setTime(endDate);
                endMonth = calendar.get(Calendar.MONTH);
                endYear = calendar.get(Calendar.YEAR);

                for (int i = startMonth; i <
                        ((endMonth) + ((endYear - startYear) * MONTHS_IN_YEAR)); i++) {
                    Double d = i + 0.0;
                    months.put(d, 0.0);
                }
                searchResults = ratList.sortByDate(startDate, endDate);
                for (int i = 0; i < searchResults.size(); i++) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                    RatSighting result = searchResults.get(i);
                    Date checker = sdf.parse(result.getDateTime());
                    calendar = new GregorianCalendar();

                    calendar.setTime(checker);
                    int month = calendar.get(Calendar.MONTH);
                    int year = calendar.get(Calendar.YEAR);
                    x = (month) + ((year - startYear) * MONTHS_IN_YEAR);
                    months.put(x, months.get(x) + 1.0);
                }*/
            } catch(Exception e) {
                Log.e("Exception", e.getMessage());
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
                    } catch (Exception e) {
                        Log.e("Exception", e.getMessage());
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
        Viewport viewport = graph.getViewport();
        viewport.setScalable(true);
        viewport.setScalableY(false);


    }

    /**
     *
     * @param startDate the start date for the query
     * @param endDate the end date for the query
     * @return a hash map containing the months from startDate to endDate
     * and the number of Rat Sightings in each month.
     */
    private HashMap<Double, Double> getMonths(Date startDate, Date endDate) {
        HashMap<Double, Double> months = new HashMap<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        int startMonth = calendar.get(Calendar.MONTH);
        int startYear = calendar.get(Calendar.YEAR);
        calendar.setTime(endDate);
        int endMonth = calendar.get(Calendar.MONTH);
        int endYear = calendar.get(Calendar.YEAR);

        for (int i = startMonth; i <
                ((endMonth) + ((endYear - startYear) * MONTHS_IN_YEAR)); i++) {
            Double d = i + 0.0;
            months.put(d, 0.0);
        }
        List<RatSighting> searchResults = ratList.sortByDate(startDate, endDate);
        for (int i = 0; i < searchResults.size(); i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            RatSighting result = searchResults.get(i);
            try {
                Date checker = sdf.parse(result.getDateTime());
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
