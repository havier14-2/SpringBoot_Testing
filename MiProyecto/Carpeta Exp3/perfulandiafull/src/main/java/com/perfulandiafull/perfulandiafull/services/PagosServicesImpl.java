package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfulandiafull.perfulandiafull.entities.Pagos;
import com.perfulandiafull.perfulandiafull.repositories.PagosRepository;
@Service
public class PagosServicesImpl implements PagosServices {
      @Autowired
    private PagosRepository Pagosrepository;


    @Override
    @Transactional
    public Optional<Pagos> delete(Pagos unPagos) {
        Optional<Pagos> PagosOptional = Pagosrepository.findById(unPagos.getId());
        PagosOptional.ifPresent(PagosDb ->{ 
            Pagosrepository.delete(unPagos);
         });
        return PagosOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pagos> findByAll() {
        return (List<Pagos>) Pagosrepository.findAll();   //funcionalmente select * from tabla
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pagos> findById(Long id) {
        return Pagosrepository.findById(id);
    }

    @Override
    @Transactional
    public Pagos save(Pagos unPagos) {
        return Pagosrepository.save(unPagos);
    }


}
