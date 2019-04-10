package com.example.jay.packageandroidproject.first;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jay.packageandroidproject.R;

import java.util.ArrayList;
import java.util.List;

class GalleryAdapter extends PagerAdapter {
    private Context mCtx;
    private List<String> mGalleryList = new ArrayList<>();

    public GalleryAdapter(Context mCtx,List<String> mGalleryList) {
        this.mCtx = mCtx;
        this.mGalleryList = mGalleryList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        position %= mGalleryList.size();
        if (position<0) {
            position = mGalleryList.size() + position;
        }
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_home_gallery, null);
        ImageView itemIv = view.findViewById(R.id.gallery_item);
        Glide.with(mCtx).load(mGalleryList.get(position)).into(itemIv);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
