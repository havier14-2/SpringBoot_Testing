package com.pagos.pagos.restControllers;

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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.pagos.pagos.entities.Pagos;
import com.pagos.pagos.services.PagosServices;
@Tag(name = "Pagos", description = "Operaciones relacionadas con los Pagos")
@RestController
@RequestMapping("/api/pagos")
public class PagosRestController {
    @Autowired
private PagosServices pagoServices;
@Operation(summary = "Obtener lista de Pagos", description = "Devuelve todos los Pagos")
    @ApiResponse(responseCode = "200", description = "Lista de Pagos retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Pagos.class)))
@GetMapping
public List<Pagos> verPagos(){
    return (List<Pagos>) pagoServices.findByAll();
}
@Operation(summary = "Obtener pago por ID", description = "Obtiene el detalle de un pago específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagos.class))),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
@GetMapping("/{id}")
public ResponseEntity<?> verDetalle(@PathVariable Long id){
    Optional<Pagos> pagoOptional = pagoServices.findById(id);
    if (pagoOptional.isPresent()){
        return ResponseEntity.ok(pagoOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
}
@Operation(summary = "Crear un nuevo pago", description = "Crea un pago con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Pago creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagos.class)))

@PostMapping
public ResponseEntity<Pagos> crear(@RequestBody Pagos unPago){
    return ResponseEntity.status(HttpStatus.CREATED).body(pagoServices.save(unPago));
}
@Operation(summary = "Modificar pago por ID", description = "Modificar información de un pago")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago modificado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagos.class))),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })

@PutMapping("/{id}")
public ResponseEntity<?> modificarPago(@PathVariable Long id, @RequestBody Pagos unPago){
    Optional<Pagos> pagoOptional = pagoServices.findById(id);
    if (pagoOptional.isPresent()){
        Pagos pagoExiste = pagoOptional.get();
        
        
        Pagos pagoModificado = pagoServices.save(pagoExiste);
        return ResponseEntity.ok(pagoModificado);
    }
    return ResponseEntity.notFound().build();
}
@Operation(summary = "Eliminar pago por ID", description = "Elimina un objeto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago eliminado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagos.class))),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
@DeleteMapping("/{id}")
public ResponseEntity<?> eliminarPago(@PathVariable Long id){
    Pagos unPago = new Pagos();
    unPago.setId(id);
    Optional<Pagos> pagoOptional = pagoServices.delete(unPago);
    if (pagoOptional.isPresent()){
        return ResponseEntity.ok(pagoOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
}

}
