package com.perfulandiafull.perfulandiafull.entities;

import jakarta.persistence.Entity;


@Entity
public class Cliente extends Usuarios {
      
    public Cliente() {
    }

    public Cliente(Long id, String nombre, String apellido, String email) {
        super(id, nombre, apellido, email);
    }
    
    

}
