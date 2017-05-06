package com.droiddev.sdu.admin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.droiddev.sdu.ConnectAPI;
import com.droiddev.sdu.R;
import com.droiddev.sdu.admin.fragment.ActivityShowAdmin;
import com.droiddev.sdu.admin.fragment.BracodeBtnFragment;
import com.droiddev.sdu.admin.fragment.CommitteeListFragmentAdmin;
import com.droiddev.sdu.teacher.ReportActivity;
import com.droiddev.sdu.teacher.fragment.ActivityShow;
import com.droiddev.sdu.teacher.fragment.CommitteeListFragment;
import com.droiddev.sdu.teacher.model.ModelActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class QuizActivityAdmin extends AppCompatActivity {

    ViewPagerAdapter adapter;
    int ID = 0;
    TabLayout tabLayout;

    FloatingActionButton fab;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setAction("กิจกรรมการสอบ");
        Bundle i = getIntent().getExtras();
        ID = i.getInt("id", 0);

        BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish_activity")) {
                    finish();
                    // DO WHATEVER YOU WANT.
                }
            }
        };
        registerReceiver(broadcast_reciever, new IntentFilter("finish_activity"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.tabanim_viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizActivityAdmin.this, ReportActivity.class).putExtra("id",ID));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }

    private void setAction(String s) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tabanim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("ข้อมูลรายละเอียดกิจกรรม");
        getSupportActionBar().setTitle(s);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        new ConnectAPI().ActivityTeacherIdAdmin(QuizActivityAdmin.this,ID,viewPager);
    }

    public void setFragment(String string, ViewPager viewPager) {
        SharedPreferences sp = getSharedPreferences("Preferences_SDU", Context.MODE_PRIVATE);
        String url = sp.getString("url", "");

        Gson gson = new Gson();
        ModelActivity m = gson.fromJson(string, ModelActivity.class);
        int temp = m.getReport().length();
        if (temp > 0) {
            fab.setVisibility(View.INVISIBLE);
        } else {
            fab.setVisibility(View.VISIBLE);
        }

        adapter.addFrag(new ActivityShowAdmin().newInstance(string), "ข้อมูลกิจกรรม");
        adapter.addFrag(new CommitteeListFragmentAdmin().newInstance(string,url), "กรรมการคุมสอบ");
        if (m.getStatus().equals("0")) {
            adapter.addFrag(new BracodeBtnFragment().newInstance(m.getId()), "SCAN");
        }
        viewPager.setAdapter(adapter);
        tabLayout.setVisibility(View.VISIBLE);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
