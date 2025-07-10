package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import com.perfulandiafull.perfulandiafull.entities.EncargadoLogistica;

public interface EncargadoLogisticaServices {
    Optional<EncargadoLogistica> delete(EncargadoLogistica encargadologistica);
    List<EncargadoLogistica> findByAll();
    Optional<EncargadoLogistica> findById(Long id);
    EncargadoLogistica save(EncargadoLogistica encargadologistica);


}
