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

import com.perfulandiafull.perfulandiafull.entities.Pagos;
import com.perfulandiafull.perfulandiafull.services.PagosServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Pagos", description = "Operaciones relacionadas con los pagos")
@RestController
@RequestMapping("/api/pagos")
public class PagosRestController {
     @Autowired
    private PagosServices Pagosservices;

    @Operation(summary = "Obtener lista de pagos", description = "Devuelve todos los pagos disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de pagos retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Pagos.class)))
    @GetMapping
    public List<Pagos> verPagos(){
        return (List<Pagos>) Pagosservices.findByAll();
    }

    @Operation(summary = "Obtener Pago por ID", description = "Obtiene el detalle de un Pago específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagos.class))),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<Pagos> PagosOptional = Pagosservices.findById(id);
      if (PagosOptional.isPresent()){
        return ResponseEntity.ok(PagosOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Crear un nuevo Pago", description = "Crea un Pago con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Pago creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagos.class)))

    @PostMapping
    public ResponseEntity<Pagos> crearPagos(@RequestBody Pagos unPagos){
        return ResponseEntity.status(HttpStatus.CREATED).body(Pagosservices.save(unPagos));
    }

    @Operation(summary = "Modificar Pago por ID", description = "Modificar información de un Pago")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago modificado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagos.class))),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarPagos(@PathVariable Long id, @RequestBody Pagos unPagos){
        Optional <Pagos> PagosOptional = Pagosservices.findById(id);
        if (PagosOptional.isPresent()){
            Pagos PagosExiste = PagosOptional.get();
            PagosExiste.setNombre(unPagos.getNombre());
            PagosExiste.setDescripcion(unPagos.getDescripcion());
            PagosExiste.setPrecio(unPagos.getPrecio());
            Pagos pagoModificado = Pagosservices.save(PagosExiste);
            return ResponseEntity.ok(pagoModificado);

        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar Pago por ID", description = "Elimina un Pago específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago eliminado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagos.class))),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
     @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPagos(@PathVariable Long id){
        Pagos unPagos = new Pagos();
        unPagos.setId(id);
        Optional<Pagos> PagosOptional = Pagosservices.delete(unPagos);
        if (PagosOptional.isPresent()){
            return ResponseEntity.ok(PagosOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
