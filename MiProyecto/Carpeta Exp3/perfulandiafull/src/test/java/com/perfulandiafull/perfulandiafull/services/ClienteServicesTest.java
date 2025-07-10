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

import com.perfulandiafull.perfulandiafull.entities.Cliente;
import com.perfulandiafull.perfulandiafull.repositories.ClienteRepository;


@SpringBootTest
public class ClienteServicesTest {

    @Mock
private ClienteRepository clienteRepositories;

@InjectMocks
private ClienteServicesImpl clienteServiceImpl;

private Cliente unCliente;

@BeforeEach
void Inicio(){
    MockitoAnnotations.openMocks(this);
    unCliente = new Cliente();
    unCliente.setId(1L);
    unCliente.setNombre("Audífonos Gamer HyperX");
}

@Test
void findByAllTest(){
    List<Cliente> unClienteList = Arrays.asList(unCliente);

    when(clienteRepositories.findAll()).thenReturn(unClienteList);
    List<Cliente> resultado = clienteServiceImpl.findByAll();

    assertEquals(1, resultado.size());
    verify(clienteRepositories).findAll();
}

@Test
void findByIdFoundTest(){
    when(clienteRepositories.findById(1L)).thenReturn(Optional.of(unCliente));
    Optional<Cliente> resultado = clienteServiceImpl.findById(1L);
    assertTrue(resultado.isPresent());
    assertEquals("Audífonos Gamer HyperX", resultado.get().getNombre());
    verify(clienteRepositories).findById(1L);
}

@Test
void SaveTest(){
    when(clienteRepositories.save(any(Cliente.class))).thenReturn(unCliente);
    Cliente saved = clienteServiceImpl.save(unCliente);
    assertNotNull(saved);
    assertEquals("Audífonos Gamer HyperX", saved.getNombre());
    verify(clienteRepositories).save(unCliente);
}

@Test
void deleteTest() {
    when(clienteRepositories.findById(1L)).thenReturn(Optional.of(unCliente));

    Optional<Cliente> eliminado = clienteServiceImpl.delete(unCliente);

    assertTrue(eliminado.isPresent());
    assertEquals("Audífonos Gamer HyperX", eliminado.get().getNombre());
    verify(clienteRepositories).findById(1L);
    verify(clienteRepositories).delete(unCliente);
}

}
