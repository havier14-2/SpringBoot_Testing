package com.perfulandiafull.perfulandiafull.restControllers;

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

import com.perfulandiafull.perfulandiafull.entities.Sucursales;
import com.perfulandiafull.perfulandiafull.services.SucursalesServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Sucursales", description = "Operaciones relacionadas con las sucursales")
@RestController
@RequestMapping("/api/sucursales")
public class SucursalesRestController {
     @Autowired
    private SucursalesServices sucursalServices;

    @Operation(summary = "Obtener lista de sucursales", description = "Devuelve todos las sucursales disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de sucursales retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Sucursales.class)))
    @GetMapping
    public List<Sucursales> verSucursales(){
        return (List<Sucursales>) sucursalServices.findByAll();
    }


    @Operation(summary = "Obtener sucursal por ID", description = "Obtiene el detalle de una sucursal específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "sucursal encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursales.class))),
        @ApiResponse(responseCode = "404", description = "sucursal no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<Sucursales> sucursalOptional = sucursalServices.findById(id);
      if (sucursalOptional.isPresent()){
        return ResponseEntity.ok(sucursalOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();
    }


    @Operation(summary = "Crear un nuevo producto", description = "Crea un producto con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Producto creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursales.class)))

    @PostMapping
    public ResponseEntity<Sucursales> crear(@RequestBody Sucursales unaSucursal){
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalServices.save(unaSucursal));
    }

    @Operation(summary = "Modificar Sucursal por ID", description = "Modificar información de una Sucursal")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal modificada",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursales.class))),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarSucursal(@PathVariable Long id, @RequestBody Sucursales unaSucursal){
        Optional<Sucursales> sucursalOptional = sucursalServices.findById(id);
        if (sucursalOptional.isPresent()){
            Sucursales sucursalExiste = sucursalOptional.get();
            sucursalExiste.setNombre(unaSucursal.getNombre());
            sucursalExiste.setDireccion(unaSucursal.getDireccion());
            Sucursales sucursalModificada = sucursalServices.save(sucursalExiste);
            return ResponseEntity.ok(sucursalModificada);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar sucursal por ID", description = "Elimina una sucursal específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "sucursal eliminado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursales.class))),
        @ApiResponse(responseCode = "404", description = "sucursal no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarSucursal(@PathVariable Long id){
        Sucursales unSucursal = new Sucursales();
        unSucursal.setId(id);
        Optional<Sucursales> sucursalOptional = sucursalServices.delete(unSucursal);
        if (sucursalOptional.isPresent()){
            return ResponseEntity.ok(sucursalOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
