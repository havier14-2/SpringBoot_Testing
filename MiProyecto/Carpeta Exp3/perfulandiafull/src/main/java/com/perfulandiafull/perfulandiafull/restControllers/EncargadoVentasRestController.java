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

import com.perfulandiafull.perfulandiafull.entities.EncargadoVentas;
import com.perfulandiafull.perfulandiafull.entities.Inventario;
import com.perfulandiafull.perfulandiafull.services.EncargadoVentasServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Encargados de Ventas", description = "Operaciones relacionadas con Encargados de Ventas")
@RestController
@RequestMapping("/api/encargados-ventas")
public class EncargadoVentasRestController {
    @Autowired
    private EncargadoVentasServices encargadoVentasServices;

    @Operation(summary = "Obtener lista de Encargados de Ventas", description = "Devuelve todos los Encargados de Ventas disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de Encargados de Ventas retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Inventario.class)))
    @GetMapping
    public List<EncargadoVentas> verEncargadoVentas(){
        return (List<EncargadoVentas>) encargadoVentasServices.findByAll();
    }

    @Operation(summary = "Obtener Encargados de Ventas por ID", description = "Obtiene el detalle de un producto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Encargado de Ventas encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = EncargadoVentas.class))),
        @ApiResponse(responseCode = "404", description = "Encargados de Ventas no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<EncargadoVentas> EncargadoVentasOptional = encargadoVentasServices.findById(id);
      if (EncargadoVentasOptional.isPresent()){
        return ResponseEntity.ok(EncargadoVentasOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Crear un nuevo Encargado de Ventas", description = "Crea un Encargado de Ventas con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Encargado de Ventas creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = EncargadoVentas.class)))

    @PostMapping
    public ResponseEntity<EncargadoVentas> crear(@RequestBody EncargadoVentas unEncargadoV){
        return ResponseEntity.status(HttpStatus.CREATED).body(encargadoVentasServices.save(unEncargadoV));
    }

    @Operation(summary = "Modificar Encargado de Ventas por ID", description = "Modificar información de un Encargado de Ventas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Encargado de Ventas modificado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = EncargadoVentas.class))),
        @ApiResponse(responseCode = "404", description = "Encargado de Ventas no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarEncargadoVentas(@PathVariable Long id, @RequestBody EncargadoVentas unEncargadoV){
        Optional <EncargadoVentas> elogisticaOptional = encargadoVentasServices.findById(id);
        if (elogisticaOptional.isPresent()){
            EncargadoVentas encargadovExiste = elogisticaOptional.get();
            encargadovExiste.setNombre(unEncargadoV.getNombre());
            encargadovExiste.setApellido(unEncargadoV.getApellido());
            encargadovExiste.setEmail(unEncargadoV.getEmail());
          
            
            EncargadoVentas encargadolModificado = encargadoVentasServices.save(encargadovExiste);
            return ResponseEntity.ok(encargadolModificado);

        }
        return ResponseEntity.notFound().build();
        
    }

    @Operation(summary = "Eliminar Encargado de Ventas por ID", description = "Elimina un Encargado de Ventas específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Encargado de Ventas eliminado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = EncargadoVentas.class))),
        @ApiResponse(responseCode = "404", description = "Encargado de Ventas no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEncargadoVentas(@PathVariable Long id){
        EncargadoVentas unEncargadoVentas = new EncargadoVentas();
        unEncargadoVentas.setId(id);
        Optional<EncargadoVentas> encargadoVentasOptional =encargadoVentasServices.delete(unEncargadoVentas);
        if (encargadoVentasOptional.isPresent()){
            return ResponseEntity.ok(encargadoVentasOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
