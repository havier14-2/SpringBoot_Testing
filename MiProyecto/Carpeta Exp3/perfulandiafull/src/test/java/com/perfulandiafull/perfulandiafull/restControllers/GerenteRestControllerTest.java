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
import com.perfulandiafull.perfulandiafull.entities.Gerente;
import com.perfulandiafull.perfulandiafull.services.GerenteServicesImpl;


@SpringBootTest
@AutoConfigureMockMvc
public class GerenteRestControllerTest {

    @Autowired
private MockMvc mockMvc;

@MockitoBean
private GerenteServicesImpl gerenteServiceImpl;        //mocks, objeto simulado

@Autowired
private ObjectMapper objectMapper;              //atributo para gestionar el método save

List<Gerente> listaGerente;

@Test
public void verGerente() throws Exception{
    when(gerenteServiceImpl.findByAll()).thenReturn(listaGerente);
    mockMvc.perform(get("/api/gerentes")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
}

@Test
public void verUnGerente(){
    Gerente unGerente = new Gerente (4L, "Gerente", "Direccion","hola@gmail.com");
    try{
        when(gerenteServiceImpl.findById(4L)).thenReturn(Optional.of(unGerente));
        mockMvc.perform(get("/api/gerentes/4")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }catch (Exception ex){
        fail("Error.. en el test " + ex.getMessage());
    }
}

@Test
public void gerenteNoExiste() throws Exception{
    when(gerenteServiceImpl.findById(10L)).thenReturn(Optional.empty());
    mockMvc.perform(get("/api/gerente/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
}

@Test
public void crearGerenteTest() throws Exception{
    Gerente unGerente = new Gerente(5L, "Gerente", "Dirección","hola@gmail.com");
    when(gerenteServiceImpl.save(any(Gerente.class))).thenReturn(unGerente);
    Gerente otroGerente = new Gerente(6L, "Gerente1", "Dirección1","hola@gmail.com");
    when (gerenteServiceImpl.save(any(Gerente.class))).thenReturn(otroGerente);
    mockMvc.perform(post("/api/gerentes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unGerente)))
        .andExpect(status().isCreated());
}

@Test
public void actualizarGerenteTest() throws Exception {
    Gerente gerenteExistente = new Gerente(7L, "Gerente Antiguo", "Dirección antigua","hola@gmail.com");
    Gerente gerenteActualizado = new Gerente(7L, "Gerente Nueva", "Dirección nueva","hola@gmail.com");

    when(gerenteServiceImpl.findById(7L)).thenReturn(Optional.of(gerenteExistente));
    when(gerenteServiceImpl.save(any(Gerente.class))).thenReturn(gerenteActualizado);

    mockMvc.perform(put("/api/gerentes/7")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(gerenteActualizado)))
        .andExpect(status().isOk());
}

@Test
public void eliminarGerenteTest() throws Exception {
    Gerente gerente = new Gerente(8L, "Gerente a eliminar", "Ubicación","hola@gmail.com");

    when(gerenteServiceImpl.delete(any(Gerente.class)))
        .thenReturn(Optional.of(gerente));

    mockMvc.perform(delete("/api/gerentes/8")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()); 
}

}
