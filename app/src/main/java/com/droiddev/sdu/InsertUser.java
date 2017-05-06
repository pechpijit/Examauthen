package com.droiddev.sdu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.droiddev.sdu.admin.HomeAdminActivity;
import com.droiddev.sdu.admin.model.ModelAdmin;
import com.droiddev.sdu.teacher.model.ModelProfile;
import com.droiddev.sdu.teacher.HomeActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

public class InsertUser {

    public void ConverUserDetail(Activity context,String string,String url) {
        Gson gson = new Gson();
        ModelProfile m = gson.fromJson(string, ModelProfile.class);

        Intent intent = new Intent("finish_activity");
        context.sendBroadcast(intent);

        SharedPreferences sp = context.getSharedPreferences("Preferences_SDU", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putBoolean("login", true);
        editor.putString("permission", "teacher");
        editor.putString("url",url);
        editor.putInt("id", m.getId());
        editor.putString("code", m.getCode());
        editor.putString("username", m.getUsername());
        editor.putString("firstname", m.getFirstname());
        editor.putString("lastname", m.getLastname());
        editor.putString("fullname", m.getFullname());
        editor.putString("email", m.getEmail());
        editor.putString("image", m.getImage());
        editor.putString("gender", m.getGender());
        editor.putString("date", m.getDate());
        editor.putString("phone", m.getPhone());
        editor.putString("fax", m.getFax());
        editor.putInt("status", m.getStatus());
        editor.putString("idCard", m.getIdCard());
        editor.putString("title", m.getTitle());
        editor.putString("major", m.getMajor());
        editor.putString("faculty", m.getFaculty());
        editor.putString("position", m.getPosition());
        editor.commit();

        context.startActivity(new Intent(context, HomeActivity.class));
        context.finish();
        context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    public void ConverAdminDetail(Activity context,String string,String url) {
        Gson gson = new Gson();
        ModelAdmin m = gson.fromJson(string, ModelAdmin.class);

        Intent intent = new Intent("finish_activity");
        context.sendBroadcast(intent);

        SharedPreferences sp = context.getSharedPreferences("Preferences_SDU", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putBoolean("login", true);
        editor.putString("permission", "admin");
        editor.putString("url",url);
        editor.putInt("id", m.getId());
        editor.putString("name", m.getName());
        editor.putString("email", m.getEmail());
        editor.commit();

        context.startActivity(new Intent(context, HomeAdminActivity.class));
        context.finish();
        context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
}
