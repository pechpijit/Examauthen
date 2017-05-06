package com.droiddev.sdu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.droiddev.sdu.admin.HomeAdminActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginAdminActivity extends AppCompatActivity {
    Button _loginButton;
    TextView _usernameText,_passwordText;
    String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_admin);
        _loginButton = (Button) findViewById(R.id.btn_login);
        _usernameText = (TextView) findViewById(R.id.input_email);
        _passwordText = (TextView) findViewById(R.id.input_password);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login() {
        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginAdminActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();

        new ConnectAPI().AdminLogin(LoginAdminActivity.this,email,password);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess(String s,String url) {
        _loginButton.setEnabled(true);
        new InsertUser().ConverAdminDetail(LoginAdminActivity.this, s,url);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (username.isEmpty() || username.length() < 6) {
            _usernameText.setError("enter a valid email address");
            valid = false;
        } else {
            _usernameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            _passwordText.setError("at least  4 characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
