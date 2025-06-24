package com.javier.springboot.ejemplito.perfulandia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javier.springboot.ejemplito.perfulandia.entities.Administrador;
import com.javier.springboot.ejemplito.perfulandia.entities.EncargadoLogistica;
import com.javier.springboot.ejemplito.perfulandia.repositories.AdministradorRepository;
import com.javier.springboot.ejemplito.perfulandia.repositories.EncargadoLogisticaRepository;

@Service

public class EncargadoLogisticaServicesImpl implements EncargadoLogisticaServices {
    @Autowired
    private EncargadoLogisticaRepository encargadoLogisticaRepository;
    
    @Override
    @Transactional
    public Optional<EncargadoLogistica> delete(EncargadoLogistica encargadoLogistica) {
        Optional<EncargadoLogistica> encargadoLogisticaOptional = encargadoLogisticaRepository.findById(encargadoLogistica.getId());
        encargadoLogisticaOptional.ifPresent(encargadoLogisticaDb -> {
            encargadoLogisticaRepository.delete(encargadoLogisticaDb);
        });
        return encargadoLogisticaOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EncargadoLogistica> findByAll() {
        return (List<EncargadoLogistica>) encargadoLogisticaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EncargadoLogistica> findById(Long id) {
        return encargadoLogisticaRepository.findById(id);
    }

    @Override
    @Transactional
    public EncargadoLogistica save(EncargadoLogistica encargadoLogistica) {
        return encargadoLogisticaRepository.save(encargadoLogistica);
    }

}
