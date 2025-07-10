package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfulandiafull.perfulandiafull.entities.Administrador;
import com.perfulandiafull.perfulandiafull.repositories.AdministradorRepository;
@Service
public class AdministradorServicesImpl implements AdministradorServices {
@Autowired
    private AdministradorRepository administradorrepository;
    
    @Override
    @Transactional
    public Optional<Administrador> delete(Administrador administrador) {
        Optional<Administrador> clienteOptional = administradorrepository.findById(administrador.getId());
        clienteOptional.ifPresent(administradorDb -> {
            administradorrepository.delete(administradorDb);
        });
        return clienteOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Administrador> findByAll() {
        return (List<Administrador>) administradorrepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Administrador> findById(Long id) {
        return administradorrepository.findById(id);
    }

    @Override
    @Transactional
    public Administrador save(Administrador administrador) {
        return administradorrepository.save(administrador);
    }
}
