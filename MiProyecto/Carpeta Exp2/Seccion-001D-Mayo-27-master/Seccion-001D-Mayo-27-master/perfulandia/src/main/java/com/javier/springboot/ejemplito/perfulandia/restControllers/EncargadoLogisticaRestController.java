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
import com.javier.springboot.ejemplito.perfulandia.entities.EncargadoLogistica;
import com.javier.springboot.ejemplito.perfulandia.services.EncargadoLogisticaServices;

@RestController
@RequestMapping("/encargados-logistica")
public class EncargadoLogisticaRestController {
    @Autowired
    private EncargadoLogisticaServices encargadoLogisticaServices;

    @GetMapping
    public List<EncargadoLogistica> verProductos(){
        return (List<EncargadoLogistica>) encargadoLogisticaServices.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<EncargadoLogistica> elogisticaOptional = encargadoLogisticaServices.findById(id);
      if (elogisticaOptional.isPresent()){
        return ResponseEntity.ok(elogisticaOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<EncargadoLogistica> crear(@RequestBody EncargadoLogistica unEncargadoL){
        return ResponseEntity.status(HttpStatus.CREATED).body(encargadoLogisticaServices.save(unEncargadoL));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarEncargado(@PathVariable Long id, @RequestBody EncargadoLogistica unEncargadoL){
        Optional <EncargadoLogistica> elogisticaOptional = encargadoLogisticaServices.findById(id);
        if (elogisticaOptional.isPresent()){
            EncargadoLogistica encargadolExiste = elogisticaOptional.get();
            encargadolExiste.setNombre(unEncargadoL.getNombre());
            encargadolExiste.setApellido(unEncargadoL.getApellido());
            encargadolExiste.setDireccion(unEncargadoL.getDireccion());
            encargadolExiste.setComuna(unEncargadoL.getComuna());
            encargadolExiste.setEmail(unEncargadoL.getEmail());
            encargadolExiste.setRut(unEncargadoL.getRut());
            encargadolExiste.setTelefono(unEncargadoL.getTelefono());
            
            EncargadoLogistica encargadolModificado = encargadoLogisticaServices.save(encargadolExiste);
            return ResponseEntity.ok(encargadolModificado);

        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        EncargadoLogistica unEncargadoLogistica = new EncargadoLogistica();
        unEncargadoLogistica.setId(id);
        Optional<EncargadoLogistica> encargadoLogisticaOptional =encargadoLogisticaServices.delete(unEncargadoLogistica);
        if (encargadoLogisticaOptional.isPresent()){
            return ResponseEntity.ok(encargadoLogisticaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }



}
