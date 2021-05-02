package com.android.locationtracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

/**
 * Created by Personal on 01/10/2018.
 */

public class BaseActivity extends AppCompatActivity {


    public DataManager dataManager;
    public DisplayMetrics metrices;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataManager = new DataManager(this);


        metrices = getResources().getDisplayMetrics();
    }
}

