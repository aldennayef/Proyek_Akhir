package com.example.proyekakhir;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyekakhir.adapter.TrxAdapter;
import com.example.proyekakhir.database.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaParser;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.proyekakhir.database.entitas.transaksi;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnAddNote,btnHome;
    private AppDatabase database;
    private TrxAdapter trxAdapter;
    private List<transaksi> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        btnAddNote = findViewById(R.id.btn_addNote);
        btnHome = findViewById(R.id.btn_home);

        recyclerView = findViewById(R.id.recycler_view);

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.trxDao().getAll());
        trxAdapter = new TrxAdapter(getApplicationContext(),list);
        trxAdapter.setDialog(new TrxAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit","Hapus"};
                dialog = new AlertDialog.Builder(NoteActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(NoteActivity.this,UpdateNote.class);
                                intent.putExtra("no",list.get(position).no);
                                startActivity(intent);
                                break;
                            case 1:
                                transaksi transaksi = list.get(position);
                                database.trxDao().delete(transaksi);
                                onStart();  //untuk mengclearkan data
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(trxAdapter);

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteActivity.this,CreateNote.class));
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.trxDao().getAll());
        trxAdapter.notifyDataSetChanged();
    }
}