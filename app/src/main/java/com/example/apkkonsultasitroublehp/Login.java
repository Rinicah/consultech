package com.example.apkkonsultasitroublehp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button btnLogin, btnCancel;
    String usernamekey, passwordkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.loginLogin);
        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        btnCancel = findViewById(R.id.cancelLogin);

        btnCancel = (Button) findViewById(R.id.cancelLogin);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernamekey = username.getText().toString();
                passwordkey = password.getText().toString();
                if (usernamekey.equals("rini") && passwordkey.equals("2505")) {
                    Toast.makeText(getApplicationContext(), "Login Berhasil",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, Home.class));
                    finish();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage("Username dan Password Salah").setNegativeButton("Coba Lagi",null).show();
                }
            }
        });
    }
}