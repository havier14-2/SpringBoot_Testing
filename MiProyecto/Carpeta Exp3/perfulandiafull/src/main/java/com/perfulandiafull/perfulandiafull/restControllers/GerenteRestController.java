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

import com.perfulandiafull.perfulandiafull.entities.Gerente;
import com.perfulandiafull.perfulandiafull.services.GerenteServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Gerente", description = "Operaciones relacionadas con los gerentes")
@RestController
@RequestMapping("/api/gerentes")
public class GerenteRestController {
    @Autowired
    private GerenteServices gerenteservices;

    @Operation(summary = "Obtener lista de Gerentes", description = "Devuelve todos los Gerentes disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de Gerentes retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Gerente.class)))
    @GetMapping
    public List<Gerente> verGerentes(){
        return (List<Gerente>) gerenteservices.findByAll();
    }

    @Operation(summary = "Obtener Gerente por ID", description = "Obtiene el detalle de un Gerente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gerente encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gerente.class))),
        @ApiResponse(responseCode = "404", description = "Gerente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<Gerente> gerenteOptional = gerenteservices.findById(id);
      if (gerenteOptional.isPresent()){
        return ResponseEntity.ok(gerenteOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Crear un nuevo Gerente", description = "Crea un Gerente con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Gerente creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gerente.class)))

    @PostMapping
    public ResponseEntity<Gerente> crear(@RequestBody Gerente unGerente){
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenteservices.save(unGerente));
    }

    @Operation(summary = "Modificar Gerente por ID", description = "Modificar información de un Gerente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gerente modificado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gerente.class))),
        @ApiResponse(responseCode = "404", description = "Gerente no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarGerente(@PathVariable Long id, @RequestBody Gerente unGerente){
        Optional <Gerente> gerenteOptional = gerenteservices.findById(id);
        if (gerenteOptional.isPresent()){
            Gerente gerenteExiste = gerenteOptional.get();
           gerenteExiste.setNombre(unGerente.getNombre());
            gerenteExiste.setApellido(unGerente.getApellido());
            gerenteExiste.setEmail(unGerente.getEmail());
            
            
            Gerente clienteModificado = gerenteservices.save(gerenteExiste);
            return ResponseEntity.ok(clienteModificado);

        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar Gerente por ID", description = "Elimina un Gerente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gerente eliminado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gerente.class))),
        @ApiResponse(responseCode = "404", description = "Gerente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarGerente(@PathVariable Long id){
        Gerente unGerente = new Gerente();
        unGerente.setId(id);
        Optional<Gerente> gerenteOptional = gerenteservices.delete(unGerente);
        if (gerenteOptional.isPresent()){
            return ResponseEntity.ok(gerenteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
