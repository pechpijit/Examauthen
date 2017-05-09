package com.droiddev.sdu.teacher.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.droiddev.sdu.R;
import com.droiddev.sdu.teacher.ProfileActivity;
import com.droiddev.sdu.teacher.ProfileEmActivity;
import com.droiddev.sdu.teacher.adapter.AdapterCommittee;
import com.droiddev.sdu.teacher.adapter.AdapterEmployee;
import com.droiddev.sdu.teacher.model.ModelActivity;
import com.droiddev.sdu.teacher.model.ModelEmployee;
import com.droiddev.sdu.teacher.model.ModelProfile;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeListFragment extends Fragment {

    AdapterEmployee adapter;
    ArrayList<ModelEmployee> temp;
    private static final String ARG_PARAM1 = "data";
    private static final String ARG_PARAM2 = "url";
    private String data;
    private String URL;

    // TODO: Rename and change types of parameters

    public EmployeeListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EmployeeListFragment newInstance(String data, String url) {
        EmployeeListFragment fragment = new EmployeeListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, data);
        args.putString(ARG_PARAM2, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getString(ARG_PARAM1);
            URL = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dummy_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        Gson gson = new Gson();
        ModelActivity modelActivity = gson.fromJson(data, ModelActivity.class);
        final ModelEmployee employee = modelActivity.getEmployee();

        adapter = new AdapterEmployee(getActivity(), employee,URL);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new AdapterEmployee.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int id = employee.getId();
                getActivity().startActivity(new Intent(getActivity(), ProfileEmActivity.class).putExtra("id", id));
            }
        });
        return view;
    }

}
