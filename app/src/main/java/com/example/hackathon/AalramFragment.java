package com.example.hackathon;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AalramFragment extends Fragment {

    private CustomAdapter adapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AalramFragment() {
    }

    public static AalramFragment newInstance(String param1, String param2) {
        AalramFragment fragment = new AalramFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.reportAll("Bearer "+LoginActivity.puToken).enqueue(new Callback<ReportAllResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<ReportAllResponse> call, Response<ReportAllResponse> response) {
                if (response.isSuccessful()){
                    List <ReportResponseList> listName = response.body().reportResponseList;
                    RecyclerView recyclerView = container.findViewById(R.id.recyclerview);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);

                    adapter = new CustomAdapter();
                    recyclerView.setAdapter(adapter);
                    Log.d("success",String.valueOf(response.body().reportResponseList.stream().count()));
                    for (int i = 0; i<response.body().reportResponseList.stream().count(); i++) {
                        adapter.addItem(response.body().reportResponseList.get(i));
                    }
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ReportAllResponse> call, Throwable t) {
                Log.d("success",t.getMessage());
            }
        });



        return inflater.inflate(R.layout.fragment_alram, container, false);
    }
}