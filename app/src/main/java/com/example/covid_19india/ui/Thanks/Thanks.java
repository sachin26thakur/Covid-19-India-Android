package com.example.covid_19india.ui.Thanks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.covid_19india.R;
import com.example.covid_19india.ui.Thanks.ui.thanks.ThanksFragment;

public class Thanks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanks_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ThanksFragment.newInstance())
                    .commitNow();
        }
    }
}
