package com.droiddev.sdu.teacher.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.droiddev.sdu.R;
import com.droiddev.sdu.teacher.model.ModelActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ActivityShow extends Fragment {

    private static final String ARG_PARAM1 = "data";
    private String data;

    public ActivityShow() {

    }

    public static ActivityShow newInstance(String data) {
        ActivityShow fragment = new ActivityShow();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity_show, container, false);
        Gson gson = new Gson();
        ModelActivity m = gson.fromJson(data, ModelActivity.class);

        TextView txtSubjectCode = (TextView) view.findViewById(R.id.txtSubjectCode);
        TextView txtSubject = (TextView) view.findViewById(R.id.txtSubject);
        TextView txtFaculty = (TextView) view.findViewById(R.id.txtFaculty);
        TextView txtMajor = (TextView) view.findViewById(R.id.txtMajor);
        TextView txtStart = (TextView) view.findViewById(R.id.txtStart);
        TextView txtStop = (TextView) view.findViewById(R.id.txtStop);
        TextView txtDescription = (TextView) view.findViewById(R.id.txtDescription);
        TextView txtReport = (TextView) view.findViewById(R.id.txtReport);

        txtSubjectCode.setText(m.getSubjectCode());
        txtSubject.setText(m.getSubjectName());
        txtFaculty.setText(m.getFaculty());
        txtMajor.setText(m.getMajor());
        txtStart.setText(m.getDate()+" "+m.getStart());
        txtStop.setText(m.getDate()+" "+m.getStop());
        txtDescription.setText(m.getDescription());
        txtReport.setText(m.getReport());
        return view;
    }

}
