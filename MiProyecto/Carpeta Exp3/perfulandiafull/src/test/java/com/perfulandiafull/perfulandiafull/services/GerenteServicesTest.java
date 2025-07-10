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

import com.perfulandiafull.perfulandiafull.entities.Gerente;
import com.perfulandiafull.perfulandiafull.repositories.GerenteRepository;


@SpringBootTest
public class GerenteServicesTest {

    @Mock
private GerenteRepository gerenteRepositories;

@InjectMocks
private GerenteServicesImpl gerenteServiceImpl;

private Gerente unGerente;

@BeforeEach
void Inicio(){
    MockitoAnnotations.openMocks(this);
    unGerente = new Gerente();
    unGerente.setId(1L);
    unGerente.setNombre("Audífonos Gamer HyperX");
}

@Test
void findByAllTest(){
    List<Gerente> unGerenteList = Arrays.asList(unGerente);

    when(gerenteRepositories.findAll()).thenReturn(unGerenteList);
    List<Gerente> resultado = gerenteServiceImpl.findByAll();

    assertEquals(1, resultado.size());
    verify(gerenteRepositories).findAll();
}

@Test
void findByIdFoundTest(){
    when(gerenteRepositories.findById(1L)).thenReturn(Optional.of(unGerente));
    Optional<Gerente> resultado = gerenteServiceImpl.findById(1L);
    assertTrue(resultado.isPresent());
    assertEquals("Audífonos Gamer HyperX", resultado.get().getNombre());
    verify(gerenteRepositories).findById(1L);
}

@Test
void SaveTest(){
    when(gerenteRepositories.save(any(Gerente.class))).thenReturn(unGerente);
    Gerente saved = gerenteServiceImpl.save(unGerente);
    assertNotNull(saved);
    assertEquals("Audífonos Gamer HyperX", saved.getNombre());
    verify(gerenteRepositories).save(unGerente);
}

@Test
void deleteTest() {
    when(gerenteRepositories.findById(1L)).thenReturn(Optional.of(unGerente));

    Optional<Gerente> eliminado = gerenteServiceImpl.delete(unGerente);

    assertTrue(eliminado.isPresent());
    assertEquals("Audífonos Gamer HyperX", eliminado.get().getNombre());
    verify(gerenteRepositories).findById(1L);
    verify(gerenteRepositories).delete(unGerente);
}

}
