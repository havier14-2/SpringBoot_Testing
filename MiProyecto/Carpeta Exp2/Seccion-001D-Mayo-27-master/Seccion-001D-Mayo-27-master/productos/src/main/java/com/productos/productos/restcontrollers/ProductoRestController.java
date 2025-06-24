package com.productos.productos.restcontrollers;

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

import com.productos.productos.entities.Producto;
import com.productos.productos.services.ProductoServices;
@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {
     @Autowired
    private ProductoServices productoservices;

    @GetMapping
    public List<Producto> verProductos(){
        return (List<Producto>) productoservices.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<Producto> productoOptional = productoservices.findById(id);
      if (productoOptional.isPresent()){
        return ResponseEntity.ok(productoOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto unProducto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoservices.save(unProducto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarProducto(@PathVariable Long id, @RequestBody Producto unProducto){
        Optional <Producto> productoOptional = productoservices.findById(id);
        if (productoOptional.isPresent()){
            Producto productoExiste = productoOptional.get();
            productoExiste.setNombre(unProducto.getNombre());
            productoExiste.setDescripcion(unProducto.getDescripcion());
            productoExiste.setPrecio(unProducto.getPrecio());
            Producto productoModificado = productoservices.save(productoExiste);
            return ResponseEntity.ok(productoModificado);

        }
        return ResponseEntity.notFound().build();
    }
     @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        Producto unProducto = new Producto();
        unProducto.setId(id);
        Optional<Producto> productoOptional = productoservices.delete(unProducto);
        if (productoOptional.isPresent()){
            return ResponseEntity.ok(productoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }



}
