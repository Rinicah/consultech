package com.example.apkkonsultasitroublehp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apkkonsultasitroublehp.ModelKeluhan;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputTrouble extends AppCompatActivity {
    EditText nama, alamat, keluhan, tanggal;
    Button simpan, daftar;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_trouble);

        nama = findViewById(R.id.nama);
        alamat = findViewById(R.id.alamat);
        keluhan = findViewById(R.id.keluhan);
        tanggal = findViewById(R.id.tanggal);
        simpan = findViewById(R.id.simpan);
        daftar = findViewById(R.id.daftar);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNama = nama.getText().toString();
                String getAlamat = alamat.getText().toString();
                String getKeluhan = keluhan.getText().toString();
                String getTanggal = tanggal.getText().toString();

                if (getNama.isEmpty()){
                    nama.setError("Masukkan Nama");
                } else if (getAlamat.isEmpty()) {
                    alamat.setError("Alamat masih kosong");
                }else if (getKeluhan.isEmpty()) {
                    alamat.setError("Alamat masih kosong");
                }else if (getTanggal.isEmpty()) {
                    alamat.setError("Alamat masih kosong");
                }else{
                    database.child("Trouble").push().setValue(new ModelKeluhan(getNama, getAlamat, getKeluhan,getTanggal)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(InputTrouble.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(InputTrouble.this, RiwayatTrouble.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            
                        }
                    });
                }
            }
        });

    }
}