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
import com.perfulandiafull.perfulandiafull.entities.Inventario;
import com.perfulandiafull.perfulandiafull.services.InventarioServicesImpl;


@SpringBootTest
@AutoConfigureMockMvc
public class InventarioRestControllerTest {

    @Autowired
private MockMvc mockMvc;

@MockitoBean
private InventarioServicesImpl inventarioServiceImpl;        //mocks, objeto simulado

@Autowired
private ObjectMapper objectMapper;              //atributo para gestionar el método save

List<Inventario> listaInventario;

@Test
public void verInventario() throws Exception{
    when(inventarioServiceImpl.findByAll()).thenReturn(listaInventario);
    mockMvc.perform(get("/api/inventario")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
}

@Test
public void verUnInventario(){
    Inventario unInventario = new Inventario (4L, "Inventario", "Direccion",500.0);
     // Cambié el constructor de Inventario para incluir un campo de precio
     // Asegúrate de que la clase Inventario tenga este campo y su getter/setter
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
    Inventario unInventario = new Inventario(5L, "Inventario", "Dirección",500.0);
    when(inventarioServiceImpl.save(any(Inventario.class))).thenReturn(unInventario);
    Inventario otroInventario = new Inventario(6L, "Inventario1", "Dirección1",500.0);
    when (inventarioServiceImpl.save(any(Inventario.class))).thenReturn(otroInventario);
    mockMvc.perform(post("/api/inventario")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unInventario)))
        .andExpect(status().isCreated());
}

@Test
public void actualizarInventarioTest() throws Exception {
    Inventario inventarioExistente = new Inventario(7L, "Inventario Antiguo", "Dirección antigua",500.0);
    Inventario inventarioActualizado = new Inventario(7L, "Inventario Nueva", "Dirección nueva",500.0);

    when(inventarioServiceImpl.findById(7L)).thenReturn(Optional.of(inventarioExistente));
    when(inventarioServiceImpl.save(any(Inventario.class))).thenReturn(inventarioActualizado);

    mockMvc.perform(put("/api/inventario/7")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(inventarioActualizado)))
        .andExpect(status().isOk());
}

@Test
public void eliminarInventarioTest() throws Exception {
    Inventario inventario = new Inventario(8L, "Inventario a eliminar", "Ubicación",500.0);

    when(inventarioServiceImpl.delete(any(Inventario.class)))
        .thenReturn(Optional.of(inventario));

    mockMvc.perform(delete("/api/inventario/8")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()); 
}

}
