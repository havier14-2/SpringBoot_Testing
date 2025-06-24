package com.pedidos.pedidos.restcontroller;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedidos.pedidos.entities.Pedidos;
import com.pedidos.pedidos.services.PedidosServicesImpl;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

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
    public void verUnPedido(){
        Pedidos unPedido = new Pedidos (4L, "Audífonos Gamer HyperX", "Audífonos con sonido envolvente 7.1 y micrófono con cancelación de ruido", 150000);
        try{
            when(pedidosServiceImpl.findById(4L)).thenReturn(Optional.of(unPedido));
            mockMvc.perform(get("/api/pedidos/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        }catch (Exception ex){
            fail("Error.. en el test " + ex.getMessage());
        }
    }

    @Test
    public void pedidosNoExisten() throws Exception{
        when(pedidosServiceImpl.findById(10L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/pedidos/10")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }


    @Test
    public void crearPedidoTest() throws Exception{
        Pedidos unPedido = new Pedidos(5L, "Teclado", "Gamer", 25);
        Pedidos otroPedido = new Pedidos(6L, "Teclado", "Normal", 25);
        when (pedidosServiceImpl.save(any(Pedidos.class))).thenReturn(otroPedido);
        mockMvc.perform(post("/api/pedidos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unPedido)))
            .andExpect(status().isCreated());
    }

}

