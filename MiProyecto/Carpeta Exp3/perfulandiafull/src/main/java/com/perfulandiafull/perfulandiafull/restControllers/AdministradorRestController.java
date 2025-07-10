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

import com.perfulandiafull.perfulandiafull.entities.Administrador;
import com.perfulandiafull.perfulandiafull.services.AdministradorServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/administradores")
@Tag(name = "Administrador", description = "Operaciones relacionadas con los Administradores") 

public class AdministradorRestController {
    @Autowired
    private AdministradorServices administradorservices;

    @Operation(summary = "Obtener lista de Administradores", description = "Devuelve todos los Administradores disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de Administradores retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Administrador.class)))
    @GetMapping
    public List<Administrador> verAdministradores(){
        return (List<Administrador>) administradorservices.findByAll();
    }

    @Operation(summary = "Obtener Administrador por ID", description = "Obtiene el detalle de un Administrador específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Administrador.class))),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<Administrador> administradorOptional = administradorservices.findById(id);
      if (administradorOptional.isPresent()){
        return ResponseEntity.ok(administradorOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Crear un nuevo Administrador", description = "Crea un Administrador con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Administrador creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Administrador.class)))

    @PostMapping
    public ResponseEntity<Administrador> crearAdministrador(@RequestBody Administrador unAdministrador){
        return ResponseEntity.status(HttpStatus.CREATED).body(administradorservices.save(unAdministrador));
    }

    @Operation(summary = "Modificar Administrador por ID", description = "Modificar información de un Administrador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador modificado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Administrador.class))),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarAdministrador(@PathVariable Long id, @RequestBody Administrador unAdministrador){
        Optional <Administrador> administradorOptional = administradorservices.findById(id);
        if (administradorOptional.isPresent()){
            Administrador administradorExiste = administradorOptional.get();
            administradorExiste.setNombre(unAdministrador.getNombre());
            administradorExiste.setApellido(unAdministrador.getApellido());
            administradorExiste.setEmail(unAdministrador.getEmail());
          
            
            Administrador administradorModificado = administradorservices.save(administradorExiste);
            return ResponseEntity.ok(administradorModificado);

        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar Administrador por ID", description = "Elimina un Administrador específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador eliminado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Administrador.class))),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
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
