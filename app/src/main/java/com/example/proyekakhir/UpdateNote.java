package com.example.proyekakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyekakhir.database.AppDatabase;
import com.example.proyekakhir.database.entitas.transaksi;

public class UpdateNote extends AppCompatActivity {

    private TextView updJudul,updPembeli,updTanggal,updDeskripsi,updHarga;
    private AppDatabase database;
    private Button btnUpdate,btnBack;
    private int no=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        updJudul = findViewById(R.id.upd_judul);
        updPembeli = findViewById(R.id.upd_pembeli);
        updTanggal = findViewById(R.id.upd_tanggal);
        updDeskripsi = findViewById(R.id.upd_deskripsi);
        updHarga = findViewById(R.id.upd_harga);

        btnUpdate = findViewById(R.id.btn_update);
        btnBack = findViewById(R.id.btn_back);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        no = intent.getIntExtra("no",0);
        if(no>0){
            transaksi transaksi = database.trxDao().get(no);
            updJudul.setText(transaksi.judul);
            updPembeli.setText(transaksi.pembeli);
            updTanggal.setText(transaksi.tanggal);
            updDeskripsi.setText(transaksi.deskripsi);
            updHarga.setText(transaksi.harga);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.trxDao().update(no,updJudul.getText().toString(),updPembeli.getText().toString(),updTanggal.getText().toString(),updDeskripsi.getText().toString(),updHarga.getText().toString());
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(UpdateNote.this,NoteActivity.class);
                startActivity(back);
            }
        });

    }
}