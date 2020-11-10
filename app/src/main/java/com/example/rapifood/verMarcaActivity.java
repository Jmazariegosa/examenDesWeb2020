package com.example.rapifood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.rapifood.Models.ConexionSQLHelper;
import com.example.rapifood.Models.marca;
import com.example.rapifood.Utilidades.Utilidades;

import java.util.ArrayList;

public class verMarcaActivity extends AppCompatActivity {


    EditText buscarMarca;

    ArrayList<marca> listaMarca;
    RecyclerView recycler;

    ConexionSQLHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_marca);

        conn = new ConexionSQLHelper(getApplicationContext(), "bd_alumno", null, 1);


        buscarMarca = (EditText) findViewById(R.id.etxt_buscar_Marca);
        buscarMarca.addTextChangedListener(new TextWatcher() {
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

        listaMarca = new ArrayList<>();

        recycler = (RecyclerView) findViewById(R.id.recyclerMarca);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        consultarListaMarca();

        adapterRecyclerMarca adapter = new adapterRecyclerMarca(listaMarca);
        recycler.setAdapter(adapter);

    }

    public void filtrar(String texto){

        ArrayList<marca> filtrarLista = new ArrayList<>();

        for (marca marcA : listaMarca){
            if (marcA.getMarca().toLowerCase().contains(texto.toLowerCase())){
                filtrarLista.add(marcA);
            }
        }
        adapterRecyclerMarca adaptador = new adapterRecyclerMarca(listaMarca);
        recycler.setAdapter(adaptador);
        adaptador.filtrar(filtrarLista);
    }

    private void consultarListaMarca() {

        SQLiteDatabase db = conn.getReadableDatabase();

        marca mark = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_MARCA, null);

        while (cursor.moveToNext()){

            mark = new marca();
            //grado.setId_grado(cursor.getInt(0));
            mark.setMarca(cursor.getString(1));

            listaMarca.add(mark);
        }

    }

}