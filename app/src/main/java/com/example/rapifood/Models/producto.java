package com.example.rapifood.Models;

import java.io.Serializable;

public class producto implements Serializable {

private Integer idProduct;
private String nombre;
private String peso;
private String precio;
private String marca;
private byte[] imagen;

    public producto() {
        this.idProduct = idProduct;
        this.nombre = nombre;
        this.peso = peso;
        this.precio = precio;
        this.marca = marca;
        this.imagen = imagen;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public byte[] setImagen(byte[] imagen) {
        this.imagen = imagen;
        return imagen;
    }
}
