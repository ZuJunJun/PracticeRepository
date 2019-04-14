package com.example.jay.packageandroidproject.main;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.example.jay.packageandroidproject.R;
import com.example.jay.packageandroidproject.base.Constant;
import com.example.jay.packageandroidproject.base.XActivity;
import com.example.jay.packageandroidproject.first.HomeFragment;
import com.example.jay.packageandroidproject.fourth.FourthFragment;
import com.example.jay.packageandroidproject.second.SecondFragment;
import com.example.jay.packageandroidproject.third.ThirdFragment;
import com.example.jay.packageandroidproject.util.NotificationUtil;
import com.example.jay.packageandroidproject.util.StatusBarUtil;

public class MainActivity extends XActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar mBotNavigationBar;
    private TextBadgeItem badgeItem;
    private Fragment currentFragment = new Fragment();
    private Fragment[] fragments = new Fragment[]{HomeFragment.newInstance()
            , SecondFragment.newInstance(), ThirdFragment.newInstance(), FourthFragment.newInstance()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideActionBar();
        NotificationUtil.createNotificationCategory(this);
        init();
    }

    private void init() {
        badgeItem = new TextBadgeItem().setBackgroundColorResource(R.color.colorPrimaryDark)
                .setTextColorResource(R.color.colorWhite).setText("5");
        mBotNavigationBar = findViewById(R.id.bot_nav_bar);
        mBotNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        mBotNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        mBotNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home_normal, "首页").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.sinace_normal, "科学").setActiveColorResource(R.color.colorBlue))
                .addItem(new BottomNavigationItem(R.mipmap.list_normal, "资讯").setActiveColorResource(R.color.colorYellow).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.setting_normal, "设置").setActiveColorResource(R.color.colorRed))
                .setFirstSelectedPosition(0)
                .initialise();
        mBotNavigationBar.setTabSelectedListener(this);
        loadFragment(fragments[0]);
    }

    private void createNotificationChannel() {

    }

    @Override
    public void onTabSelected(int position) {
        loadFragment(fragments[position]);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    public void loadFragment(Fragment toFragment) {
        if (currentFragment != toFragment) {
            setStatusBarColor(toFragment);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // 先判断是否被add过
            if (!toFragment.isAdded()) {
                // 隐藏当前的fragment，add下一个到Activity中
                transaction.hide(currentFragment).add(R.id.container, toFragment, toFragment.getClass().getName());
            } else {
                transaction.hide(currentFragment).show(toFragment); // 隐藏当前的fragment，显示下一个
            }
            currentFragment = toFragment;
            transaction.commitAllowingStateLoss();
        }
    }

    private void setStatusBarColor(Fragment toFragment) {
        if (toFragment instanceof HomeFragment) {
            StatusBarUtil.setStatusBarColor(this,ContextCompat.getColor(this,R.color.colorAccentDark));
        }else if (toFragment instanceof SecondFragment) {
            StatusBarUtil.setStatusBarColor(this,ContextCompat.getColor(this,R.color.colorBlueDark));
        }else if (toFragment instanceof ThirdFragment) {
            StatusBarUtil.setStatusBarColor(this,ContextCompat.getColor(this,R.color.colorYellowDark));
        }else if (toFragment instanceof FourthFragment) {
            StatusBarUtil.setStatusBarColor(this,ContextCompat.getColor(this,R.color.colorRedDark));
        }
    }

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
