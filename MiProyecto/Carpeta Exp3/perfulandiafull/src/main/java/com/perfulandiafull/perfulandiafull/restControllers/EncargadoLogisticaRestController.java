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

import com.perfulandiafull.perfulandiafull.entities.EncargadoLogistica;
import com.perfulandiafull.perfulandiafull.services.EncargadoLogisticaServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Encargado de Logistica", description = "Operaciones relacionadas con los Encargados de Logistica")

@RestController
@RequestMapping("/api/encargados-logistica")
public class EncargadoLogisticaRestController {
     @Autowired
    private EncargadoLogisticaServices encargadoLogisticaServices;

@Operation(summary = "Obtener lista de Encargados de Logistica", description = "Devuelve todos los Encargados de Logistica disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de Encargados de Logistica retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = EncargadoLogistica.class)))
    @GetMapping
    public List<EncargadoLogistica> verEncargado(){
        return (List<EncargadoLogistica>) encargadoLogisticaServices.findByAll();
    }

@Operation(summary = "Obtener Encargado de Logistica por ID", description = "Obtiene el detalle de un Encargado de Logistica específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Encargado de Logistica encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = EncargadoLogistica.class))),
        @ApiResponse(responseCode = "404", description = "Encargado de Logistica no encontrado")
    })

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<EncargadoLogistica> elogisticaOptional = encargadoLogisticaServices.findById(id);
      if (elogisticaOptional.isPresent()){
        return ResponseEntity.ok(elogisticaOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();

    }
@Operation(summary = "Crear un nuevo Encargado de Logistica", description = "Crea un Encargado de Logistica con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Encargado de Logistica creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = EncargadoLogistica.class)))

    @PostMapping
    public ResponseEntity<EncargadoLogistica> crear(@RequestBody EncargadoLogistica unEncargadoL){
        return ResponseEntity.status(HttpStatus.CREATED).body(encargadoLogisticaServices.save(unEncargadoL));
    }

    @Operation(summary = "Modificar Encargado de Logistica por ID", description = "Modificar información de un Encargado de Logistica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Encargado de Logistica modificado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = EncargadoLogistica.class))),
        @ApiResponse(responseCode = "404", description = "Encargado de Logistica no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarEncargado(@PathVariable Long id, @RequestBody EncargadoLogistica unEncargadoL){
        Optional <EncargadoLogistica> elogisticaOptional = encargadoLogisticaServices.findById(id);
        if (elogisticaOptional.isPresent()){
            EncargadoLogistica encargadolExiste = elogisticaOptional.get();
            encargadolExiste.setNombre(unEncargadoL.getNombre());
            encargadolExiste.setApellido(unEncargadoL.getApellido());
            encargadolExiste.setEmail(unEncargadoL.getEmail());
            
            
            EncargadoLogistica encargadolModificado = encargadoLogisticaServices.save(encargadolExiste);
            return ResponseEntity.ok(encargadolModificado);

        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar Encargado de Logistica por ID", description = "Elimina un Encargado de Logistica específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Encargado de Logistica eliminado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = EncargadoLogistica.class))),
        @ApiResponse(responseCode = "404", description = "Encargado de Logistica no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEncargadoLogistica(@PathVariable Long id){
        EncargadoLogistica unEncargadoLogistica = new EncargadoLogistica();
        unEncargadoLogistica.setId(id);
        Optional<EncargadoLogistica> encargadoLogisticaOptional =encargadoLogisticaServices.delete(unEncargadoLogistica);
        if (encargadoLogisticaOptional.isPresent()){
            return ResponseEntity.ok(encargadoLogisticaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
