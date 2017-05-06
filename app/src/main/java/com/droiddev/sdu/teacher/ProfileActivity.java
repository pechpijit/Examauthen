package com.droiddev.sdu.teacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.droiddev.sdu.ConnectAPI;
import com.droiddev.sdu.R;
import com.droiddev.sdu.teacher.model.ModelProfile;
import com.google.gson.Gson;

public class ProfileActivity extends AppCompatActivity {
    int ID = 0;
    TextView name, email,teacherid,date,phone,fax,idcard,address,faculty,major,position;
    ImageView imageView;
    CollapsingToolbarLayout layout;
    String url;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle i = getIntent().getExtras();

        SharedPreferences sp = getSharedPreferences("Preferences_SDU", Context.MODE_PRIVATE);
        url = sp.getString("url", "");


        ID = i.getInt("id", 0);

        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        teacherid = (TextView) findViewById(R.id.teacherid);
        date = (TextView) findViewById(R.id.date);
        phone = (TextView) findViewById(R.id.phone);
        fax = (TextView) findViewById(R.id.fax);
        idcard = (TextView) findViewById(R.id.idcard);
        address = (TextView) findViewById(R.id.address);
        faculty = (TextView) findViewById(R.id.faculty);
        major = (TextView) findViewById(R.id.major);
        position = (TextView) findViewById(R.id.position);

        layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        imageView = (ImageView) findViewById(R.id.imageView);

        new ConnectAPI().Profile(ProfileActivity.this,ID);

    }

    public void setView(String string) {
        Gson gson = new Gson();
        ModelProfile profile = gson.fromJson(string, ModelProfile.class);
        name.setHint(profile.getTitle()+" "+profile.getFullname());
        email.setHint(profile.getEmail());
        teacherid.setHint(profile.getCode());
        date.setHint(profile.getDate());
        phone.setHint(profile.getPhone());
        fax.setHint(profile.getFax());
        idcard.setHint(profile.getIdCard());
        address.setHint(profile.getAddress());
        faculty.setHint(profile.getFaculty());
        major.setHint(profile.getMajor());
        position.setHint(profile.getPosition());
        layout.setTitle(profile.getTitle()+" "+profile.getFullname());

        Glide.with(ProfileActivity.this)
                .load(url+"/teacherImg_resize/"+profile.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.nopic)
                .into(imageView);
    }
}
