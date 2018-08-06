package com.example.olgierd.kalendarz;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button calendarButton, newExpenseButton;

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
