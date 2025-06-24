package com.sucursales.sucursales.restcontrollers;

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

import com.sucursales.sucursales.entities.Sucursal;
import com.sucursales.sucursales.sevices.SucursalesServices;
@RestController
@RequestMapping("/api/sucursales")
public class SucursalesRestControllers {
    @Autowired
    private SucursalesServices sucursalServices;

    @GetMapping
    public List<Sucursal> verSucursales(){
        return (List<Sucursal>) sucursalServices.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<Sucursal> sucursalOptional = sucursalServices.findById(id);
      if (sucursalOptional.isPresent()){
        return ResponseEntity.ok(sucursalOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Sucursal> crear(@RequestBody Sucursal unaSucursal){
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalServices.save(unaSucursal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarSucursal(@PathVariable Long id, @RequestBody Sucursal unaSucursal){
        Optional<Sucursal> sucursalOptional = sucursalServices.findById(id);
        if (sucursalOptional.isPresent()){
            Sucursal sucursalExiste = sucursalOptional.get();
            sucursalExiste.setNombre(unaSucursal.getNombre());
            sucursalExiste.setDireccion(unaSucursal.getDireccion());
            Sucursal sucursalModificada = sucursalServices.save(sucursalExiste);
            return ResponseEntity.ok(sucursalModificada);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        Sucursal unSucursal = new Sucursal();
        unSucursal.setId(id);
        Optional<Sucursal> sucursalOptional = sucursalServices.delete(unSucursal);
        if (sucursalOptional.isPresent()){
            return ResponseEntity.ok(sucursalOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
