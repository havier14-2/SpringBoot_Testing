package com.javier.springboot.ejemplito.perfulandia.services;

import java.util.List;
import java.util.Optional;

import com.javier.springboot.ejemplito.perfulandia.entities.EncargadoVentas;

public interface EncargadoVentasServices {
    Optional<EncargadoVentas> delete(EncargadoVentas encargadoventas);
    List<EncargadoVentas> findByAll();
    Optional<EncargadoVentas> findById(Long id);
    EncargadoVentas save(EncargadoVentas encargadoventas);


}
