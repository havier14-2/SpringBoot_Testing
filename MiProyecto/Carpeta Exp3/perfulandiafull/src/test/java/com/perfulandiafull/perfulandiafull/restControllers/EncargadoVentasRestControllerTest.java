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
import com.perfulandiafull.perfulandiafull.entities.EncargadoVentas;
import com.perfulandiafull.perfulandiafull.services.EncargadoVentasServicesImpl;


@SpringBootTest
@AutoConfigureMockMvc
public class EncargadoVentasRestControllerTest {

    @Autowired 
private MockMvc mockMvc;

@MockitoBean
private EncargadoVentasServicesImpl encargadoVentasServiceImpl;        //mocks, objeto simulado

@Autowired
private ObjectMapper objectMapper;              //atributo para gestionar el método save

List<EncargadoVentas> listaEncargadoVentas;


@Test
public void verEncargadoVentas() throws Exception{
    when(encargadoVentasServiceImpl.findByAll()).thenReturn(listaEncargadoVentas);
    mockMvc.perform(get("/api/encargados-ventas")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
}


@Test
public void verUnEncargadoVentas(){
    EncargadoVentas unEncargadoVentas = new EncargadoVentas(4L, "EncargadoVentas", "Apellido","hola@gmail.com");
    try{
        when(encargadoVentasServiceImpl.findById(4L)).thenReturn(Optional.of(unEncargadoVentas));
        mockMvc.perform(get("/api/encargados-ventas/4")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }catch (Exception ex){
        fail("Error.. en el test " + ex.getMessage());
    }
}

@Test
public void encargadoVentasNoExiste() throws Exception{
    when(encargadoVentasServiceImpl.findById(10L)).thenReturn(Optional.empty());
    mockMvc.perform(get("/api/encargados-ventas/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
}


@Test
public void crearEncargadoVentasTest() throws Exception{
    EncargadoVentas unEncargadoVentas = new EncargadoVentas(5L, "EncargadoVentas", "Apellido","hola@gmail.com");
    when(encargadoVentasServiceImpl.save(any(EncargadoVentas.class))).thenReturn(unEncargadoVentas);
    EncargadoVentas otroEncargadoVentas = new EncargadoVentas(6L, "EncargadoVentas1", "Apellido1","hola@gmail.com");
    when (encargadoVentasServiceImpl.save(any(EncargadoVentas.class))).thenReturn(otroEncargadoVentas);
    mockMvc.perform(post("/api/encargados-ventas")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unEncargadoVentas)))
        .andExpect(status().isCreated());
}

@Test
public void actualizarEncargadoVentasTest() throws Exception {
    EncargadoVentas encargadoVentasExistente = new EncargadoVentas(7L, "EncargadoVentas Antiguo", "Apellido antigua","hola@gmail.com");
    EncargadoVentas encargadoVentasActualizada = new EncargadoVentas(7L, "EncargadoVentas Nueva", "Apellido nueva","hola@gmail.com");

    when(encargadoVentasServiceImpl.findById(7L)).thenReturn(Optional.of(encargadoVentasExistente));
    when(encargadoVentasServiceImpl.save(any(EncargadoVentas.class))).thenReturn(encargadoVentasActualizada);

    mockMvc.perform(put("/api/encargados-ventas/7")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(encargadoVentasActualizada)))
        .andExpect(status().isOk());
}

@Test
public void eliminarEncargadoVentasTest() throws Exception {
    EncargadoVentas encargadoVentas = new EncargadoVentas(8L, "EncargadoVentas a eliminar", "Apellido","hola@gmail.com");

    when(encargadoVentasServiceImpl.delete(any(EncargadoVentas.class)))
        .thenReturn(Optional.of(encargadoVentas));

    mockMvc.perform(delete("/api/encargados-ventas/8")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()); 
}

}
