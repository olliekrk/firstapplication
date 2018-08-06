package com.example.olgierd.kalendarz;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button calendarButton, newExpenseButton;
    EditText date;                                      //dodawanie wydatku
    DatePickerDialog datePickerDialog;

    ExpenseDB myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarButton = (Button) findViewById(R.id.launchCalendarButton);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCalendarActivity();
            }
        });
        newExpenseButton = (Button) findViewById(R.id.buttonDodajWydatek);
        newExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { launchNewExpenseDialogActivity();
            }
        });
        myDB = new ExpenseDB(this);


//        Button mDodajWydatek = (Button) findViewById(R.id.buttonDodajWydatek);                      //button dodaj wydatek
//        mDodajWydatek.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
//                View mView = getLayoutInflater().inflate(R.layout.activity_add_expence, null);        //ważne, nie do konca wiem dlaczego
//                EditText mNazwa = (EditText) mView.findViewById(R.id.etName);
//                EditText mData = (EditText) mView.findViewById(R.id.etDate);
//                EditText mKwota = (EditText) mView.findViewById(R.id.etAmount);
//
//                mBuilder.setView(mView);                                                             //tez wazne
//                AlertDialog dialog = mBuilder.create();
//                dialog.show();
//
//            }
//        });


    }


    private void launchCalendarActivity() {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    private void launchNewExpenseDialogActivity() {
        Intent intent = new Intent(this, NewExpenseDialogActivity.class);
        startActivity(intent);
    }


}
