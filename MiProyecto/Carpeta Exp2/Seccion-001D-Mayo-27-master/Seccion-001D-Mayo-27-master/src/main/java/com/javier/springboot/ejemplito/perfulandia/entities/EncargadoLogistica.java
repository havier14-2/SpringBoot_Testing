package com.javier.springboot.ejemplito.perfulandia.entities;

import jakarta.persistence.Entity;

@Entity
public class EncargadoLogistica extends Usuario{

    private String rut;
    private String nombre;
    private String apellido;
    private String direccion;
    private String comuna;
    private String telefono;
    private String email;
    private String password;

    public EncargadoLogistica(String rut, String nombre, String apellido, String direccion, String comuna, String telefono, String email, String password) {
        super(rut, nombre, apellido, direccion, comuna, telefono, email, password);
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.comuna = comuna;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }
    public EncargadoLogistica() {
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
