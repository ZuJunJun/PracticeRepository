package com.example.jay.packageandroidproject.first;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jay.packageandroidproject.R;

import java.util.ArrayList;
import java.util.List;

public class GridViewPagerAdapter extends PagerAdapter {

    private Context mCtx;
    private List<String> mPagerList = new ArrayList<>();
    private HomeGridAdapter homeGridAdapter;
    private List<String> mGridList = new ArrayList<>();

    public GridViewPagerAdapter(Context mCtx, List<String> mPagerList, List<String> mGridList) {
        this.mCtx = mCtx;
        this.mPagerList = mPagerList;
        this.mGridList = mGridList;
    }

    @Override
    public int getCount() {
        return mPagerList == null ? 0 : mPagerList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_home_recycler_viewpager, container, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mCtx, 4);
        RecyclerView mGridView = view.findViewById(R.id.viewpager_recycler);
        mGridView.setLayoutManager(gridLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(mCtx, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(mCtx, R.drawable.home_recycler_divider));
        mGridView.addItemDecoration(decoration);
        homeGridAdapter = new HomeGridAdapter(mCtx, mGridList);
        mGridView.setAdapter(homeGridAdapter);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
