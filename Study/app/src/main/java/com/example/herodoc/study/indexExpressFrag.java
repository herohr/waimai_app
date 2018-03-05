package com.example.herodoc.study;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class indexExpressFrag extends Fragment {


    public indexExpressFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        actUtils.setTile(getActivity(), "快递");
        return inflater.inflate(R.layout.fragment_index_express, container, false);
    }

}
