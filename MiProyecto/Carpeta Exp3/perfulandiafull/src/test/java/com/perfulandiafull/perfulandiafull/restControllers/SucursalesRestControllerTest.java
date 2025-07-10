package com.perfulandiafull.perfulandiafull.restControllers;

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
import com.perfulandiafull.perfulandiafull.entities.Sucursales;
import com.perfulandiafull.perfulandiafull.services.SucursalesServicesImpl;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc

public class SucursalesRestControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SucursalesServicesImpl sucursalesServiceImpl;        //mocks, objeto simulado

    @Autowired
    private ObjectMapper objectMapper;              //atributo para gestionar el método save

    List<Sucursales> listaSucursales;


    @Test
    public void verSucursales() throws Exception{
        when(sucursalesServiceImpl.findByAll()).thenReturn(listaSucursales);
        mockMvc.perform(get("/api/sucursales")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }


    @Test
    public void verUnSucursales(){
        Sucursales unSucursales = new Sucursales (4L, "Sucursal", "Direccion");
        try{
            when(sucursalesServiceImpl.findById(4L)).thenReturn(Optional.of(unSucursales));
            mockMvc.perform(get("/api/sucursales/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        }catch (Exception ex){
            fail("Error.. en el test " + ex.getMessage());
        }
    }

    @Test
    public void sucursalesNoExiste() throws Exception{
        when(sucursalesServiceImpl.findById(10L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/sucursales/10")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }


    @Test
    public void crearSucursalesTest() throws Exception{
        Sucursales unSucursales = new Sucursales(5L, "Sucursal", "Dirección");
        when(sucursalesServiceImpl.save(any(Sucursales.class))).thenReturn(unSucursales);
        Sucursales otroSucursales = new Sucursales(6L, "Sucursal1", "Dirección1");
        when (sucursalesServiceImpl.save(any(Sucursales.class))).thenReturn(otroSucursales);
        mockMvc.perform(post("/api/sucursales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unSucursales)))
            .andExpect(status().isCreated());
    }

   @Test
public void actualizarSucursalesTest() throws Exception {
    Sucursales sucursalExistente = new Sucursales(7L, "Sucursal Antiguo", "Dirección antigua");
    Sucursales sucursalActualizada = new Sucursales(7L, "Sucursal Nueva", "Dirección nueva");

    when(sucursalesServiceImpl.findById(7L)).thenReturn(Optional.of(sucursalExistente));
    when(sucursalesServiceImpl.save(any(Sucursales.class))).thenReturn(sucursalActualizada);

    mockMvc.perform(put("/api/sucursales/7")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(sucursalActualizada)))
        .andExpect(status().isOk());
}

@Test
public void eliminarSucursalesTest() throws Exception {
    Sucursales sucursal = new Sucursales(8L, "Sucursal a eliminar", "Ubicación");

  
    when(sucursalesServiceImpl.delete(any(Sucursales.class)))
        .thenReturn(Optional.of(sucursal));

    mockMvc.perform(delete("/api/sucursales/8")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()); 
}
}
