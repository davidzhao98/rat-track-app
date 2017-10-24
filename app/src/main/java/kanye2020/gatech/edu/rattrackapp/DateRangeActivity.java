package kanye2020.gatech.edu.rattrackapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Pulak Azad on 10/24/17.
 */
public class DateRangeActivity extends AppCompatActivity {


    //intializes buttons and textViews
    Button btn;
    Button btn2;
    Button btn3;

    TextView startDateView;
    TextView endDateView;

    int monthStart, dayStart, yearStart;
    int monthEnd, dayEnd, yearEnd;

    static final int DIALOG_ID = 0;
    static final int DIALOG_ID2 = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_range);

        final Calendar cal = Calendar.getInstance();
        yearStart = cal.get(Calendar.YEAR);
        monthStart = cal.get(Calendar.MONTH);
        dayStart = cal.get(Calendar.DAY_OF_MONTH);

        yearEnd = cal.get(Calendar.YEAR);
        monthEnd = cal.get(Calendar.MONTH);
        dayEnd = cal.get(Calendar.DAY_OF_MONTH) + 1;

        startDateView = (TextView) findViewById(R.id.startDateTV);
        endDateView = (TextView) findViewById(R.id.endDateTV);

        showDialogOnButtonClick();
    }

    /**
     * shows what happens when you click on each button
     */
    public void showDialogOnButtonClick() {
        btn = (Button) findViewById(R.id.startDateButton);
        btn2 = (Button) findViewById(R.id.endDateButton);
        btn3 = (Button) findViewById(R.id.viewMapRange);


        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(DIALOG_ID);
                    }
                }
        );

        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(DIALOG_ID2);
                    }
                }
        );

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * date picker for startDate
     */
    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            yearStart = i;
            monthStart = i1 + 1;
            dayStart = i2;

            startDateView.setText(monthStart + "/" + dayStart + "/" + yearStart);

            Toast.makeText(DateRangeActivity.this, monthStart + "/" + dayStart + "/" + yearStart, Toast.LENGTH_LONG).show();
        }
    };

    /**
     * date picker for endDate
     */
    private DatePickerDialog.OnDateSetListener dpickerListener2
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            yearEnd = i;
            monthEnd = i1 + 1;
            dayEnd = i2;

            endDateView.setText(monthEnd + "/" + dayEnd + "/" + yearEnd);

            Toast.makeText(DateRangeActivity.this, monthEnd + "/" + dayEnd + "/" + yearEnd, Toast.LENGTH_LONG).show();
        }
    };


    /**
     * Creates a Dialog and determines which date Picker to use
     * @param id the id for either start date or end date
     * @return the dialog of the specified id
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerListener, yearStart, monthStart, dayStart);
        }
        if (id == DIALOG_ID2) {
            return new DatePickerDialog(this, dpickerListener2, yearEnd, monthEnd, dayEnd);

        }
        return null;
    }


}
