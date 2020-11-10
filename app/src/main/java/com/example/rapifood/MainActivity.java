package com.example.rapifood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rapifood.Models.ConexionSQLHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConexionSQLHelper conn = new ConexionSQLHelper(this,"bd_alumno", null,1);
    }


    public void onClick(View view){

        Intent miIntent= null;

        switch (view.getId()){
            case R.id.lin1:
                miIntent= new Intent(MainActivity.this, nuevoProductoActivity.class);
                break;
            case R.id.lin2:
                miIntent= new Intent(MainActivity.this, marcaActivity.class);
                break;
            case R.id.lin3:
                miIntent= new Intent(MainActivity.this, verProductoActivity.class);
                break;
            case R.id.lin4:
                miIntent= new Intent(MainActivity.this, verMarcaActivity.class);
                break;


        }startActivity(miIntent);
    }



}