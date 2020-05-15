package com.example.testsgwd;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.TimerTask;

public class HomeFragment extends Fragment {

    ViewPager viewPager;
    private  static final long SLIDER_TIMER = 800;
    private int currentPage =0;
    private boolean isCountDownTimerActive = false;

    Handler handler;

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(!isCountDownTimerActive){
                automateSlider();
            }
            handler.postDelayed(runnable,1000);
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = (ViewPager)v.findViewById(R.id.viewPager);
        handler = new Handler();
        handler.postDelayed(runnable,1000);
        runnable.run();

        ViewPagerAdapter viewPagerAdapter;
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    currentPage = 0;
                } else if (position == 1) {
                    currentPage = 1;
                } else if (position == 2) {
                    currentPage = 2;
                } else {
                    currentPage = 3;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return v;
    }
    private void automateSlider() {
        isCountDownTimerActive = true;
        new CountDownTimer(SLIDER_TIMER, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                int nextSlider = currentPage + 1;
                if (nextSlider == 4) {
                    nextSlider = 0; // if it's last Image, let it go to the first image
                }

                viewPager.setCurrentItem(nextSlider);
                isCountDownTimerActive = false;
            }
        }.start();
    }

    @Override
    public void onStop() {
        super.onStop();

        handler.removeCallbacks(runnable);
    }
}
