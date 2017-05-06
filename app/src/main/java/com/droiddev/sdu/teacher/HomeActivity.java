package com.droiddev.sdu.teacher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.droiddev.sdu.ConnectAPI;
import com.droiddev.sdu.R;
import com.droiddev.sdu.SplashActivity;
import com.droiddev.sdu.teacher.fragment.QuizFragment;
import com.droiddev.sdu.teacher.model.ModelActivityList;
import com.droiddev.sdu.teacher.fragment.ActivityListFragment;
import com.droiddev.sdu.teacher.fragment.HomeFragment;
import com.droiddev.sdu.teacher.fragment.NewsFragment;
import com.droiddev.sdu.teacher.fragment.ProfileFragment;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    Fragment selectFragment = null;
    FloatingActionButton fab;
    Toolbar toolbar;
    int ID = 0;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d("HomeActivity", "Key: " + key + " Value: " + value);
            }
        }

        sp = getSharedPreferences("Preferences_SDU", Context.MODE_PRIVATE);
        ID = sp.getInt("id",0);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Welcome");

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        setDrawer();

        selectFragment = new HomeFragment();
        setAdapterFragment("",4);
        drawer.openDrawer(GravityCompat.START);
        fab.setVisibility(View.INVISIBLE);

    }

    private void setDrawer() {
        String title = sp.getString("title", "test");
        String fullname = sp.getString("fullname", "test test");
        String email = sp.getString("email", "test@test.test");
        String image = sp.getString("image", "");
        String url = sp.getString("url", "");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);

        TextView nav_name = (TextView)hView.findViewById(R.id.name);
        TextView nav_email = (TextView)hView.findViewById(R.id.email);
        ImageView imag = (ImageView)hView.findViewById(R.id.imageView);

        nav_name.setText(title+" "+fullname);
        nav_email.setText(email);

        Glide.with(HomeActivity.this)
                .load(url+"/teacherImg_resize/"+image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.nopic)
                .into(imag);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.activity) {
            new ConnectAPI().AllActivityTeacher(HomeActivity.this, ID);
        } else if (id == R.id.news) {
            new ConnectAPI().AllNews(HomeActivity.this);
        } else if (id == R.id.profile) {
            startActivity(new Intent(HomeActivity.this,ProfileActivity.class).putExtra("id",ID));
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.logout) {
            SharedPreferences sp = getSharedPreferences("Preferences_SDU", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("login", false);
            editor.commit();

            startActivity(new Intent(HomeActivity.this,SplashActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        } else if (id == R.id.history) {
            new ConnectAPI().AllhistoryId(HomeActivity.this,ID);
        }

        return true;
    }

    public void setAdapterFragment(String string, int mode) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(selectFragment);
        selectFragment = null;

        switch (mode) {
            case 1:
                toolbar.setTitle("กิจกรรม");
                selectFragment = new ActivityListFragment().newInstance(string);
                fab.setVisibility(View.INVISIBLE);
                break;
            case 2:
                toolbar.setTitle("ข่าวสาร");
                selectFragment = new NewsFragment().newInstance(string);
                fab.setVisibility(View.INVISIBLE);
                break;
            case 3:
                toolbar.setTitle("ประวัติส่วนตัว");
                selectFragment = new ProfileFragment();
                fab.setVisibility(View.INVISIBLE);
                break;
            case 4:
                toolbar.setTitle("หน้าแรก");
                selectFragment = new HomeFragment();
                fab.setVisibility(View.INVISIBLE);
                break;
            case 9:
                toolbar.setTitle("กิจกรรมแบ่งตามสาขาทั้งหมด");
                selectFragment = new QuizFragment().newInstance(string);
                fab.setVisibility(View.INVISIBLE);
                break;
        }

        transaction.replace(R.id.content, selectFragment);
        transaction.commit();

        drawer.closeDrawer(GravityCompat.START);
    }
}
