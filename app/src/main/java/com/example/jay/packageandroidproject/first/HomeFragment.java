package com.example.jay.packageandroidproject.first;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.ding.DingBroadcastReceiver;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private HomeRecyclerViewAdapter adapter;
    private List<Integer> totalList = new ArrayList<>();
    private List<String> mGalleryList = new ArrayList<>();
    private List<String> mPagerList = new ArrayList<>();
    private Context mCtx;

    public static HomeFragment newInstance() {
        HomeFragment messageFragment = new HomeFragment();
        return messageFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mCtx = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        totalList.add(2);
        totalList.add(3);
        totalList.add(4);
        totalList.add(4);
        mGalleryList.add("http://img4.imgtn.bdimg.com/it/u=2378606792,1096904360&fm=26&gp=0.jpg");
        mGalleryList.add("http://pic.58pic.com/58pic/15/68/59/71X58PICNjx_1024.jpg");
        mGalleryList.add("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg");
        mGalleryList.add("http://img4.imgtn.bdimg.com/it/u=2378606792,1096904360&fm=26&gp=0.jpg");
        mGalleryList.add("http://pic.58pic.com/58pic/15/68/59/71X58PICNjx_1024.jpg");
        mGalleryList.add("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg");
        mPagerList.add("http://pic.58pic.com/58pic/15/68/59/71X58PICNjx_1024.jpg");
        mPagerList.add("http://pic15.nipic.com/20110628/1369025_192645024000_2.jpg");
        mRecyclerView = view.findViewById(R.id.home_recycler);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setColorSchemeColors(Color.BLACK, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mCtx));
        DividerItemDecoration decoration = new DividerItemDecoration(mCtx, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(mCtx, R.drawable.home_recycler_divider));
        mRecyclerView.addItemDecoration(decoration);
        adapter = new HomeRecyclerViewAdapter(mCtx, totalList, mGalleryList, mPagerList);
        mRecyclerView.setAdapter(adapter);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });
        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent("ANDROID_DING_MESSAGE");
                mCtx.sendBroadcast(in);
            }
        });
    }
}
