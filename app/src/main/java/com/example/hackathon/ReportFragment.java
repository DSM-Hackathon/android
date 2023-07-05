package com.example.hackathon;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;

import android.location.Location;
import android.location.LocationManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class ReportFragment extends Fragment implements OnMapReadyCallback,View.OnClickListener{

    private GoogleMap gmap;
    static double x = 0;
    static double y = 0;
    private DataListener dataListener;

    ImageButton reportBtn;
    EditText edtDetail;
    ConstraintSet.Layout dialogView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report, container, false);

        reportBtn = rootView.findViewById(R.id.report_btn);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.report_map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction()
                    .add(R.id.report_map, mapFragment)
                    .commit();
        }
        mapFragment.getMapAsync(this);

//        Bundle extra = getArguments();
//        Log.d("TEST", String.valueOf(getArguments()));
//        Log.d("TEST", "extra"+String.valueOf(extra));
//        if(extra != null){
//            x = extra.getDouble("위도",0);
//            y = extra.getDouble("경도",0);
//
//            Log.d("TEST","위도"+x);
//        }

        ImageButton open = (ImageButton) rootView.findViewById(R.id.report_btn);
        open.setOnClickListener(this);

        return rootView;
    }



    public void setDataListener(DataListener listener){
        this.dataListener = listener;
    }

    public void updateData(double latitude, double longitude) {
        Log.d("TEST","프위도"+latitude);
        Log.d("TEST", String.valueOf(dataListener));
        if(dataListener != null){
            dataListener.onDataReceived(latitude, longitude);
            Log.d("TEST","인 위도"+latitude);
            x = latitude;
            y = longitude;
            Log.d("TEST","xx"+x);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gmap = googleMap;



        LatLng SEOUL = new LatLng(x, y);
        Log.d("TEST","x"+x);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국 수도");

        gmap.addMarker(markerOptions);

        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 17));
    }


    @Override
    public void onClick(View v) {

        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("key","value");
                FragmentDialog dialog = new FragmentDialog();
                dialog.setArguments(args);
                Log.d("TEST", String.valueOf(args));

                dialog.show(getActivity().getSupportFragmentManager(),"tag");
            }
        });
    }
}