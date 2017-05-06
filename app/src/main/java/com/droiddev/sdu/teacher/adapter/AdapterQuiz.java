package com.droiddev.sdu.teacher.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.droiddev.sdu.R;
import com.droiddev.sdu.admin.model.ModelHistory;

import java.util.ArrayList;


public class AdapterQuiz extends RecyclerView.Adapter<AdapterQuiz.VersionViewHolder> {

    private Context context;
    OnItemClickListener clickListener;
    private ArrayList<ModelHistory> list;


    public AdapterQuiz(Context context, ArrayList<ModelHistory> list) {
        this.context = context;
        this. list = list;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_major, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
            versionViewHolder.txtTitle.setText("ยืนยันการรับข้อสอบ : \n"+list.get(i).getQuizId());
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
