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

import com.perfulandiafull.perfulandiafull.entities.Pedidos;
import com.perfulandiafull.perfulandiafull.repositories.PedidosRepository;

@SpringBootTest
public class PedidosServicesTest {
    @Mock
private PedidosRepository pedidosRepositories;

@InjectMocks
private PedidosServicesImpl pedidosServiceImpl;

private Pedidos unPedidos;

@BeforeEach
void Inicio(){
    MockitoAnnotations.openMocks(this);
    unPedidos = new Pedidos();
    unPedidos.setId(1L);
    unPedidos.setNombre("Audífonos Gamer HyperX");
}

@Test
void findByAllTest(){
    List<Pedidos> unPedidosList = Arrays.asList(unPedidos);

    when(pedidosRepositories.findAll()).thenReturn(unPedidosList);
    List<Pedidos> resultado = pedidosServiceImpl.findByAll();

    assertEquals(1, resultado.size());
    verify(pedidosRepositories).findAll();
}

@Test
void findByIdFoundTest(){
    when(pedidosRepositories.findById(1L)).thenReturn(Optional.of(unPedidos));
    Optional<Pedidos> resultado = pedidosServiceImpl.findById(1L);
    assertTrue(resultado.isPresent());
    assertEquals("Audífonos Gamer HyperX", resultado.get().getNombre());
    verify(pedidosRepositories).findById(1L);
}

@Test
void SaveTest(){
    when(pedidosRepositories.save(any(Pedidos.class))).thenReturn(unPedidos);
    Pedidos saved = pedidosServiceImpl.save(unPedidos);
    assertNotNull(saved);
    assertEquals("Audífonos Gamer HyperX", saved.getNombre());
    verify(pedidosRepositories).save(unPedidos);
}

@Test
void deleteTest() {
    when(pedidosRepositories.findById(1L)).thenReturn(Optional.of(unPedidos));

    Optional<Pedidos> eliminado = pedidosServiceImpl.delete(unPedidos);

    assertTrue(eliminado.isPresent());
    assertEquals("Audífonos Gamer HyperX", eliminado.get().getNombre());
    verify(pedidosRepositories).findById(1L);
    verify(pedidosRepositories).delete(unPedidos);
}

}
