package com.droiddev.sdu.admin;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.droiddev.sdu.ConnectAPI;
import com.droiddev.sdu.R;
import com.droiddev.sdu.teacher.QuizActivity;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class Camera_QRscan extends AppCompatActivity implements ZXingScannerView.ResultHandler,ActivityCompat.OnRequestPermissionsResultCallback {

    private ZXingScannerView mScannerView;
    private static final int REQUEST_CAMERA = 0;
    public static final String EXTRA_DATA = "EXTRA_DATA";
    ProgressDialog progressDialog;
    int ID = 0;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_qr_scan);
        Bundle i = getIntent().getExtras();
        ID = i.getInt("id", 0);
        Start_QR_Scan();//เปิดกล้อง
    }

    private void Start_QR_Scan() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission();
        }
        QrScanner();
    }

    public void QrScanner(){
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.setAutoFocus(true);
        mScannerView.startCamera();
    }

    private void requestCameraPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CAMERA) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                QrScanner();
            } else {
                finish();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        //ผลลัพธ์ที่ได้

        Log.e("handler", rawResult.getText()); // Prints scan results
        Log.e("handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)
        Log.e("handler", rawResult.getResultPoints().toString()); // Prints the scan format (qrcode)

        dialog();

        new ConnectAPI().AdminScan(Camera_QRscan.this,ID,rawResult.getText());

//        String string = rawResult.getText();
//        final Intent data = new Intent();
//        data.putExtra(EXTRA_DATA, string);
//        setResult(Activity.RESULT_OK, data);
//        finish();
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

//        // show the scanner result into dialog box.
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Scan Result");
//        builder.setMessage(rawResult.getText());
//        AlertDialog alert1 = builder.create();
//        alert1.show();

        // If you would like to resume scanning, call this method below:
        // mScannerView.resumeCameraPreview(this);
    }

    private void dialog() {
        progressDialog = new ProgressDialog(Camera_QRscan.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
    }

    public void Success() {
        Log.d("success", "Success");
        Intent intent = new Intent("finish_activity");
        sendBroadcast(intent);
        progressDialog.dismiss();
        dialogSuccess(Camera_QRscan.this);
    }

    public void Fail(String string) {
        progressDialog.dismiss();
        dialogErrorNoIntent(Camera_QRscan.this,string);
    }

    private void dialogErrorNoIntent(final Activity context, String string) {
        new AlertDialog.Builder(context)
                .setTitle("The system temporarily")
                .setMessage("ไม่สามารถใช้งานได้ กรุณาลองใหม่ภายหลัง error code = " + string)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                })
                .show();
    }

    private void dialogSuccess(final Activity context) {
        new AlertDialog.Builder(context)
                .setTitle("Success")
                .setMessage("ยืนยันการรับข้อสอบสำเร็จแล้ว")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        startActivity(new Intent(Camera_QRscan.this,QuizActivityAdmin.class).putExtra("id",ID));
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                })
                .show();
    }
}
