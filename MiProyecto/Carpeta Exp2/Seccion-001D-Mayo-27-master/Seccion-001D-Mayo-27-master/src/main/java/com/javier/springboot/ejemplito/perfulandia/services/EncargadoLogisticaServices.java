package com.javier.springboot.ejemplito.perfulandia.services;

import java.util.List;
import java.util.Optional;

import com.javier.springboot.ejemplito.perfulandia.entities.EncargadoLogistica;

public interface EncargadoLogisticaServices {
    Optional<EncargadoLogistica> delete(EncargadoLogistica encargadologistica);
    List<EncargadoLogistica> findByAll();
    Optional<EncargadoLogistica> findById(Long id);
    EncargadoLogistica save(EncargadoLogistica encargadologistica);

}
