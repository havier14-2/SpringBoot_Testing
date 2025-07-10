package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfulandiafull.perfulandiafull.entities.Inventario;
import com.perfulandiafull.perfulandiafull.repositories.InventarioRepository;
@Service
public class InventarioServicesImpl  implements InventarioServices {
      @Autowired
    private InventarioRepository Inventariorepository;


    @Override
    @Transactional
    public Optional<Inventario> delete(Inventario unInventario) {
        Optional<Inventario> InventarioOptional = Inventariorepository.findById(unInventario.getId());
        InventarioOptional.ifPresent(InventarioDb ->{ 
           Inventariorepository.delete(unInventario);
         });
        return InventarioOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Inventario> findByAll() {
        return (List<Inventario>) Inventariorepository.findAll();   //funcionalmente select * from tabla
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Inventario> findById(Long id) {
        return Inventariorepository.findById(id);
    }

    @Override
    @Transactional
    public Inventario save(Inventario unInventario) {
        return Inventariorepository.save(unInventario);
    }


}
