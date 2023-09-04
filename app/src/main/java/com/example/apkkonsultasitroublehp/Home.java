package com.example.apkkonsultasitroublehp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Home extends AppCompatActivity {
    Button btnHome,btnTrouble,btnAbout;
    LinearLayout InputTrouble, RiwayatTrouble;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setContentView(R.layout.activity_home);
        btnHome = findViewById(R.id.btnHome);
        btnTrouble = findViewById(R.id.btnTrouble);
        btnAbout = findViewById(R.id.btnAbout);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Home.class));
            }
        });
        btnTrouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Trouble.class));
            }
        });
        InputTrouble = findViewById(R.id.InputTrouble);
        InputTrouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu1 = new Intent(Home.this,InputTrouble.class);
                startActivity(menu1);
                Toast.makeText(getApplicationContext(), "Input Trouble", Toast.LENGTH_SHORT).show();
            }
        });
        RiwayatTrouble = findViewById(R.id.RiwayatTrouble);
        RiwayatTrouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu1 = new Intent(Home.this,RiwayatTrouble.class);
                startActivity(menu1);
                Toast.makeText(getApplicationContext(), "Menuju Menu Pesanan", Toast.LENGTH_SHORT).show();
            }
        });


        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, About.class));
            }
        });
    }}
