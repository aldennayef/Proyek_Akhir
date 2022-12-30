package com.example.proyekakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView textName;
    private FirebaseUser firebaseUser;
    private Button btnLogout,btnEditProfil,btnCatatTrx;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textName = findViewById(R.id.name);
        btnLogout = findViewById(R.id.btn_Logout);
        btnCatatTrx = findViewById(R.id.btn_catattrx);
        btnEditProfil = findViewById(R.id.btn_editprofil);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser.getDisplayName()!=null){
            textName.setText(firebaseUser.getDisplayName());
        }else{
            textName.setText("Login Gagal!");
        }

        btnLogout.setOnClickListener(v ->{
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });

        btnCatatTrx.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(),NoteActivity.class));
        });

        btnEditProfil.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),UpdateProfil.class));
        });
    }
}