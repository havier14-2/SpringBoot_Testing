package com.javier.springboot.ejemplito.perfulandia.restControllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javier.springboot.ejemplito.perfulandia.entities.Administrador;
import com.javier.springboot.ejemplito.perfulandia.services.AdministradorServices;
@RestController
@RequestMapping("/administradores")
public class AdministradorRestController {
    @Autowired
    private AdministradorServices administradorservices;

    @GetMapping
    public List<Administrador> verProductos(){
        return (List<Administrador>) administradorservices.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<Administrador> administradorOptional = administradorservices.findById(id);
      if (administradorOptional.isPresent()){
        return ResponseEntity.ok(administradorOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Administrador> crear(@RequestBody Administrador unAdministrador){
        return ResponseEntity.status(HttpStatus.CREATED).body(administradorservices.save(unAdministrador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarAdministrador(@PathVariable Long id, @RequestBody Administrador unAdministrador){
        Optional <Administrador> administradorOptional = administradorservices.findById(id);
        if (administradorOptional.isPresent()){
            Administrador administradorExiste = administradorOptional.get();
            administradorExiste.setNombre(unAdministrador.getNombre());
            administradorExiste.setApellido(unAdministrador.getApellido());
            administradorExiste.setDireccion(unAdministrador.getDireccion());
            administradorExiste.setComuna(unAdministrador.getComuna());
            administradorExiste.setEmail(unAdministrador.getEmail());
            administradorExiste.setRut(unAdministrador.getRut());
            administradorExiste.setTelefono(unAdministrador.getTelefono());
            
            Administrador administradorModificado = administradorservices.save(administradorExiste);
            return ResponseEntity.ok(administradorModificado);

        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        Administrador unAdministrador = new Administrador();
        unAdministrador.setId(id);
        Optional<Administrador> administradorOptional = administradorservices.delete(unAdministrador);
        if (administradorOptional.isPresent()){
            return ResponseEntity.ok(administradorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


}
