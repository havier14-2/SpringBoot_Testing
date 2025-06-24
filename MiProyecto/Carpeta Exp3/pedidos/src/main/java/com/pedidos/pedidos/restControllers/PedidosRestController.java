package com.pedidos.pedidos.restControllers;

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

import com.pedidos.pedidos.entities.Pedidos;
import com.pedidos.pedidos.services.PedidosServices;
@Tag(name = "Pedidos", description = "Operaciones relacionadas con los pedidos")
@RestController
@RequestMapping("/api/pedidos")
public class PedidosRestController {
    @Autowired
private PedidosServices pedidoServices;
@Operation(summary = "Obtener lista de productos", description = "Devuelve todos los productos disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de productos retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Pedidos.class)))
@GetMapping
public List<Pedidos> verPedidos() {
    return (List<Pedidos>) pedidoServices.findByAll();
}
@Operation(summary = "Obtener producto por ID", description = "Obtiene el detalle de un producto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedidos.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
@GetMapping("/{id}")
public ResponseEntity<?> verDetalle(@PathVariable Long id) {
    Optional<Pedidos> pedidoOptional = pedidoServices.findById(id);
    if (pedidoOptional.isPresent()) {
        return ResponseEntity.ok(pedidoOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
}
@Operation(summary = "Crear un nuevo producto", description = "Crea un producto con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Producto creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedidos.class)))

@PostMapping
public ResponseEntity<Pedidos> crear(@RequestBody Pedidos unPedido) {
    return ResponseEntity.status(HttpStatus.CREATED).body(pedidoServices.save(unPedido));
}
@Operation(summary = "Modificar producto por ID", description = "Modificar información de un producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto modificado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedidos.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
@PutMapping("/{id}")
public ResponseEntity<?> modificarPedido(@PathVariable Long id, @RequestBody Pedidos unPedido) {
    Optional<Pedidos> pedidoOptional = pedidoServices.findById(id);
    if (pedidoOptional.isPresent()) {
        Pedidos pedidoExiste = pedidoOptional.get();
        

        Pedidos pedidoModificado = pedidoServices.save(pedidoExiste);
        return ResponseEntity.ok(pedidoModificado);
    }
    return ResponseEntity.notFound().build();
}
@Operation(summary = "Eliminar producto por ID", description = "Elimina un objeto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedidos.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
@DeleteMapping("/{id}")
public ResponseEntity<?> eliminarPedido(@PathVariable Long id) {
    Pedidos unPedido = new Pedidos();
    unPedido.setId(id);
    Optional<Pedidos> pedidoOptional = pedidoServices.delete(unPedido);
    if (pedidoOptional.isPresent()) {
        return ResponseEntity.ok(pedidoOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
}

}
