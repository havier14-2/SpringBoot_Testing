package com.pagos.pagos.restcontrollers;

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
import com.pagos.pagos.entities.Pagos;
import com.pagos.pagos.services.PagosServicesImpl;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class PagosRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PagosServicesImpl pagosServiceImpl;        //mocks, objeto simulado

    @Autowired
    private ObjectMapper objectMapper;              //atributo para gestionar el método save

    List<Pagos> listaPagos;


    @Test
    public void verPagos() throws Exception{
        when(pagosServiceImpl.findByAll()).thenReturn(listaPagos);
        mockMvc.perform(get("/api/pagos")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }


    @Test
    public void verUnPago(){
        Pagos unPago = new Pagos (4L, "Audífonos Gamer HyperX", 150000);
        try{
            when(pagosServiceImpl.findById(4L)).thenReturn(Optional.of(unPago));
            mockMvc.perform(get("/api/pagos/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        }catch (Exception ex){
            fail("Error.. en el test " + ex.getMessage());
        }
    }

    @Test
    public void pagosNoExiste() throws Exception{
        when(pagosServiceImpl.findById(10L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/pagos/10")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }


    @Test
    public void crearPagosTest() throws Exception{
        Pagos unPago = new Pagos(5L, "Teclado", 25000);
        Pagos otroPago = new Pagos(6L, "Teclado", 25000);
        when (pagosServiceImpl.save(any(Pagos.class))).thenReturn(otroPago);
        mockMvc.perform(post("/api/pagos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unPago)))
            .andExpect(status().isCreated());
    }

}

