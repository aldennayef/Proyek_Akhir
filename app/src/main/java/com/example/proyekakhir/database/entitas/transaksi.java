package com.example.proyekakhir.database.entitas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class transaksi {
    @PrimaryKey     //menjadikan no sebagai primary key
    public int no;

    public String judul;

    public String pembeli;

    public String tanggal;

    public String deskripsi;

    public String harga;

}
