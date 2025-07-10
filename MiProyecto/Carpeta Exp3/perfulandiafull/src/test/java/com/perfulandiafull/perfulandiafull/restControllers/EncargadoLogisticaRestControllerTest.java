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
import com.perfulandiafull.perfulandiafull.entities.EncargadoLogistica;
import com.perfulandiafull.perfulandiafull.services.EncargadoLogisticaServicesImpl;


@SpringBootTest
@AutoConfigureMockMvc
public class EncargadoLogisticaRestControllerTest {

  @Autowired  
private MockMvc mockMvc;

@MockitoBean
private EncargadoLogisticaServicesImpl encargadoLogisticaServiceImpl;        //mocks, objeto simulado

@Autowired
private ObjectMapper objectMapper;              //atributo para gestionar el método save

List<EncargadoLogistica> listaEncargadoLogistica;


@Test
public void verEncargadoLogistica() throws Exception{
    when(encargadoLogisticaServiceImpl.findByAll()).thenReturn(listaEncargadoLogistica);
    mockMvc.perform(get("/api/encargados-logistica")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
}


@Test
public void verUnEncargadoLogistica(){
    EncargadoLogistica unEncargadoLogistica = new EncargadoLogistica(4L, "EncargadoVentas", "Apellido","hola@gmail.com");
    try{
        when(encargadoLogisticaServiceImpl.findById(4L)).thenReturn(Optional.of(unEncargadoLogistica));
        mockMvc.perform(get("/api/encargados-logistica/4")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }catch (Exception ex){
        fail("Error.. en el test " + ex.getMessage());
    }
}

@Test
public void encargadoLogisticaNoExiste() throws Exception{
    when(encargadoLogisticaServiceImpl.findById(10L)).thenReturn(Optional.empty());
    mockMvc.perform(get("/api/encargados-logistica/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
}


@Test
public void crearEncargadoLogisticaTest() throws Exception{
    EncargadoLogistica unEncargadoLogistica = new EncargadoLogistica(5L, "EncargadoVentas", "Apellido","hola@gmail.com");
    when(encargadoLogisticaServiceImpl.save(any(EncargadoLogistica.class))).thenReturn(unEncargadoLogistica);
    EncargadoLogistica otroEncargadoLogistica = new EncargadoLogistica(6L, "EncargadoVentas1", "Apellido1","hola@gmail.com");
    when (encargadoLogisticaServiceImpl.save(any(EncargadoLogistica.class))).thenReturn(otroEncargadoLogistica);
    mockMvc.perform(post("/api/encargados-logistica")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unEncargadoLogistica)))
        .andExpect(status().isCreated());
}

@Test
public void actualizarEncargadoLogisticaTest() throws Exception {
    EncargadoLogistica encargadoLogisticaExistente = new EncargadoLogistica(7L, "EncargadoVentas Antiguo", "Apellido antigua","hola@gmail.com");
    EncargadoLogistica encargadoLogisticaActualizada = new EncargadoLogistica(7L, "EncargadoVentas Nueva", "Apellido nueva","hola@gmail.com");

    when(encargadoLogisticaServiceImpl.findById(7L)).thenReturn(Optional.of(encargadoLogisticaExistente));
    when(encargadoLogisticaServiceImpl.save(any(EncargadoLogistica.class))).thenReturn(encargadoLogisticaActualizada);

    mockMvc.perform(put("/api/encargados-logistica/7")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(encargadoLogisticaActualizada)))
        .andExpect(status().isOk());
}

@Test
public void eliminarEncargadoLogisticaTest() throws Exception {
    EncargadoLogistica encargadoLogistica = new EncargadoLogistica(8L, "EncargadoVentas a eliminar", "Apellido","hola@gmail.com");

    when(encargadoLogisticaServiceImpl.delete(any(EncargadoLogistica.class)))
        .thenReturn(Optional.of(encargadoLogistica));

    mockMvc.perform(delete("/api/encargados-logistica/8")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()); 
}

}
