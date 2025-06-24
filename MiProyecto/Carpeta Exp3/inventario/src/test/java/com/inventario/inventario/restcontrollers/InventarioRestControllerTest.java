package com.inventario.inventario.restcontrollers;

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
import com.inventario.inventario.entities.Inventario;
import com.inventario.inventario.services.InventarioServicesImpl;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class InventarioRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InventarioServicesImpl inventarioServiceImpl;        //mocks, objeto simulado

    @Autowired
    private ObjectMapper objectMapper;              //atributo para gestionar el método save

    List<Inventario> listaInventarios;


    @Test
    public void verInventarios() throws Exception{
        when(inventarioServiceImpl.findByAll()).thenReturn(listaInventarios);
        mockMvc.perform(get("/api/inventario")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }


    @Test
    public void verUnInventario(){
        Inventario unInventario = new Inventario (4L, "Audífonos Gamer HyperX", "Audífonos con sonido envolvente 7.1 y micrófono con cancelación de ruido", 150000);
        try{
            when(inventarioServiceImpl.findById(4L)).thenReturn(Optional.of(unInventario));
            mockMvc.perform(get("/api/inventario/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        }catch (Exception ex){
            fail("Error.. en el test " + ex.getMessage());
        }
    }

    @Test
    public void inventarioNoExiste() throws Exception{
        when(inventarioServiceImpl.findById(10L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/inventario/10")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }


    @Test
    public void crearInventarioTest() throws Exception{
        Inventario unInventario = new Inventario(5L, "Teclado", "Gamer", 25);
        Inventario otroInventario = new Inventario(6L, "Teclado", "Normal", 25);
        when (inventarioServiceImpl.save(any(Inventario.class))).thenReturn(otroInventario);
        mockMvc.perform(post("/api/inventario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unInventario)))
            .andExpect(status().isCreated());
    }

}
