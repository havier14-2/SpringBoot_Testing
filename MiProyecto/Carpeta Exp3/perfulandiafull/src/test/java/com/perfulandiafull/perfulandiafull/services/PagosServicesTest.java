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

import com.perfulandiafull.perfulandiafull.entities.Pagos;
import com.perfulandiafull.perfulandiafull.repositories.PagosRepository;

@SpringBootTest
public class PagosServicesTest {

    @Mock
private PagosRepository pagosRepositories;

@InjectMocks
private PagosServicesImpl pagosServiceImpl;

private Pagos unPagos;

@BeforeEach
void Inicio(){
    MockitoAnnotations.openMocks(this);
    unPagos = new Pagos();
    unPagos.setId(1L);
    unPagos.setNombre("Audífonos Gamer HyperX");
}

@Test
void findByAllTest(){
    List<Pagos> unPagosList = Arrays.asList(unPagos);

    when(pagosRepositories.findAll()).thenReturn(unPagosList);
    List<Pagos> resultado = pagosServiceImpl.findByAll();

    assertEquals(1, resultado.size());
    verify(pagosRepositories).findAll();
}

@Test
void findByIdFoundTest(){
    when(pagosRepositories.findById(1L)).thenReturn(Optional.of(unPagos));
    Optional<Pagos> resultado = pagosServiceImpl.findById(1L);
    assertTrue(resultado.isPresent());
    assertEquals("Audífonos Gamer HyperX", resultado.get().getNombre());
    verify(pagosRepositories).findById(1L);
}

@Test
void SaveTest(){
    when(pagosRepositories.save(any(Pagos.class))).thenReturn(unPagos);
    Pagos saved = pagosServiceImpl.save(unPagos);
    assertNotNull(saved);
    assertEquals("Audífonos Gamer HyperX", saved.getNombre());
    verify(pagosRepositories).save(unPagos);
}

@Test
void deleteTest() {
    when(pagosRepositories.findById(1L)).thenReturn(Optional.of(unPagos));

    Optional<Pagos> eliminado = pagosServiceImpl.delete(unPagos);

    assertTrue(eliminado.isPresent());
    assertEquals("Audífonos Gamer HyperX", eliminado.get().getNombre());
    verify(pagosRepositories).findById(1L);
    verify(pagosRepositories).delete(unPagos);
}

}
