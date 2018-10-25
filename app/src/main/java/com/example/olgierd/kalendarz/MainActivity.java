package com.example.olgierd.kalendarz;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    //private Button  newExpenseButton;
    private ImageButton calendarImageButton, newExpenseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        calendarImageButton = (ImageButton) findViewById(R.id.launchCalendarButton);
        calendarImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCalendarActivity();
            }
        });
        newExpenseButton = (ImageButton) findViewById(R.id.buttonDodajWydatek);
        newExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { launchNewExpenseDialogActivity();
            }
        });
    }


    private void launchCalendarActivity() {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    private void launchNewExpenseDialogActivity() {
        Intent intent = new Intent(this, NewExpenseActivity.class);
        startActivity(intent);
    }


}
