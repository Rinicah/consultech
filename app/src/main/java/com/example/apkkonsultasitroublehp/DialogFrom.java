package com.example.apkkonsultasitroublehp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.apkkonsultasitroublehp.ModelKeluhan;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogFrom extends DialogFragment {
    String nama, alamat, keluhan, tanggal, key, pilih;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogFrom(String nama, String alamat, String keluhan, String tanggal, String key, String pilih){
        this.nama = nama;
        this.alamat = alamat;
        this.keluhan = keluhan;
        this.tanggal = tanggal;
        this.key = key;
        this.pilih = pilih;
    }
    TextView tnama, talamat, tkeluhan, ttanggal;
    Button btn_simpan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_input_trouble, container, false);
        tnama = view.findViewById(R.id.nama);
        talamat = view.findViewById(R.id.alamat);
        tkeluhan = view.findViewById(R.id.keluhan);
        ttanggal = view.findViewById(R.id.tanggal);
        btn_simpan = view.findViewById(R.id.simpan);

        tnama.setText(nama);
        talamat.setText(alamat);
        tkeluhan.setText(keluhan);
        ttanggal.setText(tanggal);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = tnama.getText().toString();
                String alamat = talamat.getText().toString();
                String keluhan = tkeluhan.getText().toString();
                String tanggal = ttanggal.getText().toString();
                if (pilih.equals("Update")){
                    database.child("Trouble").setValue(nama).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(view.getContext(), "Berhasil di Update", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), "Maaf Data Gagal di Update", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
