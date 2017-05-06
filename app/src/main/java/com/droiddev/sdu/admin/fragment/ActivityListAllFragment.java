package com.droiddev.sdu.admin.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.droiddev.sdu.R;
import com.droiddev.sdu.admin.QuizActivityAdmin;
import com.droiddev.sdu.admin.adapter.AdapterActivityList;
import com.droiddev.sdu.teacher.model.ModelActivityList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityListAllFragment extends Fragment {

    private static final String ARG_PARAM1 = "data";
    private String data;
    // TODO: Rename and change types of parameters

    private AdapterActivityList adapter;
    private ArrayList<ModelActivityList> temp;

    public ActivityListAllFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ActivityListAllFragment newInstance(String data) {
        ActivityListAllFragment fragment = new ActivityListAllFragment();
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
        View view = inflater.inflate(R.layout.dummy_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);


        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelActivityList>>() {}.getType();
        Collection<ModelActivityList> enums = gson.fromJson(data, collectionType);
        final ArrayList<ModelActivityList> list = new ArrayList<ModelActivityList>(enums);

        adapter = new AdapterActivityList(getActivity(), list);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new AdapterActivityList.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int ID = list.get(position).getId();
                startActivity(new Intent(getActivity(), QuizActivityAdmin.class).putExtra("id", ID));
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });


        return view;
    }

}
