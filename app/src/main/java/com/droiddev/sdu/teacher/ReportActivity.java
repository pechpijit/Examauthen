package com.droiddev.sdu.teacher;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.droiddev.sdu.ConnectAPI;
import com.droiddev.sdu.R;

public class ReportActivity extends AppCompatActivity {

    EditText editText;
    Button btn;
    int ID = 0;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Bundle i = getIntent().getExtras();
        ID = i.getInt("id", 0);

        editText = (EditText) findViewById(R.id.editText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btn = (Button) findViewById(R.id.btn_send);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(ReportActivity.this)
                        .setTitle("ยืนยันการรายงาน")
                        .setMessage("กดตกลงเพื่อยืนกันการรายงาน")
                        .setNegativeButton("ยืนยัน", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                progressBar.setVisibility(View.VISIBLE);
                                btn.setEnabled(false);
                                new ConnectAPI().TeacherReport(ReportActivity.this, ID, editText.getText().toString().trim());
                            }
                        })
                        .setPositiveButton("ยกเลิก",null)
                        .show();


            }
        });

    }

    public void Success() {
        progressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent("finish_activity");
        sendBroadcast(intent);
        startActivity(new Intent(ReportActivity.this,QuizActivity.class).putExtra("id",ID));
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void Fail(String string) {
        progressBar.setVisibility(View.INVISIBLE);
        btn.setEnabled(true);
        dialogErrorNoIntent(ReportActivity.this,string);
    }

    private static void dialogErrorNoIntent(final Activity context, String string) {
        new AlertDialog.Builder(context)
                .setTitle("The system temporarily")
                .setMessage("ไม่สามารถเข้าใช้งานได้ กรุณาลองใหม่ภายหลัง error code = " + string)
                .setNegativeButton("OK", null)
                .show();
    }


}
