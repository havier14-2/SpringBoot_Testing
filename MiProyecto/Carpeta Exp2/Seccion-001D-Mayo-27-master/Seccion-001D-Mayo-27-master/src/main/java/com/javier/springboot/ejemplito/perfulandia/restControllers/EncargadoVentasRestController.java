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
import com.javier.springboot.ejemplito.perfulandia.entities.EncargadoVentas;
import com.javier.springboot.ejemplito.perfulandia.services.EncargadoVentasServices;
@RestController
@RequestMapping("/encargados-ventas")
public class EncargadoVentasRestController {
    @Autowired
    private EncargadoVentasServices encargadoVentasServices;

    @GetMapping
    public List<EncargadoVentas> verProductos(){
        return (List<EncargadoVentas>) encargadoVentasServices.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<EncargadoVentas> EncargadoVentasOptional = encargadoVentasServices.findById(id);
      if (EncargadoVentasOptional.isPresent()){
        return ResponseEntity.ok(EncargadoVentasOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<EncargadoVentas> crear(@RequestBody EncargadoVentas unEncargadoV){
        return ResponseEntity.status(HttpStatus.CREATED).body(encargadoVentasServices.save(unEncargadoV));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarEncargado(@PathVariable Long id, @RequestBody EncargadoVentas unEncargadoV){
        Optional <EncargadoVentas> elogisticaOptional = encargadoVentasServices.findById(id);
        if (elogisticaOptional.isPresent()){
            EncargadoVentas encargadovExiste = elogisticaOptional.get();
            encargadovExiste.setNombre(unEncargadoV.getNombre());
            encargadovExiste.setApellido(unEncargadoV.getApellido());
            encargadovExiste.setDireccion(unEncargadoV.getDireccion());
            encargadovExiste.setComuna(unEncargadoV.getComuna());
            encargadovExiste.setEmail(unEncargadoV.getEmail());
            encargadovExiste.setRut(unEncargadoV.getRut());
            encargadovExiste.setTelefono(unEncargadoV.getTelefono());
            
            EncargadoVentas encargadolModificado = encargadoVentasServices.save(encargadovExiste);
            return ResponseEntity.ok(encargadolModificado);

        }
        return ResponseEntity.notFound().build();
        
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        EncargadoVentas unEncargadoVentas = new EncargadoVentas();
        unEncargadoVentas.setId(id);
        Optional<EncargadoVentas> encargadoVentasOptional =encargadoVentasServices.delete(unEncargadoVentas);
        if (encargadoVentasOptional.isPresent()){
            return ResponseEntity.ok(encargadoVentasOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


}
