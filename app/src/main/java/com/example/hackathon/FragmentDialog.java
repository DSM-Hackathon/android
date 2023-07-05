package com.example.hackathon;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.ViewUtils;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class FragmentDialog extends DialogFragment {
    private Fragment fragment;
    public FragmentDialog(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_layout, container, false);

        Bundle args = getArguments();
        String value = args.getString("key");

        //dialog.show(getActivity().getSupportFragmentManager(),"tag");
        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("tag");
        if(fragment!= null){
            DialogFragment dialogFragment= (DialogFragment) fragment;
            dialogFragment.dismiss();
        }
        return view;
    }
}
