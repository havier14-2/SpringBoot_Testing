package com.perfulandiafull.perfulandiafull.restControllers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandiafull.perfulandiafull.entities.Cliente;
import com.perfulandiafull.perfulandiafull.services.ClienteServicesImpl;


@SpringBootTest
@AutoConfigureMockMvc
public class ClienteRestControllerTest {

    @Autowired  
private MockMvc mockMvc;

@MockitoBean
private ClienteServicesImpl clienteServiceImpl;        //mocks, objeto simulado

@Autowired
private ObjectMapper objectMapper;              //atributo para gestionar el método save

List<Cliente> listaCliente;


@Test
public void verCliente() throws Exception{
    when(clienteServiceImpl.findByAll()).thenReturn(listaCliente);
    mockMvc.perform(get("/api/clientes")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
}


@Test
public void verUnCliente(){
    Cliente unCliente = new Cliente(4L, "EncargadoVentas", "Apellido","hola@gmail.com");
    try{
        when(clienteServiceImpl.findById(4L)).thenReturn(Optional.of(unCliente));
        mockMvc.perform(get("/api/clientes/4")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }catch (Exception ex){
        fail("Error.. en el test " + ex.getMessage());
    }
}

@Test
public void clienteNoExiste() throws Exception{
    when(clienteServiceImpl.findById(10L)).thenReturn(Optional.empty());
    mockMvc.perform(get("/api/clientes/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
}


@Test
public void crearClienteTest() throws Exception{
    Cliente unCliente = new Cliente(5L, "EncargadoVentas", "Apellido","hola@gmail.com");
    when(clienteServiceImpl.save(any(Cliente.class))).thenReturn(unCliente);
    Cliente otroCliente = new Cliente(6L, "EncargadoVentas1", "Apellido1","hola@gmail.com");
    when (clienteServiceImpl.save(any(Cliente.class))).thenReturn(otroCliente);
    mockMvc.perform(post("/api/clientes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unCliente)))
        .andExpect(status().isCreated());
}

@Test
public void actualizarClienteTest() throws Exception {
    Cliente clienteExistente = new Cliente(7L, "EncargadoVentas Antiguo", "Apellido antigua","hola@gmail.com");
    Cliente clienteActualizada = new Cliente(7L, "EncargadoVentas Nueva", "Apellido nueva","hola@gmail.com");

    when(clienteServiceImpl.findById(7L)).thenReturn(Optional.of(clienteExistente));
    when(clienteServiceImpl.save(any(Cliente.class))).thenReturn(clienteActualizada);

    mockMvc.perform(put("/api/clientes/7")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(clienteActualizada)))
        .andExpect(status().isOk());
}

@Test
public void eliminarClienteTest() throws Exception {
    Cliente cliente = new Cliente(8L, "EncargadoVentas a eliminar", "Apellido","hola@gmail.com");

    when(clienteServiceImpl.delete(any(Cliente.class)))
        .thenReturn(Optional.of(cliente));

    mockMvc.perform(delete("/api/clientes/8")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()); 
}

}
