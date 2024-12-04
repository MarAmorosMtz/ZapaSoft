package com.mycompany.zapasoft;

public class detalleCompra {

    private Short numc;
    private Float totalDeCompra;
    private Float precio;
    private Float costo;
    private String metodoDePago;
    private Short cantidad;
    private Short idProductos;
    private Short folioC;

    public detalleCompra(Short numc, Float totalDeCompra, Float precio, Float costo, String metodoDePago, Short cantidad, Short idProductos, Short folioC) {
        this.numc = numc;
        this.totalDeCompra = totalDeCompra;
        this.precio = precio;
        this.costo = costo;
        this.metodoDePago = metodoDePago;
        this.cantidad = cantidad;
        this.idProductos = idProductos;
        this.folioC = folioC;
    }

    // Getters y setters
    public Short getNumc() {
        return numc;
    }

    public void setNumc(Short numc) {
        this.numc = numc;
    }

    public Float getTotalDeCompra() {
        return totalDeCompra;
    }

    public void setTotalDeCompra(Float totalDeCompra) {
        this.totalDeCompra = totalDeCompra;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public String getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public Short getCantidad() {
        return cantidad;
    }

    public void setCantidad(Short cantidad) {
        this.cantidad = cantidad;
    }

    public Short getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(Short idProductos) {
        this.idProductos = idProductos;
    }

    public Short getFolioC() {
        return folioC;
    }

    public void setFolioC(Short folioC) {
        this.folioC = folioC;
    }
}
