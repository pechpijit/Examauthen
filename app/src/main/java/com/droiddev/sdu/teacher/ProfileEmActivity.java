package com.droiddev.sdu.teacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.droiddev.sdu.ConnectAPI;
import com.droiddev.sdu.R;
import com.droiddev.sdu.teacher.model.ModelEmployee;
import com.droiddev.sdu.teacher.model.ModelProfile;
import com.google.gson.Gson;

public class ProfileEmActivity extends AppCompatActivity {
    int ID = 0;
    TextView name, email,phone;
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
        setContentView(R.layout.activity_profile_em);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle i = getIntent().getExtras();

        SharedPreferences sp = getSharedPreferences("Preferences_SDU", Context.MODE_PRIVATE);
        url = sp.getString("url", "");
        ID = i.getInt("id", 0);

        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone);

        layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        imageView = (ImageView) findViewById(R.id.imageView);

        new ConnectAPI().ProfileEm(ProfileEmActivity.this,ID);

    }

    public void setView(String string) {
        Gson gson = new Gson();
        ModelEmployee profile = gson.fromJson(string, ModelEmployee.class);
        name.setHint(profile.getName());
        phone.setHint(profile.getPhone());
        email.setHint(profile.getEmail());
        layout.setTitle(profile.getName());

        Glide.with(ProfileEmActivity.this)
                .load(url+"/customerImage_resize/"+profile.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.nopic)
                .into(imageView);
    }
}
