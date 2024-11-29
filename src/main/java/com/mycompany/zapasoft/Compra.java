package com.mycompany.zapasoft;

import java.sql.Date;

public class Compra {
    private int folio, empleado, proveedor;
    private Date fecha;
    
    public Compra(){}
    
    // Constructor
    public Compra(int folio, int empleado, int proveedor, Date fecha) {
        this.folio = folio;
        this.empleado = empleado;
        this.proveedor = proveedor;
        this.fecha = fecha;
    }

    // Métodos set
    public void setFolio(int folio) {
        this.folio = folio;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // Métodos get
    public int getFolio() {
        return folio;
    }

    public int getEmpleado() {
        return empleado;
    }

    public int getProveedor() {
        return proveedor;
    }

    public Date getFecha() {
        return fecha;
    }
}
