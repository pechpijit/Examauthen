package com.droiddev.sdu.admin;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.droiddev.sdu.ConnectAPI;
import com.droiddev.sdu.R;
import com.droiddev.sdu.SplashActivity;
import com.droiddev.sdu.admin.fragment.ActivityListAllFragment;
import com.droiddev.sdu.admin.fragment.ActivityListFragment;
import com.droiddev.sdu.admin.fragment.FacultyFragment;
import com.droiddev.sdu.admin.fragment.FacultyMajorFragment;
import com.droiddev.sdu.admin.fragment.MajorFragment;
import com.droiddev.sdu.teacher.fragment.QuizFragment;
import com.droiddev.sdu.teacher.fragment.HomeFragment;


public class HomeAdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    Fragment selectFragment = null;
    FloatingActionButton fab;
    Toolbar toolbar;
    int ID = 0;
    SharedPreferences sp;
    FrameLayout frameLayout;
    int menu2 = 0;
    int menu3 = 0;
    int fa_ma = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        frameLayout = (FrameLayout) findViewById(R.id.fram);

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);

        selectFragment = new HomeFragment();
        setAdapterFragment("",5);
        drawer.openDrawer(GravityCompat.START);
        fab.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (fa_ma == 1 || menu2 == 1||menu3 == 1) {
            if (fa_ma == 1) {
                new ConnectAPI().AllActivityFaculty2(HomeAdminActivity.this);
                fa_ma = 0;
            }
            if (menu2 == 1) {
                new ConnectAPI().AllActivityFaculty(HomeAdminActivity.this);
                menu2 = 0;
            }

            if (menu3 == 1) {
                new ConnectAPI().AllActivityMajor(HomeAdminActivity.this);
                menu3 = 0;
            }

        } else {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (fa_ma == 1 || menu2 == 1||menu3 == 1) {
            if (fa_ma == 1) {
                fa_ma = 0;
            }
            if (menu2 == 1) {
                menu2 = 0;
            }
            if (menu3 == 1) {
                menu3 = 0;
            }
        }

        if (id == R.id.menu1) {
            frameLayout.setVisibility(View.VISIBLE);
            new ConnectAPI().AllActivity(HomeAdminActivity.this);
        } else if (id == R.id.menu2) {
            frameLayout.setVisibility(View.VISIBLE);
            new ConnectAPI().AllActivityFaculty(HomeAdminActivity.this);
        }else if (id == R.id.menu3) {
            frameLayout.setVisibility(View.VISIBLE);
            new ConnectAPI().AllActivityMajor(HomeAdminActivity.this);
        }else if (id == R.id.menu4) {
            frameLayout.setVisibility(View.VISIBLE);
            new ConnectAPI().AllActivityFaculty2(HomeAdminActivity.this);
        }else if (id == R.id.logout) {
            SharedPreferences sp = getSharedPreferences("Preferences_SDU", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("login", false);
            editor.commit();

            startActivity(new Intent(HomeAdminActivity.this,SplashActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setAdapterFragment(String string, int mode) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(selectFragment);
        selectFragment = null;

        switch (mode) {
            case 1:
                toolbar.setTitle("กิจกรรมทั้งหมด");
                selectFragment = new ActivityListAllFragment().newInstance(string);
                fab.setVisibility(View.INVISIBLE);
                frameLayout.setVisibility(View.INVISIBLE);
                break;
            case 2:
                toolbar.setTitle("กิจกรรมแบ่งตามคณะทั้งหมด");
                selectFragment = new FacultyFragment().newInstance(string);
                fab.setVisibility(View.INVISIBLE);
                frameLayout.setVisibility(View.INVISIBLE);
                break;
            case 3:
                toolbar.setTitle("กิจกรรมแบ่งตามสาขาทั้งหมด");
                selectFragment = new MajorFragment().newInstance(string);
                fab.setVisibility(View.INVISIBLE);
                frameLayout.setVisibility(View.INVISIBLE);
                break;
            case 4:
                toolbar.setTitle("กิจกรรมแบ่งคณะ/สาขา");
                selectFragment = new FacultyMajorFragment().newInstance(string);
                fab.setVisibility(View.INVISIBLE);
                frameLayout.setVisibility(View.INVISIBLE);
                break;
            case 5:
                toolbar.setTitle("หน้าแรก");
                selectFragment = new HomeFragment();
                fab.setVisibility(View.INVISIBLE);
                break;
            case 6:
                fa_ma = 1;
                toolbar.setTitle("กิจกรรมแบ่งคณะ/สาขา");
                selectFragment = new MajorFragment().newInstance(string);
                fab.setVisibility(View.INVISIBLE);
                frameLayout.setVisibility(View.INVISIBLE);
                break;
            case 7:
                menu2 = 1;
                toolbar.setTitle("กิจกรรมแบ่งตามคณะทั้งหมด");
                selectFragment = new ActivityListFragment().newInstance(string);
                fab.setVisibility(View.INVISIBLE);
                frameLayout.setVisibility(View.INVISIBLE);
                break;
            case 8:
                menu3 = 1;
                toolbar.setTitle("กิจกรรมแบ่งตามคณะทั้งหมด");
                selectFragment = new ActivityListFragment().newInstance(string);
                fab.setVisibility(View.INVISIBLE);
                frameLayout.setVisibility(View.INVISIBLE);
                break;
        }

        transaction.replace(R.id.content, selectFragment);
        try {
            transaction.commit();
        } catch (Exception e) {

        }
    }
}
