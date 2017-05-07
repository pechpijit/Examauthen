package com.droiddev.sdu.admin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.droiddev.sdu.R;
import com.droiddev.sdu.teacher.model.ModelActivityList;

import java.util.ArrayList;


public class AdapterActivityList extends RecyclerView.Adapter<AdapterActivityList.VersionViewHolder> {

    private Context context;
    OnItemClickListener clickListener;
    private ArrayList<ModelActivityList> list;


    public AdapterActivityList(Context context, ArrayList<ModelActivityList> list) {
        this.context = context;
        this. list = list;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_activity, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
        try {
            versionViewHolder.txtSubject.setText(list.get(i).getCode()+" "+list.get(i).getName());
            versionViewHolder.txtMajor.setText("ห้องสอบ : "+list.get(i).getRoom());
            versionViewHolder.txtTime.setText("เวลาสอบ : "+list.get(i).getDate()+" "+list.get(i).getStart()+"-"+list.get(i).getStop());
        } catch (Exception e) {
            Log.d("test", ""+e);
        }

        switch (list.get(i).getStatus()) {
            case 1:
                versionViewHolder.img.setVisibility(View.VISIBLE);
                break;
            case 2:
                versionViewHolder.img.setVisibility(View.INVISIBLE);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtSubject,txtMajor,txtTime;
        ImageView img;

        public VersionViewHolder(View itemView) {
            super(itemView);

            txtSubject = (TextView) itemView.findViewById(R.id.txtTitle);
            txtMajor = (TextView) itemView.findViewById(R.id.txtMajor);
            txtTime = (TextView) itemView.findViewById(R.id.txtTime);
            img = (ImageView) itemView.findViewById(R.id.img);

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
