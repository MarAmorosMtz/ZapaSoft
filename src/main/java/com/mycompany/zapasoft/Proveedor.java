package com.mycompany.zapasoft;


public class Proveedor {
    
    private Integer id;
    private String nombre, direccion, correo, telefono;
    
    public Proveedor(){}
    
    public Proveedor(String nombre, String direccion, String correo, String telefono){
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
    }
    
    // Métodos get y set para el campo id
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Métodos get y set para el campo nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos get y set para el campo direccion
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Métodos get y set para el campo correo
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Métodos get y set para el campo telefono
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
