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

import com.perfulandiafull.perfulandiafull.entities.Pedidos;
import com.perfulandiafull.perfulandiafull.services.PedidosServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Pedidos", description = "Operaciones relacionadas con los pedidos")

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRestController {
    @Autowired
    private PedidosServices Pedidosservices;


    @Operation(summary = "Obtener lista de Pedido", description = "Devuelve todos los Pedidos disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de Pedidos retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Pedidos.class)))
    @GetMapping
    public List<Pedidos> verPedidos(){
        return (List<Pedidos>) Pedidosservices.findByAll();
    }

    @Operation(summary = "Obtener Pedido por ID", description = "Obtiene el detalle de un Pedido específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedidos.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<Pedidos> PedidosOptional = Pedidosservices.findById(id);
      if (PedidosOptional.isPresent()){
        return ResponseEntity.ok(PedidosOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Crear un nuevo Pedido", description = "Crea un Pedido con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Pedido creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedidos.class)))

    @PostMapping
    public ResponseEntity<Pedidos> crearPedidos(@RequestBody Pedidos unPedidos){
        return ResponseEntity.status(HttpStatus.CREATED).body(Pedidosservices.save(unPedidos));
    }

    @Operation(summary = "Modificar Pedido por ID", description = "Modificar información de un Pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido modificado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedidos.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarPedidos(@PathVariable Long id, @RequestBody Pedidos unPedidos){
        Optional <Pedidos> PedidosOptional = Pedidosservices.findById(id);
        if (PedidosOptional.isPresent()){
            Pedidos PedidosExiste = PedidosOptional.get();
            PedidosExiste.setNombre(unPedidos.getNombre());
            PedidosExiste.setDescripcion(unPedidos.getDescripcion());
           PedidosExiste.setPrecio(unPedidos.getPrecio());
            Pedidos PedidosModificado = Pedidosservices.save(PedidosExiste);
            return ResponseEntity.ok(PedidosModificado);

        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar Pedido por ID", description = "Elimina un Pedido específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido eliminado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedidos.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
     @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedidos(@PathVariable Long id){
        Pedidos unPedidos= new Pedidos();
        unPedidos.setId(id);
        Optional<Pedidos> PedidosOptional = Pedidosservices.delete(unPedidos);
        if (PedidosOptional.isPresent()){
            return ResponseEntity.ok(PedidosOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
