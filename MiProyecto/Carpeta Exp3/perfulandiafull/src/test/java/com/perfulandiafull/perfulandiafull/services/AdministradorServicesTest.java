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

import com.perfulandiafull.perfulandiafull.entities.Administrador;
import com.perfulandiafull.perfulandiafull.repositories.AdministradorRepository;


@SpringBootTest

public class AdministradorServicesTest {

    @Mock
private AdministradorRepository administradorRepositories;

@InjectMocks
private AdministradorServicesImpl administradorServiceImpl;

private Administrador unAdministrador;

@BeforeEach
void Inicio(){
    MockitoAnnotations.openMocks(this);
    unAdministrador = new Administrador();
    unAdministrador.setId(1L);
    unAdministrador.setNombre("Audífonos Gamer HyperX");
}

@Test
void findByAllTest(){
    List<Administrador> unAdministradorList = Arrays.asList(unAdministrador);

    when(administradorRepositories.findAll()).thenReturn(unAdministradorList);
    List<Administrador> resultado = administradorServiceImpl.findByAll();

    assertEquals(1, resultado.size());
    verify(administradorRepositories).findAll();
}

@Test
void findByIdFoundTest(){
    when(administradorRepositories.findById(1L)).thenReturn(Optional.of(unAdministrador));
    Optional<Administrador> resultado = administradorServiceImpl.findById(1L);
    assertTrue(resultado.isPresent());
    assertEquals("Audífonos Gamer HyperX", resultado.get().getNombre());
    verify(administradorRepositories).findById(1L);
}

@Test
void SaveTest(){
    when(administradorRepositories.save(any(Administrador.class))).thenReturn(unAdministrador);
    Administrador saved = administradorServiceImpl.save(unAdministrador);
    assertNotNull(saved);
    assertEquals("Audífonos Gamer HyperX", saved.getNombre());
    verify(administradorRepositories).save(unAdministrador);
}

@Test
void deleteTest() {
    when(administradorRepositories.findById(1L)).thenReturn(Optional.of(unAdministrador));

    Optional<Administrador> eliminado = administradorServiceImpl.delete(unAdministrador);

    assertTrue(eliminado.isPresent());
    assertEquals("Audífonos Gamer HyperX", eliminado.get().getNombre());
    verify(administradorRepositories).findById(1L);
    verify(administradorRepositories).delete(unAdministrador);
}

}
