package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import com.perfulandiafull.perfulandiafull.entities.EncargadoVentas;

public interface EncargadoVentasServices {
     Optional<EncargadoVentas> delete(EncargadoVentas encargadoventas);
    List<EncargadoVentas> findByAll();
    Optional<EncargadoVentas> findById(Long id);
    EncargadoVentas save(EncargadoVentas encargadoventas);


}
