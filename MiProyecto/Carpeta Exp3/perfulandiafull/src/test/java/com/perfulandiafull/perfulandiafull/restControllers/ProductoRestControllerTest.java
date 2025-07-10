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
import com.perfulandiafull.perfulandiafull.entities.Producto;
import com.perfulandiafull.perfulandiafull.services.ProductoServicesImpl;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductoRestControllerTest {

    @Autowired 
private MockMvc mockMvc;

@MockitoBean
private ProductoServicesImpl productoServiceImpl;        //mocks, objeto simulado

@Autowired
private ObjectMapper objectMapper;              //atributo para gestionar el método save

List<Producto> listaProducto;


@Test
public void verProducto() throws Exception{
    when(productoServiceImpl.findByAll()).thenReturn(listaProducto);
    mockMvc.perform(get("/api/productos")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
}


@Test
public void verUnProducto(){
    Producto unProducto = new Producto(4L, "Sucursal", "Direccion",600.0);
    try{
        when(productoServiceImpl.findById(4L)).thenReturn(Optional.of(unProducto));
        mockMvc.perform(get("/api/productos/4")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }catch (Exception ex){
        fail("Error.. en el test " + ex.getMessage());
    }
}

@Test
public void productoNoExiste() throws Exception{
    when(productoServiceImpl.findById(10L)).thenReturn(Optional.empty());
    mockMvc.perform(get("/api/productos/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
}


@Test
public void crearProductoTest() throws Exception{
    Producto unProducto = new Producto(5L, "Sucursal", "Dirección",600.0);
    when(productoServiceImpl.save(any(Producto.class))).thenReturn(unProducto);
    Producto otroProducto = new Producto(6L, "Sucursal1", "Dirección1",600.0);
    when (productoServiceImpl.save(any(Producto.class))).thenReturn(otroProducto);
    mockMvc.perform(post("/api/productos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unProducto)))
        .andExpect(status().isCreated());
}

@Test
public void actualizarProductoTest() throws Exception {
    Producto productoExistente = new Producto(7L, "Sucursal Antiguo", "Dirección antigua",600.0);
    Producto productoActualizada = new Producto(7L, "Sucursal Nueva", "Dirección nueva",600.0);

    when(productoServiceImpl.findById(7L)).thenReturn(Optional.of(productoExistente));
    when(productoServiceImpl.save(any(Producto.class))).thenReturn(productoActualizada);

    mockMvc.perform(put("/api/productos/7")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(productoActualizada)))
        .andExpect(status().isOk());
}

@Test
public void eliminarProductoTest() throws Exception {
    Producto producto = new Producto(8L, "Sucursal a eliminar", "Ubicación",600.0);

    when(productoServiceImpl.delete(any(Producto.class)))
        .thenReturn(Optional.of(producto));

    mockMvc.perform(delete("/api/productos/8")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()); 
}

}
