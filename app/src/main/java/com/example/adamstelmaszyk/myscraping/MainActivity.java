package com.example.adamstelmaszyk.myscraping;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {


    Button button;
    Spinner spinnerCity, spinnerJob, spinnerTime;

    String[] splitedArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.btn);
        spinnerCity = (Spinner) findViewById(R.id.MA_spinner_City);
        spinnerJob = (Spinner) findViewById(R.id.MA_spinner_Job);
        spinnerTime = (Spinner) findViewById(R.id.MA_spinner_Time);

        spinners();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                intent.putExtra("City_job", spinnerCity.getSelectedItem().toString());
                intent.putExtra("Name_job", spinnerJob.getSelectedItem().toString());
                intent.putExtra("Time_job", spinnerTime.getSelectedItem().toString());
                startActivity(intent);
            }
        });

    }



    public void spinners()
    {
        spinnerCity.setAdapter(arraySpinner(R.array.cities));
        spinnerJob.setAdapter(arraySpinner(R.array.jobs));
        spinnerTime.setAdapter(arraySpinner(R.array.time_job));

    }

    public ArrayAdapter<String> arraySpinner(int name_array)
    {
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(name_array));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return myAdapter;
    }


    /*@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        String text2 = parent.getTransitionName();
        Toast.makeText(parent.getContext(), text2, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/
}
