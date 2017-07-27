package com.example.dell.driverapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

    }
    void RegisterOnClick(View v)
    {
        Intent myIntent = new Intent(Activity1.this,MapsActivity.class);
        startActivity(myIntent);


    }
}
