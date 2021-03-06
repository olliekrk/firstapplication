package com.example.olgierd.kalendarz;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class NewExpenseActivity extends AppCompatActivity {

    ExpenseDB myDB;

    private static final String TAG = "NewExpenseActivity";
    private TextView mDate;
    private EditText mName;
    private EditText mAmount;
    private Button submitButton;
    private Spinner mCat;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;

    private int selectedDay;
    private int selectedMonth;
    private int selectedYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);

        myDB = new ExpenseDB(this);

        submitButton = (Button) findViewById(R.id.submitButton);
        mName = (EditText) findViewById(R.id.etName);
        mAmount = (EditText) findViewById(R.id.etAmount);
        mDate = (TextView) findViewById(R.id.etDate);
        mCat = (Spinner) findViewById(R.id.categorySpinner);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertToDB();
            }
        });

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                selectedYear = cal.get(Calendar.YEAR);
                selectedMonth = cal.get(Calendar.MONTH);
                selectedDay = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(NewExpenseActivity.this,
                        android.R.style.Theme_Black,
                        mOnDateSetListener,
                        selectedYear, selectedMonth, selectedDay);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                Log.d(TAG, "onDateSet: date: " + day + "." + month + "." + year);
                String date = day + "." + month + "." + year;
                mDate.setText(date);

                selectedDay = day;
                selectedMonth = month;
                selectedYear = year;
            }
        };
    }

    private void insertToDB() {
        String name = mName.getText().toString();
        String cat = mCat.getSelectedItem().toString();
        Double amount = Double.parseDouble(mAmount.toString());

        try {
            long id = myDB.insertData(name, cat, amount, selectedDay, selectedMonth, selectedYear);
            if (id <= 0)
                Message.message(getApplicationContext(), "Insertion was unsuccesful");
            else
                Message.message(getApplicationContext(), "Expense inserted succesfully");
            mDate.setText("Wybierz datę");
            mAmount.setText("Kwota");
            mName.setText("Nazwa");
        } catch (Exception e) {
            Message.message(getApplicationContext(), "" + e);
        }
    }
}
