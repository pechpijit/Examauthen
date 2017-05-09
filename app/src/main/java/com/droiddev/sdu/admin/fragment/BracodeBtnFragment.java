package com.droiddev.sdu.admin.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.droiddev.sdu.R;
import com.droiddev.sdu.admin.Camera_QRscan;
import com.droiddev.sdu.teacher.HomeActivity;
import com.droiddev.sdu.teacher.model.ModelActivity;
import com.google.gson.Gson;

public class BracodeBtnFragment extends Fragment {

    private static final String ARG_PARAM1 = "data";
    private int data;

    public BracodeBtnFragment() {

    }

    public static BracodeBtnFragment newInstance(int data) {
        BracodeBtnFragment fragment = new BracodeBtnFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bar, container, false);
        view.findViewById(R.id.btn_sacn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getActivity().getSharedPreferences("Preferences_SDU", Context.MODE_PRIVATE);
                int admin = sp.getInt("id", 0);
                Toast.makeText(getActivity(), ""+admin, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), Camera_QRscan.class).putExtra("id",data).putExtra("admin",admin));
            }
        });
        return view;
    }

}
