package com.droiddev.sdu.teacher.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.droiddev.sdu.R;
import com.droiddev.sdu.teacher.model.ModelEmployee;
import com.droiddev.sdu.teacher.model.ModelProfile;

import java.util.ArrayList;


public class AdapterEmployee extends RecyclerView.Adapter<AdapterEmployee.VersionViewHolder> {

    Context context;
    OnItemClickListener clickListener;
    private ModelEmployee list;
    private String url;

    public AdapterEmployee(Context context, ModelEmployee list, String url) {
        this.context = context;
        this. list = list;
        this.url = url;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_employee, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
        versionViewHolder.txtSubject.setText(list.getName());

        Glide.with(context)
                .load(url+"/customerImage_resize/"+list.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.nopic)
                .into(versionViewHolder.imgProfile);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtSubject;
        ImageView imgProfile;

        public VersionViewHolder(View itemView) {
            super(itemView);

            txtSubject = (TextView) itemView.findViewById(R.id.txtTitle);
            imgProfile = (ImageView) itemView.findViewById(R.id.imgProfile);

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
