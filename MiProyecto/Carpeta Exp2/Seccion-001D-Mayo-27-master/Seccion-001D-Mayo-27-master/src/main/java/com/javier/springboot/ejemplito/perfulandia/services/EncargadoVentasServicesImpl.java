package com.javier.springboot.ejemplito.perfulandia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javier.springboot.ejemplito.perfulandia.entities.EncargadoVentas;
import com.javier.springboot.ejemplito.perfulandia.entities.Gerente;
import com.javier.springboot.ejemplito.perfulandia.repositories.EncargadoVentasRepository;
import com.javier.springboot.ejemplito.perfulandia.repositories.GerenteRepository;
@Service
public class EncargadoVentasServicesImpl implements EncargadoVentasServices {
      @Autowired
    private EncargadoVentasRepository encargadoventasrepository;

    @Override
    @Transactional

    public Optional<EncargadoVentas> delete(EncargadoVentas encargadoventas) {
        Optional<EncargadoVentas> encargadoventasOptional = encargadoventasrepository.findById(encargadoventas.getId());
        encargadoventasOptional.ifPresent(encargadoventasDb -> {
            encargadoventasrepository.delete(encargadoventasDb);
        });
        return encargadoventasOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EncargadoVentas> findByAll() {
        return (List<EncargadoVentas>) encargadoventasrepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EncargadoVentas> findById(Long id) {
        return encargadoventasrepository.findById(id);
    }

    @Override
    @Transactional
    public EncargadoVentas save(EncargadoVentas encargadoventas) {
        return encargadoventasrepository.save(encargadoventas);
    }


}
