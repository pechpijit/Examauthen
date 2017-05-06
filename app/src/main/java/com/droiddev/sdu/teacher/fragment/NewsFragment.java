package com.droiddev.sdu.teacher.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.droiddev.sdu.R;
import com.droiddev.sdu.teacher.NewsActivity;
import com.droiddev.sdu.teacher.QuizActivity;
import com.droiddev.sdu.teacher.adapter.AdapterActivityList;
import com.droiddev.sdu.teacher.adapter.AdapterNews;
import com.droiddev.sdu.teacher.model.ModelActivityList;
import com.droiddev.sdu.teacher.model.ModelNews;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;


public class NewsFragment extends Fragment {
    private static final String ARG_PARAM1 = "data";
    private String data;
    // TODO: Rename and change types of parameters

    private AdapterNews adapter;
    private ArrayList<ModelNews> temp;

    public NewsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String data) {
        NewsFragment fragment = new NewsFragment();
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

        SharedPreferences sp = getActivity().getSharedPreferences("Preferences_SDU", Context.MODE_PRIVATE);
        String url = sp.getString("url", "");

        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelNews>>() {}.getType();
        Collection<ModelNews> enums = gson.fromJson(data, collectionType);
        final ArrayList<ModelNews> list = new ArrayList<ModelNews>(enums);

        adapter = new AdapterNews(getActivity(), list,url);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new AdapterNews.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int ID = list.get(position).getId();
                startActivity(new Intent(getActivity(), NewsActivity.class).putExtra("id", ID));
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });


        return view;
    }

}
