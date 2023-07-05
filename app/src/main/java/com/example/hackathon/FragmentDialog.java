package com.example.hackathon;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.ViewUtils;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDialog extends DialogFragment {
    private Fragment fragment;
    View btnClick;
    EditText edtDetail;

    private DataListener dataListener;

    public FragmentDialog() {

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_layout, container, false);

        Bundle args = getArguments();
        String value = args.getString("key");
        btnClick = view.findViewById(R.id.btn_click);
        btnClick.setOnClickListener(v -> sendReport(view));

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("tag");
        view.findViewById(R.id.btn_clear).setOnClickListener(v -> {
            DialogFragment dialogFragment= (DialogFragment) fragment;
            dialogFragment.dismiss();
        });
        return view;
    }

    private void sendReport(View view) {
        edtDetail = view.findViewById(R.id.dialog_detail_et);
        String content = edtDetail.getText().toString();


        ReportRequest reportRequest = new ReportRequest(
                "kt 인재 개발원",
                String.valueOf(ReportFragment.x),
                String.valueOf(ReportFragment.y),
                content
                );
        SeverApi severApi = ApiProvider.getInstance().create(SeverApi.class);

        severApi.report("Bearer "+LoginActivity.puToken,reportRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    DialogFragment dialogFragment= (DialogFragment) fragment;
                    dialogFragment.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("TAG","Bearer "+LoginActivity.puToken);
            }
        });
    }
}
