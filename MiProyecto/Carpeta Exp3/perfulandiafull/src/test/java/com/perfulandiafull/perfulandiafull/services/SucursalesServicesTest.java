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

import com.perfulandiafull.perfulandiafull.entities.Sucursales;
import com.perfulandiafull.perfulandiafull.repositories.SucursalesRepository;

@SpringBootTest
public class SucursalesServicesTest {

    @Mock
private SucursalesRepository sucursalesRepositories;

@InjectMocks
private SucursalesServicesImpl sucursalesServiceImpl;

private Sucursales unSucursales;

@BeforeEach
void Inicio(){
    MockitoAnnotations.openMocks(this);
    unSucursales = new Sucursales();
    unSucursales.setId(1L);
    unSucursales.setNombre("Sucursal Principal");
}

@Test
void findByAllTest(){
    List<Sucursales> unSucursalesList = Arrays.asList(unSucursales);

    when(sucursalesRepositories.findAll()).thenReturn(unSucursalesList);
    List<Sucursales> resultado = sucursalesServiceImpl.findByAll();

    assertEquals(1, resultado.size());
    verify(sucursalesRepositories).findAll();
}

@Test
void findByIdFoundTest(){
    when(sucursalesRepositories.findById(1L)).thenReturn(Optional.of(unSucursales));
    Optional<Sucursales> resultado = sucursalesServiceImpl.findById(1L);
    assertTrue(resultado.isPresent());
    assertEquals("Sucursal Principal", resultado.get().getNombre());
    verify(sucursalesRepositories).findById(1L);
}

@Test
void SaveTest(){
    when(sucursalesRepositories.save(any(Sucursales.class))).thenReturn(unSucursales);
    Sucursales saved = sucursalesServiceImpl.save(unSucursales);
    assertNotNull(saved);
    assertEquals("Sucursal Principal", saved.getNombre());
    verify(sucursalesRepositories).save(unSucursales);
}

@Test
void deleteTest() {
    when(sucursalesRepositories.findById(1L)).thenReturn(Optional.of(unSucursales));

    Optional<Sucursales> eliminado = sucursalesServiceImpl.delete(unSucursales);

    assertTrue(eliminado.isPresent());
    assertEquals("Sucursal Principal", eliminado.get().getNombre());
    verify(sucursalesRepositories).findById(1L);
    verify(sucursalesRepositories).delete(unSucursales);
}

}
