package com.perfulandiafull.perfulandiafull.entities;

import jakarta.persistence.Entity;


@Entity
public class Administrador extends Usuarios{
      
    public Administrador() {
    }

    public Administrador(Long id, String nombre, String apellido, String email) {
        super(id, nombre, apellido, email);
    }
    
    




}
