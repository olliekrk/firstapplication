package com.example.olgierd.kalendarz;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class NewExpenseActivity extends AppCompatActivity {

    private static final String TAG = "NewExpenseActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);

        EditText mNazwa = (EditText) findViewById(R.id.etName);
       // EditText mData = (EditText) findViewById(R.id.etDate);
        EditText mKwota = (EditText) findViewById(R.id.etAmount);

        mDisplayDate = (TextView) findViewById(R.id.etDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(NewExpenseActivity.this,
                        android.R.style.Theme_Black, mOnDateSetListener, year, month, day );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                Log.d(TAG, "onDateSet: date: " + day + "/" + month + "/" + year);

                // zebrac dane do bazy

                String date = day + "." + month +"." + year;
                mDisplayDate.setText(date);

            }
        };

    }
}
