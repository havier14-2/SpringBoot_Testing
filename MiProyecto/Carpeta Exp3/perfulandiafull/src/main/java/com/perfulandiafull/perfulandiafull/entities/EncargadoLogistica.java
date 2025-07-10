package com.perfulandiafull.perfulandiafull.entities;

import jakarta.persistence.Entity;

@Entity
public class EncargadoLogistica extends Usuarios {
      
    public EncargadoLogistica() {
    }

    public EncargadoLogistica(Long id, String nombre, String apellido, String email) {
        super(id, nombre, apellido, email);
    }
    
    

}
