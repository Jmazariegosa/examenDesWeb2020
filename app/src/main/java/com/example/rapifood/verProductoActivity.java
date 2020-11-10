package com.example.rapifood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rapifood.Models.ConexionSQLHelper;
import com.example.rapifood.Models.producto;
import com.example.rapifood.Utilidades.Utilidades;

import java.util.ArrayList;

public class verProductoActivity extends AppCompatActivity {
    EditText buscarInscitos;
    TextView cantProduct;
    ArrayList<producto> productoList;
    CheckBox alCarrito;
    ArrayList<producto> listProducto;
    RecyclerView recycler;

    ConexionSQLHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_producto);




        conn = new ConexionSQLHelper(getApplicationContext(), "bd_alumno", null, 1);

        cantProduct = (TextView) findViewById(R.id.cantidadProduct);
        alCarrito = (CheckBox) findViewById(R.id.checkProduct);

        buscarInscitos = (EditText) findViewById(R.id.etxt_buscar_Producto);
        buscarInscitos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filtrar(s.toString());


            }
        });





        listProducto = new ArrayList<>();

        recycler = (RecyclerView) findViewById(R.id.recyclerProducto);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        consultarListaProducto();

        adapterRecyclerProducto adapter = new adapterRecyclerProducto(listProducto);


        recycler.setAdapter(adapter);

    }

    public void filtrar(String texto){

        ArrayList<producto> filtrarLista = new ArrayList<>();

        for (producto product : listProducto){
            if (product.getNombre().toLowerCase().contains(texto.toLowerCase())||
                    product.getPeso().toLowerCase().contains(texto.toLowerCase())||
                    product.getPrecio().toLowerCase().contains(texto.toLowerCase())||
                    product.getMarca().toLowerCase().contains(texto.toLowerCase())){
                filtrarLista.add(product);
            }
        }
        adapterRecyclerProducto adaptador = new adapterRecyclerProducto(listProducto);
        recycler.setAdapter(adaptador);
        adaptador.filtrar(filtrarLista);
    }

    private void consultarListaProducto() {

        SQLiteDatabase db = conn.getReadableDatabase();


        producto product = null;

        String q = " Q. ";

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_PRODUCTO, null);

        while (cursor.moveToNext()){

            product = new producto();
            product.setNombre(cursor.getString(1));
            product.setPeso("Q. " + cursor.getString(2));
            product.setPrecio(cursor.getString(3));
            product.setMarca(cursor.getString(4));
            byte[] imgBytes = product.setImagen(cursor.getBlob(5)); ;
            BitmapFactory.decodeByteArray(imgBytes,0,imgBytes.length);

            listProducto.add(product);
        }

    }

}