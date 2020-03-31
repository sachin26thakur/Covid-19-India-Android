package com.example.covid_19india.ui.districts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.covid_19india.R;
import com.example.covid_19india.ui.home.model.CovidDataModel;
import com.example.covid_19india.network.model.DistrictData;
import com.example.covid_19india.ui.districts.ui.destrict.DestrictFragment;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Destrict extends AppCompatActivity {

    JsonObject districts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.destrict_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DestrictFragment.newInstance())
                    .commitNow();
        }

    }
}
