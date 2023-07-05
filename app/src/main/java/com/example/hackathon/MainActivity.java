package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.example.hackathon.Adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DataListener{

    TabLayout tabs;
    AalramFragment aalramFragment;
    HomeFragment homeFragment;
    ReportFragment reportFragment;

    static Double latitude=6.0;

    static Double longitude=9.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ReportFragment reportFragment = new ReportFragment();
        //reportFragment.show(getSupportFragmentManager(),"dialog");

        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else {
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                String provider = location.getProvider();
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();

                ReportFragment reportFragment = new ReportFragment();
                reportFragment.setDataListener(this);
                reportFragment.updateData(latitude, longitude);
                Log.d("TEST","위도"+latitude);
            }
        }



        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.reportMain("Bearer "+LoginActivity.puToken).enqueue(new Callback<ReportMainResponse>() {
            @Override
            public void onResponse(Call<ReportMainResponse> call, Response<ReportMainResponse> response) {
                if (response.isSuccessful()) {
                    latitude = response.body().latitude;
                    longitude = response.body().longitude;
                }
            }

            @Override
            public void onFailure(Call<ReportMainResponse> call, Throwable t) {
            }
        });

        aalramFragment = new AalramFragment();
        homeFragment = new HomeFragment();
        reportFragment = new ReportFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container, aalramFragment).commit();

        tabs = findViewById(R.id.navigation);
        tabs.addTab(tabs.newTab().setText("알림함"));
        tabs.addTab(tabs.newTab().setText("홈"));
        tabs.addTab(tabs.newTab().setText("신고하기"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            public void onTabSelected(TabLayout.Tab tab){
                int position = tab.getPosition();
                Fragment selected = null;
                if(position ==0)
                    selected = aalramFragment;
                else if(position == 1)
                    selected = homeFragment;
                else
                    selected = reportFragment;
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        ViewPagerAdapter viewPager2Adapter
//                = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
//        ViewPager2 viewPager2 = findViewById(R.id.vp);
//        viewPager2.setAdapter(viewPager2Adapter);

        //TabLayout tabLayout = findViewById(R.id.navigation);
//        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
//            if (position == 0) tab.setText("알림함");
//            else if (position == 1) tab.setText("홈");
//            else tab.setText("신고하기");
//        }).attach();

//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) { // 선택 X -> 선택 O
//                if (tab.getPosition() ==0){ //탭레이아웃 포지션 얻기 0 이 Tab 1
//                    tab.setText("알림함");
//                    new AalramFragment();
//                }else if (tab.getPosition() == 1){
//                    tab.setText("홈");
//                    new HomeFragment();
//                }else {
//                    tab.setText("신고하기");
//                    new ReportFragment();
//                }
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) { // 선택 O -> 선택
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) { // 선택 O -> 선택 O
//
//            }
//        });
//
    }

    @Override
    public void onDataReceived(double latitude, double longitude) {

    }
}