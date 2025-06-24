package com.javier.springboot.ejemplito.perfulandia.restControllers;

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

import com.javier.springboot.ejemplito.perfulandia.entities.Cliente;
import com.javier.springboot.ejemplito.perfulandia.services.ClienteServices;
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
    @Autowired
    private ClienteServices clienteservices;

    @GetMapping
    public List<Cliente> verProductos(){
        return (List<Cliente>) clienteservices.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
      Optional<Cliente> clienteOptional = clienteservices.findById(id);
      if (clienteOptional.isPresent()){
        return ResponseEntity.ok(clienteOptional.orElseThrow());
      }
      return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente unCliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteservices.save(unCliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarCliente(@PathVariable Long id, @RequestBody Cliente unCliente){
        Optional <Cliente> clienteOptional = clienteservices.findById(id);
        if (clienteOptional.isPresent()){
            Cliente clienteExiste = clienteOptional.get();
            clienteExiste.setNombre(unCliente.getNombre());
            clienteExiste.setApellido(unCliente.getApellido());
            clienteExiste.setDireccion(unCliente.getDireccion());
            clienteExiste.setComuna(unCliente.getComuna());
            clienteExiste.setEmail(unCliente.getEmail());
            clienteExiste.setRut(unCliente.getRut());
            clienteExiste.setTelefono(unCliente.getTelefono());
            
            Cliente clienteModificado = clienteservices.save(clienteExiste);
            return ResponseEntity.ok(clienteModificado);

        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        Cliente unCliente = new Cliente();
        unCliente.setId(id);
        Optional<Cliente> clienteOptional = clienteservices.delete(unCliente);
        if (clienteOptional.isPresent()){
            return ResponseEntity.ok(clienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    

}
