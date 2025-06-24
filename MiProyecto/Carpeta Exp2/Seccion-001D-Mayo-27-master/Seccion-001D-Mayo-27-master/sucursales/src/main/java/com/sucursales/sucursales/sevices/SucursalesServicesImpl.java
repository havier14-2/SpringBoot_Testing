package com.sucursales.sucursales.sevices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sucursales.sucursales.entities.Sucursal;
import com.sucursales.sucursales.repositories.SucursalesRepositories;
@Service
public class SucursalesServicesImpl implements SucursalesServices {
    @Autowired
    private SucursalesRepositories sucursalRepository;

@Override
@Transactional
public Optional<Sucursal> delete(Sucursal unaSucursal) {
    Optional<Sucursal> sucursalOptional = sucursalRepository.findById(unaSucursal.getId());
    sucursalOptional.ifPresent(sucursalDb -> { 
        sucursalRepository.delete(unaSucursal);
    });
    return sucursalOptional;
}

@Override
@Transactional(readOnly = true)
public List<Sucursal> findByAll() {
    return (List<Sucursal>) sucursalRepository.findAll(); // select * from sucursal
}

@Override
@Transactional(readOnly = true)
public Optional<Sucursal> findById(Long id) {
    return sucursalRepository.findById(id);
}

@Override
@Transactional
public Sucursal save(Sucursal unaSucursal) {
    return sucursalRepository.save(unaSucursal);
}
    
}
