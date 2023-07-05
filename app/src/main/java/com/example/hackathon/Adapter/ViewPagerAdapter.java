package com.example.hackathon.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hackathon.AalramFragment;
import com.example.hackathon.HomeFragment;
import com.example.hackathon.ReportFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AalramFragment();
            case 1:
                return new HomeFragment();
            case 2:
                return new ReportFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;       // 페이지 수
    }
}