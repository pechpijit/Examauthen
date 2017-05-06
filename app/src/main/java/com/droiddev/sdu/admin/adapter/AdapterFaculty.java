package com.droiddev.sdu.admin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.droiddev.sdu.R;
import com.droiddev.sdu.admin.model.ModelFaculty;
import com.droiddev.sdu.teacher.model.ModelActivityList;

import java.util.ArrayList;


public class AdapterFaculty extends RecyclerView.Adapter<AdapterFaculty.VersionViewHolder> {

    private Context context;
    OnItemClickListener clickListener;
    private ArrayList<ModelFaculty> list;


    public AdapterFaculty(Context context, ArrayList<ModelFaculty> list) {
        this.context = context;
        this. list = list;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_faculty, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
            versionViewHolder.txtTitle.setText(list.get(i).getFacultyName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTitle;

        public VersionViewHolder(View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
