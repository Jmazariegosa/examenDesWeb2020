package com.example.rapifood.Utilidades;

public class Utilidades {

    //TABLA PRODUCTO
    public static String TABLA_PRODUCTO= "producto";
    public static String ID_PRDUCTO = "id";
    public static String PRODUCTO = "producto";
    public static String PESO = "peso";
    public static String PRECIO = "precio";
    public static String MARCAPRODUCTO = "marca";
    public static String FOTO = "foto";

    public static final String CREAR_TABLA_PRODUCTO = " CREATE TABLE "+ TABLA_PRODUCTO +
            " ( "+ ID_PRDUCTO +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            PRODUCTO +" TEXT, "+
            PESO +" TEXT, "+
            PRECIO+" INTEGER, "+
            MARCAPRODUCTO+" TEXT, "+
            FOTO+" BLOB NOT NULL ) ";


    //TABLA MARCA
    public static String TABLA_MARCA = "marca";
    public static String ID_MARCA = "id_marca";
    public static String MARCA = "marca";

    public static final String CREAR_TABLA_MARCA = " CREATE TABLE "+ TABLA_MARCA +
            " ( "+ ID_MARCA +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            MARCA +" TEXT) ";


}
