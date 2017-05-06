package com.droiddev.sdu.teacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.Script;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.droiddev.sdu.ConnectAPI;
import com.droiddev.sdu.R;
import com.droiddev.sdu.teacher.model.ModelNews;
import com.google.gson.Gson;

public class NewsActivity extends AppCompatActivity {

    int ID = 0;
    Toolbar toolbar;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle i = getIntent().getExtras();
        ID = i.getInt("id", 0);

        new ConnectAPI().NewsId(NewsActivity.this, ID);
    }

    public void setView(String string) {
        Gson gson = new Gson();
        ModelNews news = gson.fromJson(string, ModelNews.class);

        CollapsingToolbarLayout layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        TextView txtContent = (TextView) findViewById(R.id.txtConnect);
        TextView txtTime = (TextView) findViewById(R.id.txtTime);
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        ImageView img = (ImageView) findViewById(R.id.image);

        txtContent.setText(news.getNewsDetail());
        txtTime.setText(news.getCreatedAt());
        txtTitle.setText(news.getNewsName());
        layout.setTitle(news.getNewsName());

        Glide.with(NewsActivity.this)
                .load(R.drawable.ic_back)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.nopic)
                .into(img);

        Toast.makeText(NewsActivity.this, ""+news.getNewsName(), Toast.LENGTH_SHORT).show();
    }
}
