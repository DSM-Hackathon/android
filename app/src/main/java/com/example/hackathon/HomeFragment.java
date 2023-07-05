package com.example.hackathon;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double latitude=6.0;
    Double longitude=9.0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction()
                    .add(R.id.mapView, mapFragment)
                    .commit();
        }

        mapFragment.getMapAsync(this);
        reportMainResponse(rootView);
        return rootView;
    }

    private void reportMainResponse(View rootView) {
        TextView report_user_name = (TextView) rootView.findViewById(R.id.home_user_name);
        TextView report_time = (TextView) rootView.findViewById(R.id.home_report_time);
        TextView report_place = (TextView) rootView.findViewById(R.id.home_report_place);
        TextView report_description = (TextView) rootView.findViewById(R.id.content);

        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.reportMain("Bearer "+LoginActivity.puToken).enqueue(new Callback<ReportMainResponse>() {
            @Override
            public void onResponse(Call<ReportMainResponse> call, Response<ReportMainResponse> response) {
                if (response.isSuccessful()) {
                    report_user_name.setText(response.body().reporter);
                    report_time.setText(response.body().reportedAt.substring(0,10));
                    report_place.setText(response.body().place);
                    report_description.setText(response.body().description);
                    latitude = response.body().latitude;
                    longitude = response.body().longitude;
                }
            }

            @Override
            public void onFailure(Call<ReportMainResponse> call, Throwable t) {
            }
        });
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng SEOUL = new LatLng(MainActivity.latitude, MainActivity.longitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("대전");
        markerOptions.snippet("충청도 수도");

        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));
    }
}
