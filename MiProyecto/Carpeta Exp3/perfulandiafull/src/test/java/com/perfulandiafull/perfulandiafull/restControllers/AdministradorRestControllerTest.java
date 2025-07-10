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
import com.perfulandiafull.perfulandiafull.entities.Administrador;
import com.perfulandiafull.perfulandiafull.services.AdministradorServicesImpl;


@SpringBootTest
@AutoConfigureMockMvc
public class AdministradorRestControllerTest {

    @Autowired  
private MockMvc mockMvc;

@MockitoBean
private AdministradorServicesImpl administradorServiceImpl;        //mocks, objeto simulado

@Autowired
private ObjectMapper objectMapper;              //atributo para gestionar el método save

List<Administrador> listaAdministrador;


@Test
public void verAdministrador() throws Exception{
    when(administradorServiceImpl.findByAll()).thenReturn(listaAdministrador);
    mockMvc.perform(get("/api/administradores")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
}


@Test
public void verUnAdministrador(){
    Administrador unAdministrador = new Administrador(4L, "EncargadoVentas", "Apellido","hola@gmail.com");
    try{
        when(administradorServiceImpl.findById(4L)).thenReturn(Optional.of(unAdministrador));
        mockMvc.perform(get("/api/administradores/4")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }catch (Exception ex){
        fail("Error.. en el test " + ex.getMessage());
    }
}

@Test
public void administradorNoExiste() throws Exception{
    when(administradorServiceImpl.findById(10L)).thenReturn(Optional.empty());
    mockMvc.perform(get("/api/administradores/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
}


@Test
public void crearAdministradorTest() throws Exception{
    Administrador unAdministrador = new Administrador(5L, "EncargadoVentas", "Apellido","hola@gmail.com");
    when(administradorServiceImpl.save(any(Administrador.class))).thenReturn(unAdministrador);
    Administrador otroAdministrador = new Administrador(6L, "EncargadoVentas1", "Apellido1","hola@gmail.com");
    when (administradorServiceImpl.save(any(Administrador.class))).thenReturn(otroAdministrador);
    mockMvc.perform(post("/api/administradores")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unAdministrador)))
        .andExpect(status().isCreated());
}

@Test
public void actualizarAdministradorTest() throws Exception {
    Administrador administradorExistente = new Administrador(7L, "EncargadoVentas Antiguo", "Apellido antigua","hola@gmail.com");
    Administrador administradorActualizada = new Administrador(7L, "EncargadoVentas Nueva", "Apellido nueva","hola@gmail.com");

    when(administradorServiceImpl.findById(7L)).thenReturn(Optional.of(administradorExistente));
    when(administradorServiceImpl.save(any(Administrador.class))).thenReturn(administradorActualizada);

    mockMvc.perform(put("/api/administradores/7")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(administradorActualizada)))
        .andExpect(status().isOk());
}

@Test
public void eliminarAdministradorTest() throws Exception {
    Administrador administrador = new Administrador(8L, "EncargadoVentas a eliminar", "Apellido","hola@gmail.com");

    when(administradorServiceImpl.delete(any(Administrador.class)))
        .thenReturn(Optional.of(administrador));

    mockMvc.perform(delete("/api/administradores/8")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()); 
}

}
