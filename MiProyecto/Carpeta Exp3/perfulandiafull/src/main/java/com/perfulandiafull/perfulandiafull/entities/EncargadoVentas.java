package com.perfulandiafull.perfulandiafull.entities;

import jakarta.persistence.Entity;

@Entity
public class EncargadoVentas extends Usuarios {
      
    public EncargadoVentas() {
    }

    public EncargadoVentas(Long id, String nombre, String apellido, String email) {
        super(id, nombre, apellido, email);
    }
    
   




  
        
}
