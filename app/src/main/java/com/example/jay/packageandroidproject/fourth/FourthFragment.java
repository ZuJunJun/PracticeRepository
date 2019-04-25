package com.example.jay.packageandroidproject.fourth;


import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.base.Constant;
import com.example.jay.packageandroidproject.util.NotificationUtil;
import com.example.jay.packageandroidproject.util.VideoUtil;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.volokh.danylo.visibility_utils.calculator.DefaultSingleItemCalculatorCallback;
import com.volokh.danylo.visibility_utils.calculator.SingleListViewItemActiveCalculator;
import com.volokh.danylo.visibility_utils.scroll_utils.RecyclerViewItemPositionGetter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FourthFragment extends Fragment {


    private NotificationManager notificationManager;
    private RecyclerView mVideoRecyclerView;
    private VideoAdapter videoAdapter;
    private ScrollCalculatorHelper scrollCalculatorHelper;

    public static FourthFragment newInstance() {
        FourthFragment messageFragment = new FourthFragment();
        return messageFragment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            if (GSYVideoManager.instance().getCurPlayerManager() != null) {
                GSYVideoManager.instance().getCurPlayerManager().pause();
            }
        }else {
            if (GSYVideoManager.instance().getCurPlayerManager() != null) {
                GSYVideoManager.instance().getCurPlayerManager().start();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        sendNotification(view);
        mVideoRecyclerView = view.findViewById(R.id.video_recycler_view);
        mVideoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mVideoRecyclerView);
        videoAdapter = new VideoAdapter(getContext(), VideoUtil.getDatas());
        mVideoRecyclerView.setAdapter(videoAdapter);
        //限定范围为屏幕一半的上下偏移180
        int playTop = CommonUtil.getScreenHeight(getContext()) / 2 - CommonUtil.dip2px(getContext(), 180);
        int playBottom = CommonUtil.getScreenHeight(getContext()) / 2 + CommonUtil.dip2px(getContext(), 180);
        //自定播放帮助类
        scrollCalculatorHelper = new ScrollCalculatorHelper(R.id.item_video_view, playTop, playBottom);
        mVideoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

//            int firstVisibleItem, lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                scrollCalculatorHelper.onScrollStateChanged(recyclerView, newState);
                Log.e("zjj", "onScrollStateChanged: 222");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("zjj", "onScrollStateChanged: 333");
//                firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//                lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
//                //这是滑动自动播放的代码
//                scrollCalculatorHelper.onScroll(recyclerView, firstVisibleItem, lastVisibleItem, lastVisibleItem - firstVisibleItem);
            }
        });
    }

    private void sendNotification(View view) {
        notificationManager = ((NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE));
        view.findViewById(R.id.category_one).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                sendChatNotification();
            }
        });
        view.findViewById(R.id.category_three).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                sendDefaultNotification();
            }
        });
        view.findViewById(R.id.category_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLowerONotification();
            }
        });
    }

    private void sendLowerONotification() {
        NotificationUtil.sendNotification(getContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendChatNotification() {
        NotificationUtil.sendNotification(getContext(), Constant.CHAT_ID);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendDefaultNotification() {
        NotificationUtil.sendNotification(getContext(), Constant.DEFAULT_ID);
    }
}
