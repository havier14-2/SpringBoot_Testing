package com.pedidos.pedidos.services;

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



import com.pedidos.pedidos.entities.Pedidos;
import com.pedidos.pedidos.respositories.PedidosRepository;

@SpringBootTest
public class PedidosServicesTest {

     @Mock
    private PedidosRepository pedidosRepositories;

    @InjectMocks
    private PedidosServicesImpl pedidosServiceImpl;

    private Pedidos unPedido;

    @BeforeEach
    void Inicio(){
MockitoAnnotations.openMocks(this);
        unPedido = new Pedidos();
        unPedido.setId(1L);
        unPedido.setCliente("Juan Perez");
        unPedido.setProduto("Audífonos Gamer HyperX");
       

    }

    @Test
    void findByAllTest(){


        List <Pedidos> unPedidoList = Arrays.asList(unPedido);

        when(pedidosRepositories.findAll()).thenReturn(unPedidoList);
        List <Pedidos> resultado = pedidosServiceImpl.findByAll();

        assertEquals(1, resultado.size());
        verify(pedidosRepositories).findAll();
    }

    @Test
    void findByIdFoundTest(){
        when(pedidosRepositories.findById(1L)).thenReturn(Optional.of(unPedido));
        Optional<Pedidos> resultado = pedidosServiceImpl.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Audífonos Gamer HyperX", resultado.get().getProduto());
        verify(pedidosRepositories).findById(1L);
    }

    @Test
    void SaveTest(){
        when(pedidosRepositories.save(any(Pedidos.class))).thenReturn(unPedido);
       Pedidos saved = pedidosServiceImpl.save(unPedido);
       assertNotNull(saved);
        assertEquals("Audífonos Gamer HyperX", saved.getProduto());
        verify(pedidosRepositories).save(unPedido);
    }

}
