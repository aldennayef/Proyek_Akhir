package com.example.proyekakhir.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyekakhir.R;

import com.example.proyekakhir.database.entitas.transaksi;

import java.util.List;

public class TrxAdapter extends RecyclerView.Adapter<TrxAdapter.ViewAdapter>{
    private List<transaksi> list;
    private Context context;
    private  Dialog dialog;

    public interface Dialog{
        void onClick(int position);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public TrxAdapter(Context context, List<transaksi> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_note,parent,false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.judulNote.setText(list.get(position).judul);
        holder.pembeliNote.setText(list.get(position).pembeli);
        holder.tanggalNote.setText(list.get(position).tanggal);
        holder.deskripsiNote.setText(list.get(position).deskripsi);
        holder.hargaNote.setText(list.get(position).harga);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView judulNote, pembeliNote,tanggalNote,deskripsiNote,hargaNote;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            judulNote = itemView.findViewById(R.id.judul_note);
            pembeliNote = itemView.findViewById(R.id.pembeli_note);
            tanggalNote = itemView.findViewById(R.id.tanggal_note);
            deskripsiNote = itemView.findViewById(R.id.deskripsi_note);
            hargaNote = itemView.findViewById(R.id.harga_note);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
