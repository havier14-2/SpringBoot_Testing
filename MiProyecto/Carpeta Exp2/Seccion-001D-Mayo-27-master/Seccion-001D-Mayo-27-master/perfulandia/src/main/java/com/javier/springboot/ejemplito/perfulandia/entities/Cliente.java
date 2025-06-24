package com.javier.springboot.ejemplito.perfulandia.entities;

import jakarta.persistence.Entity;

@Entity
public class Cliente extends Usuario {
    
    private String direccion;
    private String comuna;
    private String telefono;
    private String email;
    private String password;

    public Cliente(String rut, String nombre, String apellido, String direccion, String comuna, String telefono, String email, String password) {
        super(rut, nombre, apellido, direccion, comuna, telefono, email, password);
        this.direccion = direccion;
        this.comuna = comuna;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }
    public Cliente() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
