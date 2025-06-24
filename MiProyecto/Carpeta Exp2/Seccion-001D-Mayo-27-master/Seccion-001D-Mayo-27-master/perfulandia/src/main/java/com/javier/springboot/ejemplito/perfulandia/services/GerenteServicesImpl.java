package com.javier.springboot.ejemplito.perfulandia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javier.springboot.ejemplito.perfulandia.entities.Gerente;
import com.javier.springboot.ejemplito.perfulandia.repositories.GerenteRepository;
@Service
public class GerenteServicesImpl implements GerenteServices {
     @Autowired
    private GerenteRepository gerenterepository;

    @Override
    @Transactional

    public Optional<Gerente> delete(Gerente gerente) {
        Optional<Gerente> gerenteOptional = gerenterepository.findById(gerente.getId());
        gerenteOptional.ifPresent(gerenteDb -> {
            gerenterepository.delete(gerenteDb);
        });
        return gerenteOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gerente> findByAll() {
        return (List<Gerente>) gerenterepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Gerente> findById(Long id) {
        return gerenterepository.findById(id);
    }

    @Override
    @Transactional
    public Gerente save(Gerente gerente) {
        return gerenterepository.save(gerente);
    }


}
