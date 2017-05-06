package com.droiddev.sdu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.droiddev.sdu.admin.HomeAdminActivity;
import com.droiddev.sdu.teacher.HomeActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    Runnable runnable;
    long delay_time;
    long time = 3000L;
    LinearLayout btnView;
    ProgressBar proView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

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

        btnView = (LinearLayout) findViewById(R.id.btnView);
        proView = (ProgressBar) findViewById(R.id.proView);

        SharedPreferences sp = getSharedPreferences("Preferences_SDU", Context.MODE_PRIVATE);
        boolean user = sp.getBoolean("login", false);
        String permission = sp.getString("permission", "teacher");

        if (user) {
            if (permission.equals("teacher")) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            } else if (permission.equals("admin")) {
                startActivity(new Intent(SplashActivity.this, HomeAdminActivity.class));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        } else {
//            isNetworkConnected();
            btnView.setVisibility(View.VISIBLE);
            proView.setVisibility(View.INVISIBLE);
        }


        findViewById(R.id.btn_teacher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this,LoginTeacherActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        findViewById(R.id.btn_authrities).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this,LoginAdminActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    public void NotConnectInternet() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setTitle("เกิดข้อผิดพลาด");
        builder.setCancelable(true);
        builder.setMessage("ไม่สามารถเชื่อมต่ออินเทอร์เน็ตได้ กรุณาลองใหม่อีกครั้ง");
        builder.setPositiveButton("ลองอีกครั้ง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(new Intent(getApplicationContext(), SplashActivity.class));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }

    public void isNetworkConnected() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url("http://www.google.com").build();

                try {
                    OkHttpClient copy = okHttpClient.newBuilder()
                            .readTimeout(30000, TimeUnit.MILLISECONDS)
                            .build();
                    Response response = copy.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);

                Log.d("SplashActivity", string);
                String[] temp = string.split(" ");

                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    NotConnectInternet();
                } else {
                    btnView.setVisibility(View.VISIBLE);
                    proView.setVisibility(View.INVISIBLE);
                }
            }
        }.execute();
    }

    public void onResume() {
        super.onResume();
        delay_time = time;
//        handler.postDelayed(runnable, delay_time);
        time = System.currentTimeMillis();
    }

    public void onPause() {
        super.onPause();
//        handler.removeCallbacks(runnable);
        time = delay_time - (System.currentTimeMillis() - time);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}