package com.perfulandiafull.perfulandiafull.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.perfulandiafull.perfulandiafull.entities.Producto;
import com.perfulandiafull.perfulandiafull.repositories.ProductoRepository;

@SpringBootTest
public class ProductoServicesTest {

    @Mock
private ProductoRepository productoRepositories;

@InjectMocks
private ProductoServicesImpl productoServiceImpl;

private Producto unProducto;

@BeforeEach
void Inicio(){
    MockitoAnnotations.openMocks(this);
    unProducto = new Producto();
    unProducto.setId(1L);
    unProducto.setNombre("Audífonos Gamer HyperX");
}

@Test
void findByAllTest(){
    List<Producto> unProductoList = Arrays.asList(unProducto);

    when(productoRepositories.findAll()).thenReturn(unProductoList);
    List<Producto> resultado = productoServiceImpl.findByAll();

    assertEquals(1, resultado.size());
    verify(productoRepositories).findAll();
}

@Test
void findByIdFoundTest(){
    when(productoRepositories.findById(1L)).thenReturn(Optional.of(unProducto));
    Optional<Producto> resultado = productoServiceImpl.findById(1L);
    assertTrue(resultado.isPresent());
    assertEquals("Audífonos Gamer HyperX", resultado.get().getNombre());
    verify(productoRepositories).findById(1L);
}

@Test
void SaveTest(){
    when(productoRepositories.save(any(Producto.class))).thenReturn(unProducto);
    Producto saved = productoServiceImpl.save(unProducto);
    assertNotNull(saved);
    assertEquals("Audífonos Gamer HyperX", saved.getNombre());
    verify(productoRepositories).save(unProducto);
}

@Test
void deleteTest() {
    when(productoRepositories.findById(1L)).thenReturn(Optional.of(unProducto));

    Optional<Producto> eliminado = productoServiceImpl.delete(unProducto);

    assertTrue(eliminado.isPresent());
    assertEquals("Audífonos Gamer HyperX", eliminado.get().getNombre());
    verify(productoRepositories).findById(1L);
    verify(productoRepositories).delete(unProducto);
}

}
