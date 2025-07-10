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

import com.perfulandiafull.perfulandiafull.entities.EncargadoLogistica;
import com.perfulandiafull.perfulandiafull.repositories.EncargadoLogisticaRepository;


@SpringBootTest
public class EncargadoLogisticaServicesTest {

    @Mock
private EncargadoLogisticaRepository encargadoLogisticaRepositories;

@InjectMocks
private EncargadoLogisticaServicesImpl encargadoLogisticaServiceImpl;

private EncargadoLogistica unEncargadoLogistica;

@BeforeEach
void Inicio(){
    MockitoAnnotations.openMocks(this);
    unEncargadoLogistica = new EncargadoLogistica();
    unEncargadoLogistica.setId(1L);
    unEncargadoLogistica.setNombre("Audífonos Gamer HyperX");
}

@Test
void findByAllTest(){
    List<EncargadoLogistica> unEncargadoLogisticaList = Arrays.asList(unEncargadoLogistica);

    when(encargadoLogisticaRepositories.findAll()).thenReturn(unEncargadoLogisticaList);
    List<EncargadoLogistica> resultado = encargadoLogisticaServiceImpl.findByAll();

    assertEquals(1, resultado.size());
    verify(encargadoLogisticaRepositories).findAll();
}

@Test
void findByIdFoundTest(){
    when(encargadoLogisticaRepositories.findById(1L)).thenReturn(Optional.of(unEncargadoLogistica));
    Optional<EncargadoLogistica> resultado = encargadoLogisticaServiceImpl.findById(1L);
    assertTrue(resultado.isPresent());
    assertEquals("Audífonos Gamer HyperX", resultado.get().getNombre());
    verify(encargadoLogisticaRepositories).findById(1L);
}

@Test
void SaveTest(){
    when(encargadoLogisticaRepositories.save(any(EncargadoLogistica.class))).thenReturn(unEncargadoLogistica);
    EncargadoLogistica saved = encargadoLogisticaServiceImpl.save(unEncargadoLogistica);
    assertNotNull(saved);
    assertEquals("Audífonos Gamer HyperX", saved.getNombre());
    verify(encargadoLogisticaRepositories).save(unEncargadoLogistica);
}

@Test
void deleteTest() {
    when(encargadoLogisticaRepositories.findById(1L)).thenReturn(Optional.of(unEncargadoLogistica));

    Optional<EncargadoLogistica> eliminado = encargadoLogisticaServiceImpl.delete(unEncargadoLogistica);

    assertTrue(eliminado.isPresent());
    assertEquals("Audífonos Gamer HyperX", eliminado.get().getNombre());
    verify(encargadoLogisticaRepositories).findById(1L);
    verify(encargadoLogisticaRepositories).delete(unEncargadoLogistica);
}

}
