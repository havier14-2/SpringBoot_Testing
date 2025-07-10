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
import com.perfulandiafull.perfulandiafull.entities.Pagos;
import com.perfulandiafull.perfulandiafull.services.PagosServicesImpl;


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
public void verUnPagos(){
    Pagos unPagos = new Pagos (4L, "Pago", "Direccion",600.0);
     // Cambié el constructor de Pagos para incluir un campo de precio
     // Asegúrate de que la clase Pagos tenga este campo y su getter/setter
    try{
        when(pagosServiceImpl.findById(4L)).thenReturn(Optional.of(unPagos));
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
    Pagos unPagos = new Pagos(5L, "Pago", "Dirección",600.0);
    when(pagosServiceImpl.save(any(Pagos.class))).thenReturn(unPagos);
    Pagos otroPagos = new Pagos(6L, "Pago1", "Dirección1",600.0);
    when (pagosServiceImpl.save(any(Pagos.class))).thenReturn(otroPagos);
    mockMvc.perform(post("/api/pagos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unPagos)))
        .andExpect(status().isCreated());
}

@Test
public void actualizarPagosTest() throws Exception {
    Pagos pagoExistente = new Pagos(7L, "Pago Antiguo", "Dirección antigua",600.0);
    Pagos pagoActualizado = new Pagos(7L, "Pago Nueva", "Dirección nueva",600.0);

    when(pagosServiceImpl.findById(7L)).thenReturn(Optional.of(pagoExistente));
    when(pagosServiceImpl.save(any(Pagos.class))).thenReturn(pagoActualizado);

    mockMvc.perform(put("/api/pagos/7")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(pagoActualizado)))
        .andExpect(status().isOk());
}

@Test
public void eliminarPagosTest() throws Exception {
    Pagos pago = new Pagos(8L, "Pago a eliminar", "Ubicación",600.0);

    when(pagosServiceImpl.delete(any(Pagos.class)))
        .thenReturn(Optional.of(pago));

    mockMvc.perform(delete("/api/pagos/8")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()); 
}

}
