package com.inventario.inventario.restcontrollers;

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

import com.inventario.inventario.entities.Inventario;
import com.inventario.inventario.services.InventarioServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Inventario", description = "Operaciones relacionadas con el inventario")
@RestController
@RequestMapping("/api/inventario")
public class InventarioRestController {
    @Autowired
private InventarioServices inventarioServices;

@Operation(summary = "Obtener lista de productos", description = "Devuelve todos los productos disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de productos retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Inventario.class)))
@GetMapping
public List<Inventario> verInventarios(){
    return (List<Inventario>) inventarioServices.findByAll();
}
@Operation(summary = "Obtener producto por ID", description = "Obtiene el detalle de un producto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Inventario.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
@GetMapping("/{id}")
public ResponseEntity<?> verDetalle(@PathVariable Long id){
  Optional<Inventario> inventarioOptional = inventarioServices.findById(id);
  if (inventarioOptional.isPresent()){
    return ResponseEntity.ok(inventarioOptional.orElseThrow());
  }
  return ResponseEntity.notFound().build();
}
@Operation(summary = "Crear un nuevo producto", description = "Crea un producto con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Producto creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Inventario.class)))

@PostMapping
public ResponseEntity<Inventario> crear(@RequestBody Inventario unInventario){
    return ResponseEntity.status(HttpStatus.CREATED).body(inventarioServices.save(unInventario));
}

@Operation(summary = "Modificar producto por ID", description = "Modificar información de un producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto modificado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Inventario.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
@PutMapping("/{id}")
public ResponseEntity<?> modificarInventario(@PathVariable Long id, @RequestBody Inventario unInventario){
    Optional<Inventario> inventarioOptional = inventarioServices.findById(id);
    if (inventarioOptional.isPresent()){
        Inventario inventarioExiste = inventarioOptional.get();
        inventarioExiste.setNombre(unInventario.getNombre());
        
        Inventario inventarioModificado = inventarioServices.save(inventarioExiste);
        return ResponseEntity.ok(inventarioModificado);
    }
    return ResponseEntity.notFound().build();
}

@Operation(summary = "Eliminar producto por ID", description = "Elimina un objeto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Inventario.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
@DeleteMapping("/{id}")
public ResponseEntity<?> eliminarInventario(@PathVariable Long id){
    Inventario unInventario = new Inventario();
    unInventario.setId(id);
    Optional<Inventario> inventarioOptional = inventarioServices.delete(unInventario);
    if (inventarioOptional.isPresent()){
        return ResponseEntity.ok(inventarioOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();

}
}
