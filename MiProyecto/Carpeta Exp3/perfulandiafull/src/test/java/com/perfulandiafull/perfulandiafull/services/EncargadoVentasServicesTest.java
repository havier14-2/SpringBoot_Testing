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

import com.perfulandiafull.perfulandiafull.entities.EncargadoVentas;
import com.perfulandiafull.perfulandiafull.repositories.EncargadoVentasRepository;

@SpringBootTest
public class EncargadoVentasServicesTest {

    @Mock
private EncargadoVentasRepository encargadoVentasRepositories;

@InjectMocks
private EncargadoVentasServicesImpl encargadoVentasServiceImpl;

private EncargadoVentas unEncargadoVentas;

@BeforeEach
void Inicio(){
    MockitoAnnotations.openMocks(this);
    unEncargadoVentas = new EncargadoVentas();
    unEncargadoVentas.setId(1L);
    unEncargadoVentas.setNombre("Audífonos Gamer HyperX");
}

@Test
void findByAllTest(){
    List<EncargadoVentas> unEncargadoVentasList = Arrays.asList(unEncargadoVentas);

    when(encargadoVentasRepositories.findAll()).thenReturn(unEncargadoVentasList);
    List<EncargadoVentas> resultado = encargadoVentasServiceImpl.findByAll();

    assertEquals(1, resultado.size());
    verify(encargadoVentasRepositories).findAll();
}

@Test
void findByIdFoundTest(){
    when(encargadoVentasRepositories.findById(1L)).thenReturn(Optional.of(unEncargadoVentas));
    Optional<EncargadoVentas> resultado = encargadoVentasServiceImpl.findById(1L);
    assertTrue(resultado.isPresent());
    assertEquals("Audífonos Gamer HyperX", resultado.get().getNombre());
    verify(encargadoVentasRepositories).findById(1L);
}

@Test
void SaveTest(){
    when(encargadoVentasRepositories.save(any(EncargadoVentas.class))).thenReturn(unEncargadoVentas);
    EncargadoVentas saved = encargadoVentasServiceImpl.save(unEncargadoVentas);
    assertNotNull(saved);
    assertEquals("Audífonos Gamer HyperX", saved.getNombre());
    verify(encargadoVentasRepositories).save(unEncargadoVentas);
}

@Test
void deleteTest() {
    when(encargadoVentasRepositories.findById(1L)).thenReturn(Optional.of(unEncargadoVentas));

    Optional<EncargadoVentas> eliminado = encargadoVentasServiceImpl.delete(unEncargadoVentas);

    assertTrue(eliminado.isPresent());
    assertEquals("Audífonos Gamer HyperX", eliminado.get().getNombre());
    verify(encargadoVentasRepositories).findById(1L);
    verify(encargadoVentasRepositories).delete(unEncargadoVentas);
}

}
