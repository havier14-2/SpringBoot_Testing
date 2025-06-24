package com.inventario.inventario.services;

import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;


import com.inventario.inventario.entities.Inventario;
import com.inventario.inventario.repositories.InventarioRepositories;

@SpringBootTest
public class InventarioServicesTest {

     @Mock
    private InventarioRepositories inventarioRepositories;

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

}
