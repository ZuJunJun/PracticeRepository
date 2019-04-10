package com.example.jay.packageandroidproject.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class XActivity extends AppCompatActivity {

    public void hideActionBar() {
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
