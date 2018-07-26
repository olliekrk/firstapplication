package com.example.olgierd.kalendarz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    private Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        goBackButton = (Button) findViewById(R.id.backButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        Date today = new Date();

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        CalendarPickerView datePicker = findViewById(R.id.calendar);
        datePicker.init(startDate.getTime(), endDate.getTime()).withSelectedDate(today);
        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {

                Calendar selectedDate = Calendar.getInstance();
                selectedDate.setTime(date);

                String selectedDateText = "" + selectedDate.get(Calendar.DAY_OF_MONTH)
                        + " - " + (selectedDate.get(Calendar.MONTH) + 1)
                        + " - " + selectedDate.get(Calendar.YEAR)
                        + "\n" + "Wydane: (tu wkleić wydatki)"
                        + "\n" + "Zyskane: (tu wkleić datki)"
                        + "\n" + "Bilans: (tu różnicę)";

                Toast.makeText(CalendarActivity.this, selectedDateText, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });


    }
}
