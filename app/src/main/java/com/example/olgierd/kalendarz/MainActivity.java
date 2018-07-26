package com.example.olgierd.kalendarz;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button calendarButton;
    EditText date;                                      //dodawanie wydatku
    DatePickerDialog datePickerDialog;



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

        Button mDodajWydatek = (Button) findViewById(R.id.buttonDodajWydatek);                      //button dodaj wydatek
        mDodajWydatek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_add_expence,null );        //ważne, nie do konca wiem dlaczego
                EditText mNazwa = (EditText) mView.findViewById(R.id.etName);
                EditText mData = (EditText) mView.findViewById(R.id.etDate);
                EditText mKwota = (EditText) mView.findViewById(R.id.etAmount);
                Button mRodzaj = (Button) mView.findViewById(R.id.buttonTypeOfExpenses);

                mBuilder.setView(mView);                                                             //tez wazne
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

    private void launchCalendarActivity() {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }



    public void type(View v){
        android.support.v7.widget.PopupMenu popup = new android.support.v7.widget.PopupMenu(this,v);  //metoda wybierajaca rodzaj wydatku
        popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
        popup.inflate(R.menu.popup_type_add_expences);
        popup.show();
    }


    public boolean onMenuItemClick(MenuItem item){                                                            //rozne przypadki wydatkow, wybieranie
        switch(item.getItemId()){
            case R.id.rodzaj1:
                Toast.makeText(this, "Rodzaj 1",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.rodzaj2:
                Toast.makeText(this, "Rodzaj 2",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.rodzaj3:
                Toast.makeText(this, "Rodzaj 3",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.rodzaj4:
                Toast.makeText(this, "Rodzaj 4",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.rodzaj5:
                Toast.makeText(this, "Rodzaj 5",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }



}
