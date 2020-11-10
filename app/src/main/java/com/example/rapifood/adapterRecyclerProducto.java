package com.example.rapifood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rapifood.Models.producto;

import java.util.ArrayList;

public class adapterRecyclerProducto extends RecyclerView.Adapter<adapterRecyclerProducto.ViewHolderDatos> {

    ArrayList<producto> productoList;
    ArrayList<producto> carroList;
    TextView cantProduct;

    public adapterRecyclerProducto(ArrayList<producto> listProducto) {
        this.productoList = listProducto;
        this.carroList = carroList;
        this.cantProduct = cantProduct;
    }


    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personalizado_productos,null, false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        Glide.with(holder.foto.getContext()).load(productoList.get(position).getImagen()).into(holder.foto);
        holder.producto.setText(productoList.get(position).getNombre());
        holder.peso.setText(productoList.get(position).getPeso());
        holder.precio.setText(productoList.get(position).getPrecio());
        holder.marca.setText(productoList.get(position).getMarca());


        holder.alCarrito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(holder.alCarrito.isChecked() == true){

                    cantProduct.setText(Integer.parseInt((cantProduct.getText().toString().trim()))+1);
                    carroList.add(productoList.get(position));

                }else if(holder.alCarrito.isChecked() == false){

                    cantProduct.setText(Integer.parseInt((cantProduct.getText().toString().trim()))+1);
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        ImageView foto;
        TextView producto, peso, precio, marca;
        CheckBox alCarrito;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            foto = (ImageView) itemView.findViewById(R.id.idPhoto);
            producto = (TextView) itemView.findViewById(R.id.prducto);
            peso = (TextView) itemView.findViewById(R.id.peso);
             precio= (TextView) itemView.findViewById(R.id.precio);
            marca = (TextView) itemView.findViewById(R.id.marca);
            alCarrito = (CheckBox) itemView.findViewById(R.id.checkProduct);
        }
    }

    public void filtrar(ArrayList<producto> filtroProducto){
        this.productoList = filtroProducto;
        notifyDataSetChanged();
    }

}
