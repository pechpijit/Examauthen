package com.droiddev.sdu;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.droiddev.sdu.admin.Camera_QRscan;
import com.droiddev.sdu.admin.HomeAdminActivity;
import com.droiddev.sdu.admin.QuizActivityAdmin;
import com.droiddev.sdu.teacher.HomeActivity;
import com.droiddev.sdu.teacher.NewsActivity;
import com.droiddev.sdu.teacher.ProfileActivity;
import com.droiddev.sdu.teacher.QuizActivity;
import com.droiddev.sdu.teacher.ReportActivity;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConnectAPI {
    String URL = "http://192.168.1.50/examauthen/public";

    public void TeacherLogin(final Activity context, final String username, final String password, final String token) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, "username=" + username + "&password=" + password+"&token="+token);
                Request request = new Request.Builder()
                        .url(URL+"/api/teacher/login")
                        .post(body)
                        .addHeader("content-type", "application/x-www-form-urlencoded")
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "cfe46e2d-6123-90fd-faa8-6369b65d2bb6")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
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
                Log.d("ConnectAPI : ", "TeacherLogin " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error")||temp[0].equals("Not")) {
                    dialogErrorNoIntent(context, string);
                }  else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else if (string.equals("NotFound")) {
                    dialogError(context, string);
                } else {
                    ((LoginTeacherActivity) context).onLoginSuccess(string,URL);
                }
            }
        }.execute();
    }

    public void AdminLogin(final Activity context, final String email, final String password) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, "email=" + email + "&password=" + password);
                Request request = new Request.Builder()
                        .url(URL+"/api/admin/login")
                        .post(body)
                        .addHeader("content-type", "application/x-www-form-urlencoded")
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "69db5fba-f03a-0bdf-778c-cd8179b4d300")
                        .build();


                try {
                    Response response = client.newCall(request).execute();
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
                Log.d("ConnectAPI : ", "TeacherLogin " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error")||temp[0].equals("Not")) {
                    dialogErrorNoIntent(context, string);
                }  else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else if (string.equals("NotFound")) {
                    dialogError(context, string);
                }else {
                    ((LoginAdminActivity) context).onLoginSuccess(string,URL);
                }
            }
        }.execute();
    }

    public void AllActivityTeacher(final Activity context, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL + "/api/activity/teacher/"+id).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
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
                Log.i("AllActivityTeacher : ", "AllActivityTeacher " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error")||temp[0].equals("Not")) {
                    dialogError(context, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else if (string.equals("NotFound")) {
                    dialogError(context, string);
                } else {
                    ((HomeActivity)context).setAdapterFragment(string,1);
                }
            }
        }.execute();
    }

    public void AllhistoryId(final Activity context, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL + "/api/history/"+id).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
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
                Log.i("AllhistoryId : ", "AllhistoryId " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error")||temp[0].equals("Not")) {
                    dialogError(context, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else if (string.equals("NotFound")) {
                    dialogError(context, string);
                } else {
                    ((HomeActivity)context).setAdapterFragment(string,9);
                }
            }
        }.execute();
    }

    public void ActivityTeacherId(final Activity context, final int id, final ViewPager viewPager) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL + "/api/activity/"+id).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
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
                Log.i("ActivityTeacherId : ", "ActivityTeacherId " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error")||temp[0].equals("Not")) {
                    dialogError(context, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else {
                    ((QuizActivity)context).setFragment(string,viewPager);
                }
            }
        }.execute();
    }

    public void ActivityTeacherIdAdmin(final Activity context, final int id, final ViewPager viewPager) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL + "/api/activity/"+id).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
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
                Log.i("ActivityTeacherId : ", "ActivityTeacherId " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error")||temp[0].equals("Not")) {
                    dialogError(context, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else {
                    ((QuizActivityAdmin)context).setFragment(string,viewPager);
                }
            }
        }.execute();
    }

    public void TeacherReport(final Activity context, final int id, final String text) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, "id="+id+"&message="+text);
                Request request = new Request.Builder()
                        .url(URL+"/api/activity/resport")
                        .post(body)
                        .addHeader("content-type", "application/x-www-form-urlencoded")
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "1ec49955-d61a-1306-0148-bb54c39775fd")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
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
                Log.d("ConnectAPI : ", "TeacherLogin " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error")||temp[0].equals("Not")) {
                    dialogErrorNoIntent(context, string);
                }  else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else if (string.equals("NotFound")) {
                    ((ReportActivity) context).Fail(string);
                }else if (string.equals("success")){
                    ((ReportActivity) context).Success();
                }
            }
        }.execute();
    }

    public void AdminScan(final Activity context, final int id, final String code) {
        Log.d("AdminScan : ", "input " + id+" "+code);
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, "id="+id+"&code="+code);
                Request request = new Request.Builder()
                        .url(URL+"/api/activity/scan")
                        .post(body)
                        .addHeader("content-type", "application/x-www-form-urlencoded")
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "25d2a818-403c-1f4e-8717-67a9a24d58df")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
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
                Log.d("AdminScan : ", "AdminScan " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error")||temp[0].equals("Not")) {
                    dialogErrorNoIntent(context, string);
                }  else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else if (string.equals("NotFound")) {
                    ((Camera_QRscan) context).Fail(string);
                }else if (string.equals("success")){
                    ((Camera_QRscan) context).Success();
                }
            }
        }.execute();
    }

    public void AllNews(final Activity context) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL + "/api/newslist").build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
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
                Log.i("AllNews : ", "AllNews " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error")||temp[0].equals("Not")) {
                    dialogError(context, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else {
                    ((HomeActivity)context).setAdapterFragment(string,2);
                }
            }
        }.execute();
    }

    public void NewsId(final Activity context, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL + "/api/news/"+id).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
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
                Log.i("NewsId : ", "NewsId " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error")||temp[0].equals("Not")) {
                    dialogError(context, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else {
                    ((NewsActivity) context).setView(string);
                }
            }
        }.execute();
    }

    public void Profile(final Activity context, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL + "/api/teacher/"+id).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
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
                Log.i("NewsId : ", "NewsId " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error")||temp[0].equals("Not")) {
                    dialogError(context, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else {
                    ((ProfileActivity) context).setView(string);
                }
            }
        }.execute();
    }

    public void AllActivity(final Activity context, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL + "/api/activity/user/"+id).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
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
                Log.i("AllActivity : ", "AllActivity " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error")||temp[0].equals("Not")) {
                    dialogError(context, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else if (string.equals("NotFound")) {
                    dialogError(context, string);
                } else {
                    ((HomeAdminActivity)context).setAdapterFragment(string,1);
                }
            }
        }.execute();
    }

    public void AllActivityMajorId(final Activity context, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL + "/api/activity/major/"+id).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
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
                Log.i("AllActivityFaculty : ", "AllActivityFaculty " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") ||temp[0].equals("Not")) {
                    dialogError(context, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else if (string.equals("NotFound")) {
                    dialogError(context, string);
                } else {
                    ((HomeAdminActivity)context).setAdapterFragment(string,8);
                }
            }
        }.execute();
    }

    public void AllActivityFacultyId(final Activity context, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL + "/api/activity/faculty/"+id).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
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
                Log.i("AllActivityFaculty : ", "AllActivityFaculty " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") ||temp[0].equals("Not")) {
                    dialogError(context, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else if (string.equals("NotFound")) {
                    dialogError(context, string);
                } else {
                    ((HomeAdminActivity)context).setAdapterFragment(string,7);
                }
            }
        }.execute();
    }

    public void AllActivityFaculty(final Activity context) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL + "/api/faculty/all").build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
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
                Log.i("AllActivityFaculty : ", "AllActivityFaculty " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") ||temp[0].equals("Not")) {
                    dialogError(context, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else if (string.equals("NotFound")) {
                    dialogError(context, string);
                } else {
                    ((HomeAdminActivity)context).setAdapterFragment(string,2);
                }
            }
        }.execute();
    }

    public void AllActivityFaculty2(final Activity context) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL + "/api/faculty/all").build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
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
                Log.i("AllActivityFaculty : ", "AllActivityFaculty " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") ||temp[0].equals("Not")) {
                    dialogError(context, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else if (string.equals("NotFound")) {
                    dialogError(context, string);
                } else {
                    ((HomeAdminActivity)context).setAdapterFragment(string,4);
                }
            }
        }.execute();
    }

    public void AllActivityMajor(final Activity context) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL + "/api/major/all").build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
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
                Log.i("AllActivityMajor : ", "AllActivityMajor " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") ||temp[0].equals("Not")) {
                    dialogError(context, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else if (string.equals("NotFound")) {
                    dialogError(context, string);
                } else {
                    ((HomeAdminActivity)context).setAdapterFragment(string,3);
                }
            }
        }.execute();
    }

    public void AllActivityMajor2(final Activity context, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url(URL + "/api/major/faculty/"+id).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
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
                Log.i("AllActivityMajor : ", "AllActivityMajor " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") ||temp[0].equals("Not")) {
                    dialogError(context, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(context);
                } else if (string.equals("NotFound")) {
                    dialogError(context, string);
                } else {
                    ((HomeAdminActivity)context).setAdapterFragment(string,6);
                }
            }
        }.execute();
    }

    private static void dialogError(final Activity context, String string) {
        new AlertDialog.Builder(context)
                .setTitle("The system temporarily")
                .setMessage("ไม่สามารถเข้าใช้งานได้ กรุณาลองใหม่ภายหลัง error code = " + string)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.finish();
                        context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                })
                .show();
    }

    private static void dialogErrorNoIntent(final Activity context, String string) {
        new AlertDialog.Builder(context)
                .setTitle("The system temporarily")
                .setMessage("ไม่สามารถเข้าใช้งานได้ กรุณาลองใหม่ภายหลัง error code = " + string)
                .setNegativeButton("OK", null)
                .show();
    }

    private static void dialogNotfound(final Activity context) {
        new AlertDialog.Builder(context)
                .setTitle("The system temporarily")
                .setMessage("ไม่พบข้อมูล")
                .setNegativeButton("OK", null)
                .show();
    }
}
