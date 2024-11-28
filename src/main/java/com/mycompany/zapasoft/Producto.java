package com.mycompany.zapasoft;

public class Producto {
    
    private int id, stock;
    private float precio, costo, talla;
    private String nombre;
    
    public Producto(){}
    
    public Producto(int id, int stock, float precio, float costo, float talla, String nombre) {
    this.id = id;
    this.stock = stock;
    this.precio = precio;
    this.costo = costo;
    this.talla = talla;
    this.nombre = nombre;
    }

    // Métodos para id
public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

// Métodos para stock
public int getStock() {
    return stock;
}

public void setStock(int stock) {
    this.stock = stock;
}

// Métodos para precio
public float getPrecio() {
    return precio;
}

public void setPrecio(float precio) {
    this.precio = precio;
}

// Métodos para costo
public float getCosto() {
    return costo;
}

public void setCosto(float costo) {
    this.costo = costo;
}

// Métodos para talla
public float getTalla() {
    return talla;
}

public void setTalla(float talla) {
    this.talla = talla;
}

// Métodos para nombre
public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

    
}
