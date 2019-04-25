package com.example.jay.packageandroidproject.bean;

import android.view.View;

import com.volokh.danylo.visibility_utils.items.ListItem;

public class VideoBean implements ListItem {
    public String mUrl;

    public VideoBean(String mUrl) {
        this.mUrl = mUrl;
    }

    @Override
    public int getVisibilityPercents(View view) {
        return 0;
    }

    @Override
    public void setActive(View newActiveView, int newActiveViewPosition) {

    }

    @Override
    public void deactivate(View currentView, int position) {

    }
}
