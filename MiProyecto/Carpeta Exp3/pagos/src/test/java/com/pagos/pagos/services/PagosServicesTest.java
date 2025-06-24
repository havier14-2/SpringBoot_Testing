package com.pagos.pagos.services;

import org.springframework.boot.test.context.SpringBootTest;

import com.pagos.pagos.entities.Pagos;
import com.pagos.pagos.repositories.PagosRepository;

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




@SpringBootTest
public class PagosServicesTest {

     @Mock
    private PagosRepository pagosRepositories;

    @InjectMocks
    private PagosServicesImpl pagosServiceImpl;

    private Pagos unPago;

    @BeforeEach
    void Inicio(){
MockitoAnnotations.openMocks(this);
        unPago = new Pagos();
        unPago.setId(1L);
        unPago.setDescripcion("Audífonos Gamer HyperX");
        unPago.setMonto(150000);

    }

    @Test
    void findByAllTest(){


        List <Pagos> unPagos = Arrays.asList(unPago);

        when(pagosRepositories.findAll()).thenReturn(unPagos);
        List <Pagos> resultado = pagosServiceImpl.findByAll();

        assertEquals(1, resultado.size());
        verify(pagosRepositories).findAll();
    }

    @Test
    void findByIdFoundTest(){
        when(pagosRepositories.findById(1L)).thenReturn(Optional.of(unPago));
        Optional<Pagos> resultado = pagosServiceImpl.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Audífonos Gamer HyperX", resultado.get().getDescripcion());
        verify(pagosRepositories).findById(1L);
    }

    @Test
    void SaveTest(){
        when(pagosRepositories.save(any(Pagos.class))).thenReturn(unPago);
       Pagos saved = pagosServiceImpl.save(unPago);
       assertNotNull(saved);
        assertEquals("Audífonos Gamer HyperX", saved.getDescripcion());
        verify(pagosRepositories).save(unPago);
    }

}

