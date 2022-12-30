package com.example.proyekakhir.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.proyekakhir.database.entitas.transaksi;

import java.util.List;

@Dao
public interface TrxDao {
    @Query("SELECT * FROM transaksi")
    List<transaksi> getAll();

    @Query("INSERT INTO transaksi (judul,pembeli,tanggal,deskripsi,harga) VALUES(:judul,:pembeli,:tanggal,:deskripsi,:harga)")
    void insertAll(String judul, String pembeli,String tanggal, String deskripsi,String harga);

    @Query("UPDATE transaksi SET judul=:judul,pembeli=:pembeli,tanggal=:tanggal,deskripsi=:deskripsi,harga=:harga WHERE `no`=:no ")
    void update(int no, String judul, String pembeli,String tanggal, String deskripsi,String harga);

    @Query("SELECT * FROM transaksi WHERE `no`=:no")
    transaksi get(int no);

    @Delete
    void delete(transaksi transaksi);
}
