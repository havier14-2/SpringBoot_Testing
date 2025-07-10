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

import com.perfulandiafull.perfulandiafull.entities.Inventario;
import com.perfulandiafull.perfulandiafull.repositories.InventarioRepository;


@SpringBootTest
public class InventarioServicesTest {

      @Mock
    private InventarioRepository inventarioRepositories;

    @InjectMocks
    private InventarioServicesImpl inventarioServiceImpl;

    private Inventario unInventario;

    @BeforeEach
    void Inicio(){
MockitoAnnotations.openMocks(this);
        unInventario = new Inventario();
        unInventario.setId(1L);
        unInventario.setNombre("Audífonos Gamer HyperX");

    }

    @Test
    void findByAllTest(){


        List <Inventario> unInventaro = Arrays.asList(unInventario);

        when(inventarioRepositories.findAll()).thenReturn(unInventaro);
        List <Inventario> resultado = inventarioServiceImpl.findByAll();

        assertEquals(1, resultado.size());
        verify(inventarioRepositories).findAll();
    }

    @Test
    void findByIdFoundTest(){
        when(inventarioRepositories.findById(1L)).thenReturn(Optional.of(unInventario));
        Optional<Inventario> resultado = inventarioServiceImpl.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Audífonos Gamer HyperX", resultado.get().getNombre());
        verify(inventarioRepositories).findById(1L);
    }

    @Test
    void SaveTest(){
        when(inventarioRepositories.save(any(Inventario.class))).thenReturn(unInventario);
       Inventario saved = inventarioServiceImpl.save(unInventario);
       assertNotNull(saved);
        assertEquals("Audífonos Gamer HyperX", saved.getNombre());
        verify(inventarioRepositories).save(unInventario);
    }

    @Test
void deleteTest() {
    when(inventarioRepositories.findById(1L)).thenReturn(Optional.of(unInventario));

    Optional<Inventario> eliminado = inventarioServiceImpl.delete(unInventario);

    assertTrue(eliminado.isPresent());
    assertEquals("Audífonos Gamer HyperX", eliminado.get().getNombre());
    verify(inventarioRepositories).findById(1L);
    verify(inventarioRepositories).delete(unInventario);

}

}
