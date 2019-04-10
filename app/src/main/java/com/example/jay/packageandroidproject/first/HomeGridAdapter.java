package com.example.jay.packageandroidproject.first;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jay.packageandroidproject.R;

import java.util.ArrayList;
import java.util.List;

class HomeGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mCtx;
    private List<String> mGridList = new ArrayList<>();

    public HomeGridAdapter(Context mCtx, List<String> mGridList) {
        this.mCtx = mCtx;
        this.mGridList = mGridList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GridHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_home_grid, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class GridHolder extends RecyclerView.ViewHolder {
        public GridHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
