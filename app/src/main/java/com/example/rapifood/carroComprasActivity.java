package com.example.rapifood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rapifood.Models.producto;

import java.util.ArrayList;

public class carroComprasActivity extends AppCompatActivity {

    ArrayList<producto> carroCOmpras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro_compras);
    }
}