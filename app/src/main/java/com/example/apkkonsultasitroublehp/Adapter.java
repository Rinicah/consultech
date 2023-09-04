package com.example.apkkonsultasitroublehp;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apkkonsultasitroublehp.ModelKeluhan;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<ModelKeluhan> mList;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public Adapter(List<ModelKeluhan>mList, Activity activity){
        this.mList = mList;
        this.activity = activity;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.layout_item, parent,false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModelKeluhan data = mList.get(position);
        holder.tv_nama.setText("Nama : " + data.getNama());
        holder.tv_alamat.setText("Alamat : " + data.getAlamat());
        holder.tv_keluhan.setText("Keluhan : " + data.getKeluhan());
        holder.tv_tanggal.setText("Tanggal : " + data.getAlamat());
        holder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.child("Trouble").child(data.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(activity, "Data Berhasil Dihapus!", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "Gagal Menghapus Data!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setMessage("Apakah yakin ingin dihapus? " + data.getNama());
                builder.show();
            }
        });
        holder.card_hasil.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FragmentManager manager = ((AppCompatActivity) activity).getSupportFragmentManager();
                DialogFrom dialog = new DialogFrom(
                        data.getNama(),
                        data.getAlamat(),
                        data.getKeluhan(),
                        data.getTanggal(),
                        data.getKey(),
                        "Update"
                );
                dialog.show(manager, "form");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView tv_nama,  tv_alamat, tv_keluhan, tv_tanggal;
        ImageView btn_hapus;
        CardView card_hasil;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_alamat = itemView.findViewById(R.id.tv_alamat);
            tv_keluhan = itemView.findViewById(R.id.tv_keluhan);
            tv_tanggal = itemView.findViewById(R.id.tv_tanggal);
            btn_hapus = itemView.findViewById(R.id.btn_hapus);
            card_hasil = itemView.findViewById(R.id.card_hasil);

        }
        }
    }
