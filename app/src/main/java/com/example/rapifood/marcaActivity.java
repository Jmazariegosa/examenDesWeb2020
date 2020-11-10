package com.example.rapifood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rapifood.Models.ConexionSQLHelper;
import com.example.rapifood.Utilidades.Utilidades;

public class marcaActivity extends AppCompatActivity {

    EditText campo_marca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca);

        campo_marca = (EditText) findViewById(R.id.etxt_marca);
        Button boton = (Button) findViewById(R.id.btn1);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarmMrca();
                limpiarCajas();
            }
        });

    }

    private void registrarmMrca() {

    ConexionSQLHelper conn = new ConexionSQLHelper(this,"bd_alumno", null,1);
    SQLiteDatabase db = conn.getWritableDatabase();

    ContentValues values = new ContentValues();


        values.put(Utilidades.MARCA,campo_marca.getText().toString());

    Long idResultante = db.insert(Utilidades.TABLA_MARCA, Utilidades.ID_MARCA, values);

        Toast.makeText(getApplicationContext(), "Registrado Exitosamente -  ID: " +idResultante, Toast.LENGTH_SHORT).show();

}

    private void limpiarCajas() {
        campo_marca.setText("");
    }



}