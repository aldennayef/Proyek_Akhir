package com.example.proyekakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyekakhir.database.AppDatabase;

public class CreateNote extends AppCompatActivity {
    private EditText editJudul, editPembeli,editTanggal,editDeskripsi,editHarga;
    private Button btnAddData,btnKembali;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        editJudul = findViewById(R.id.edit_judul);
        editPembeli = findViewById(R.id.edit_pembeli);
        editTanggal = findViewById(R.id.edit_tanggal);
        editDeskripsi = findViewById(R.id.edit_deskripsi);
        editHarga = findViewById(R.id.edit_harga);
        btnAddData = findViewById(R.id.btn_addData);
        btnKembali = findViewById(R.id.btn_kembali);

        database = AppDatabase.getInstance(getApplicationContext());

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.trxDao().insertAll(editJudul.getText().toString(),editPembeli.getText().toString(),editTanggal.getText().toString(),editDeskripsi.getText().toString(),editHarga.getText().toString());
                finish();
            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(CreateNote.this,NoteActivity.class);
                startActivity(kembali);
            }
        });
    }
}