package com.example.rapifood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rapifood.Models.marca;

import java.util.ArrayList;

public class adapterRecyclerMarca extends RecyclerView.Adapter<adapterRecyclerMarca.ViewHolderDatos> {

    ArrayList<marca> listMarca;

    public adapterRecyclerMarca(ArrayList<marca>listaMarca){

        this.listMarca = listaMarca;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personalizado_marca,null, false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        // holder.idGrado.setText(listGrado.get(position).getId_grado().toString());
        holder.marca.setText(listMarca.get(position).getMarca());


    }

    @Override
    public int getItemCount() {

        return listMarca.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView  marca;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            marca = (TextView) itemView.findViewById(R.id.id_marcatxt);
        }

    }

    public void filtrar(ArrayList<marca> filtroMarca){
        this.listMarca = filtroMarca;
        notifyDataSetChanged();
    }


}
