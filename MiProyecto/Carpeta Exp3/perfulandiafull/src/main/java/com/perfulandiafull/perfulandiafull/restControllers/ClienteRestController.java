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

import com.perfulandiafull.perfulandiafull.entities.Cliente;
import com.perfulandiafull.perfulandiafull.services.ClienteServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {
     @Autowired
    private ClienteServices clienteservices;


    @Operation(summary = "Obtener lista de Clientes", description = "Devuelve todos los Clientes disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de Clientes retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Cliente.class)))
    @GetMapping
    public List<Cliente> verClientes(){
        return (List<Cliente>) clienteservices.findByAll();
    }


    @Operation(summary = "Obtener Cliente por ID", description = "Obtiene el detalle de un Cliente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<Cliente> clienteOptional = clienteservices.findById(id);
      if (clienteOptional.isPresent()){
        return ResponseEntity.ok(clienteOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Crear un nuevo Cliente", description = "Crea un Cliente con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Cliente creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente unCliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteservices.save(unCliente));
    }

    @Operation(summary = "Modificar Cliente por ID", description = "Modificar información de un Cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente modificado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarCliente(@PathVariable Long id, @RequestBody Cliente unCliente){
        Optional <Cliente> clienteOptional = clienteservices.findById(id);
        if (clienteOptional.isPresent()){
            Cliente clienteExiste = clienteOptional.get();
            clienteExiste.setNombre(unCliente.getNombre());
            clienteExiste.setApellido(unCliente.getApellido());
            clienteExiste.setEmail(unCliente.getEmail());
            
            
            Cliente clienteModificado = clienteservices.save(clienteExiste);
            return ResponseEntity.ok(clienteModificado);

        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar Cliente por ID", description = "Elimina un Cliente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente eliminado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id){
        Cliente unCliente = new Cliente();
        unCliente.setId(id);
        Optional<Cliente> clienteOptional = clienteservices.delete(unCliente);
        if (clienteOptional.isPresent()){
            return ResponseEntity.ok(clienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
