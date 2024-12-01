package com.mycompany.zapasoft;

import java.sql.Date;

public class Venta {
    private int folio, idempleado, numv;
    private Date fecha;
    
    public Venta(){}
    
    public Venta(int folio, int idempleado, int numv, Date fecha) {
        this.folio = folio;
        this.idempleado = idempleado;
        this.numv = numv;
        this.fecha = fecha;
    }

    // Getters
    public int getFolio() {
        return folio;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public int getNumv() {
        return numv;
    }

    public Date getFecha() {
        return fecha;
    }

    // Setters
    public void setFolio(int folio) {
        this.folio = folio;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public void setNumv(int numv) {
        this.numv = numv;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
