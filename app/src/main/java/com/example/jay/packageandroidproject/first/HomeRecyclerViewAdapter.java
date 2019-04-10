package com.example.jay.packageandroidproject.first;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.view.SpringIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int Type_Gallery = 2;
    private static final int Type_Grid = 3;
    private static final int Type_Url = 4;
    private Context mCtx;
    private List<Integer> lists = new ArrayList<>();
    private List<String> mGalleryList = new ArrayList<>();
    private List<String> mGridList = new ArrayList<>();
    private GalleryAdapter galleryAdapter;
    private List<String> mPagerList = new ArrayList<>();

    public HomeRecyclerViewAdapter(Context mCtx, List<Integer> lists, List<String> mGalleryList, List<String> mPagerList) {
        this.mCtx = mCtx;
        this.lists = lists;
        this.mGalleryList = mGalleryList;
        this.mPagerList = mPagerList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == Type_Gallery) {
            return new GalleryHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_home_recycler_gallery, viewGroup, false));
        } else if (viewType == Type_Grid) {
            return new GridHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_home_recycler_grid, viewGroup, false));
        } else {
            return new UrlHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_home_recycler_url, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof GalleryHolder) {
            setGalleryData((GalleryHolder) viewHolder);
        } else if (viewHolder instanceof GridHolder) {
            setGridData((GridHolder) viewHolder);
        } else {
            setUrlData((UrlHolder) viewHolder);
        }

    }

    private void setUrlData(UrlHolder holder) {
        holder.mUrlView.loadUrl("https://www.baidu.com");
    }

    private void setGridData(GridHolder holder) {
        GridViewPagerAdapter pagerAdapter = new GridViewPagerAdapter(mCtx, mPagerList, mGridList);
        holder.mPagerView.setAdapter(pagerAdapter);
        holder.indicator.setViewPager(holder.mPagerView);
    }

    private void setGalleryData(GalleryHolder holder) {
        galleryAdapter = new GalleryAdapter(mCtx, mGalleryList);
        holder.mGallery.setAdapter(galleryAdapter);
        holder.mGallery.setOffscreenPageLimit(mGalleryList.size());
    }

    @Override
    public int getItemViewType(int position) {
        Integer integer = lists.get(position);
        if (integer == 2) {
            return Type_Gallery;
        } else if (integer == 3) {
            return Type_Grid;
        } else {
            return Type_Url;
        }
    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }

    class GalleryHolder extends RecyclerView.ViewHolder {

        private final ViewPager mGallery;

        public GalleryHolder(@NonNull View itemView) {
            super(itemView);
            mGallery = itemView.findViewById(R.id.item_viewpager);
            mGallery.setPageTransformer(true, new CardTransformer());
        }
    }

    class GridHolder extends RecyclerView.ViewHolder {

        private final ViewPager mPagerView;
        private final SpringIndicator indicator;

        public GridHolder(@NonNull View itemView) {
            super(itemView);
            mPagerView = itemView.findViewById(R.id.home_grid_pager);
            indicator = itemView.findViewById(R.id.spring_indicator);
        }
    }

    class UrlHolder extends RecyclerView.ViewHolder {

        private final WebView mUrlView;

        public UrlHolder(@NonNull View itemView) {
            super(itemView);
            mUrlView = itemView.findViewById(R.id.item_webview);
        }
    }
}
