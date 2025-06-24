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

import com.javier.springboot.ejemplito.perfulandia.entities.Gerente;
import com.javier.springboot.ejemplito.perfulandia.services.GerenteServices;
@RestController
@RequestMapping("/gerentes")
public class GerenteRestController {
    @Autowired
    private GerenteServices gerenteservices;

    @GetMapping
    public List<Gerente> verProductos(){
        return (List<Gerente>) gerenteservices.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<Gerente> gerenteOptional = gerenteservices.findById(id);
      if (gerenteOptional.isPresent()){
        return ResponseEntity.ok(gerenteOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Gerente> crear(@RequestBody Gerente unGerente){
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenteservices.save(unGerente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarCliente(@PathVariable Long id, @RequestBody Gerente unGerente){
        Optional <Gerente> gerenteOptional = gerenteservices.findById(id);
        if (gerenteOptional.isPresent()){
            Gerente gerenteExiste = gerenteOptional.get();
           gerenteExiste.setNombre(unGerente.getNombre());
            gerenteExiste.setApellido(unGerente.getApellido());
            gerenteExiste.setDireccion(unGerente.getDireccion());
            gerenteExiste.setComuna(unGerente.getComuna());
            gerenteExiste.setEmail(unGerente.getEmail());
            gerenteExiste.setRut(unGerente.getRut());
            gerenteExiste.setTelefono(unGerente.getTelefono());
            
            Gerente clienteModificado = gerenteservices.save(gerenteExiste);
            return ResponseEntity.ok(clienteModificado);

        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        Gerente unGerente = new Gerente();
        unGerente.setId(id);
        Optional<Gerente> gerenteOptional = gerenteservices.delete(unGerente);
        if (gerenteOptional.isPresent()){
            return ResponseEntity.ok(gerenteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


}
