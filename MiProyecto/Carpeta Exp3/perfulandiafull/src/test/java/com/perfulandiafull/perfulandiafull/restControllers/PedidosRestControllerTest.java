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
import com.perfulandiafull.perfulandiafull.entities.Pedidos;
import com.perfulandiafull.perfulandiafull.services.PedidosServicesImpl;


@SpringBootTest
@AutoConfigureMockMvc
public class PedidosRestControllerTest {

    @Autowired
private MockMvc mockMvc;

@MockitoBean
private PedidosServicesImpl pedidosServiceImpl;        //mocks, objeto simulado

@Autowired
private ObjectMapper objectMapper;              //atributo para gestionar el método save

List<Pedidos> listaPedidos;

@Test
public void verPedidos() throws Exception{
    when(pedidosServiceImpl.findByAll()).thenReturn(listaPedidos);
    mockMvc.perform(get("/api/pedidos")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
}

@Test
public void verUnPedidos(){
    Pedidos unPedidos = new Pedidos (4L, "Pedido", "Direccion",500.0);
    try{
        when(pedidosServiceImpl.findById(4L)).thenReturn(Optional.of(unPedidos));
        mockMvc.perform(get("/api/pedidos/4")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }catch (Exception ex){
        fail("Error.. en el test " + ex.getMessage());
    }
}

@Test
public void pedidosNoExiste() throws Exception{
    when(pedidosServiceImpl.findById(10L)).thenReturn(Optional.empty());
    mockMvc.perform(get("/api/pedidos/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
}

@Test
public void crearPedidosTest() throws Exception{
    Pedidos unPedidos = new Pedidos(5L, "Pedido", "Dirección",500.0);
    when(pedidosServiceImpl.save(any(Pedidos.class))).thenReturn(unPedidos);
    Pedidos otroPedidos = new Pedidos(6L, "Pedido1", "Dirección1",500.0);
    when (pedidosServiceImpl.save(any(Pedidos.class))).thenReturn(otroPedidos);
    mockMvc.perform(post("/api/pedidos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unPedidos)))
        .andExpect(status().isCreated());
}

@Test
public void actualizarPedidosTest() throws Exception {
    Pedidos pedidoExistente = new Pedidos(7L, "Pedido Antiguo", "Dirección antigua",500.0);
    Pedidos pedidoActualizado = new Pedidos(7L, "Pedido Nueva", "Dirección nueva",500.0);

    when(pedidosServiceImpl.findById(7L)).thenReturn(Optional.of(pedidoExistente));
    when(pedidosServiceImpl.save(any(Pedidos.class))).thenReturn(pedidoActualizado);

    mockMvc.perform(put("/api/pedidos/7")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(pedidoActualizado)))
        .andExpect(status().isOk());
}

@Test
public void eliminarPedidosTest() throws Exception {
    Pedidos pedido = new Pedidos(8L, "Pedido a eliminar", "Ubicación",500.0);

    when(pedidosServiceImpl.delete(any(Pedidos.class)))
        .thenReturn(Optional.of(pedido));

    mockMvc.perform(delete("/api/pedidos/8")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()); 
}

}
