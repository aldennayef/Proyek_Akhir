package com.example.proyekakhir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UpdateProfil extends AppCompatActivity {

    private EditText edtPassbaru,edtPasslama;
    private Button btnGantipass;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profil);

        edtPasslama = findViewById(R.id.edt_pass_lama);
        edtPassbaru = findViewById(R.id.edt_pass_baru);

        btnGantipass = findViewById(R.id.btn_ganti_pass);

        mAuth = FirebaseAuth.getInstance();

         user = mAuth.getCurrentUser();

        progressDialog = new ProgressDialog(UpdateProfil.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silakan tunggu !");
        progressDialog.setCancelable(false);

        btnGantipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final EditText updatePassword = new EditText(view.getContext());

                String newPassword = edtPassbaru.getText().toString();
                user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(),"Password Berhasil Diganti !",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Password Gagal Diganti !",Toast.LENGTH_SHORT);
                    }
                });
            }
        });
    }

}