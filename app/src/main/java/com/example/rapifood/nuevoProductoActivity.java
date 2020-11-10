package com.example.rapifood;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rapifood.Models.ConexionSQLHelper;
import com.example.rapifood.Models.marca;
import com.example.rapifood.Utilidades.Utilidades;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class nuevoProductoActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 200;

    Button guardar;
    AutoCompleteTextView dropdown;
    EditText nombre, precio, peso;
    ImageView nuevaImagen;

    ArrayList<String> listaMarca;
    ArrayList<marca> marcaList;

    ConexionSQLHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);


        conn = new ConexionSQLHelper(this,"bd_alumno", null,1);


        guardar = (Button) findViewById(R.id.btn1);

        nuevaImagen = (ImageView) findViewById(R.id.new_memory_selected_image);


        nombre = (EditText) findViewById(R.id.etxt_nombre);
        precio = (EditText) findViewById(R.id.etxt_apellido);
        peso = (EditText) findViewById(R.id.etxt_direccion);
        dropdown = (AutoCompleteTextView) findViewById(R.id.etxt_grado);



        consultarListaMarca();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,  android.R.layout.simple_dropdown_item_1line, listaMarca);

        dropdown.setAdapter(adaptador);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inscribirAlumno();

            }
        });

    }

    public void inscribirAlumno(){

        //aqui se guarda la imagen previa antes de guardar

        ConexionSQLHelper conn = new ConexionSQLHelper(this,"bd_alumno", null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        Bitmap image = ((BitmapDrawable)nuevaImagen.getDrawable()).getBitmap();

        byte[] data = getBitmapAsByteArray(image);

        ContentValues values = new ContentValues();

        values.put(Utilidades.FOTO, data);
        values.put(Utilidades.PRODUCTO,nombre.getText().toString());
        values.put(Utilidades.PESO,peso.getText().toString());
        values.put(Utilidades.PRECIO,precio.getText().toString());
        values.put(Utilidades.MARCAPRODUCTO,dropdown.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_PRODUCTO, Utilidades.ID_PRDUCTO, values);
        Toast.makeText(getApplicationContext(), "Registrado Exitosamente -  ID: " +idResultante, Toast.LENGTH_SHORT).show();

        finish();
    }


    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }


    private void consultarListaMarca() {

        SQLiteDatabase db = conn.getReadableDatabase();
        marca marcA = null;

        marcaList = new ArrayList<marca>();

        Cursor cursor = db.rawQuery( " SELECT * FROM " + Utilidades.TABLA_MARCA,null);

        while (cursor.moveToNext()){

            marcA = new marca();
            marcA.setMarca(cursor.getString(1));

            marcaList.add(marcA);

        }

        obtenerLista();

    }

    private void obtenerLista() {

        listaMarca = new ArrayList<String>();

        for (int i=0; i<marcaList.size(); i++){

            listaMarca.add(marcaList.get(i).getMarca());

        }

    }

    //METODO DE MOSTRAR CAMARA

    public void openCamera(View view){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, CAMERA_REQUEST);
        }

    }

    //mostrar imagen en el activity inscripcion
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap image = (Bitmap)extras.get("data");
            nuevaImagen.setImageBitmap(image);

        }

    }



}