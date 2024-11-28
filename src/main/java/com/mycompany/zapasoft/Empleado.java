package com.mycompany.zapasoft;

import java.sql.Time;

public class Empleado {
    
    private int id, contraseña;
    private String nombre, telefono, apellido, correo, puesto;
    private Time horarioe, horarios;
    
    public Empleado(){}
    
    public Empleado(int id, int contraseña, String nombre, String telefono, String apellido, String correo, String puesto, Time horarioe, Time horarios){
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.apellido = apellido;
        this.correo = correo;
        this.puesto = puesto;
        this.horarioe = horarioe;
        this.horarios = horarios;
        this.contraseña = contraseña;
    }
    
    public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public String getTelefono() {
    return telefono;
}

public void setTelefono(String telefono) {
    this.telefono = telefono;
}

public String getApellido() {
    return apellido;
}

public void setApellido(String apellido) {
    this.apellido = apellido;
}

public String getCorreo() {
    return correo;
}

public void setCorreo(String correo) {
    this.correo = correo;
}

public String getPuesto() {
    return puesto;
}

public void setPuesto(String puesto) {
    this.puesto = puesto;
}

public Time getHorarioe() {
    return horarioe;
}

public void setHorarioe(Time horarioe) {
    this.horarioe = horarioe;
}

public Time getHorarios() {
    return horarios;
}

public void setHorarios(Time horarios) {
    this.horarios = horarios;
}

public void setContraseña(int contraseña){
    this.contraseña = contraseña;
}

public int getContraseña(){
    return contraseña;
}

    
}
