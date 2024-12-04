package com.mycompany.zapasoft;

public class detalleVenta {
    private short numv;
    private Short idProductos;
    private float totalDeVenta;
    private float precio;
    private float costo;
    private char metodoDePago;
    private Short cantidad;
    private Short folioV;

    // Constructor
    public detalleVenta(short numv, Short idProductos, float totalDeVenta, float precio, float costo, char metodoDePago, Short cantidad, Short folioV) {
        this.numv = numv;
        this.idProductos = idProductos;
        this.totalDeVenta = totalDeVenta;
        this.precio = precio;
        this.costo = costo;
        this.metodoDePago = metodoDePago;
        this.cantidad = cantidad;
        this.folioV = folioV;
    }

    // Getters y Setters
    public short getNumv() {
        return numv;
    }

    public void setNumv(short numv) {
        this.numv = numv;
    }

    public Short getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(Short idProductos) {
        this.idProductos = idProductos;
    }

    public float getTotalDeVenta() {
        return totalDeVenta;
    }

    public void setTotalDeVenta(float totalDeVenta) {
        this.totalDeVenta = totalDeVenta;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public char getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(char metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public Short getCantidad() {
        return cantidad;
    }

    public void setCantidad(Short cantidad) {
        this.cantidad = cantidad;
    }

    public Short getFolioV() {
        return folioV;
    }

    public void setFolioV(Short folioV) {
        this.folioV = folioV;
    }
}

