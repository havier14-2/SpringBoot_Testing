package com.pagos.pagos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pagos.pagos.entities.Pagos;
import com.pagos.pagos.repositories.PagosRepository;

@Service
public class PagosServicesImpl implements PagosServices {

    @Autowired
private PagosRepository pagosRepository;

@Override
@Transactional
public Optional<Pagos> delete(Pagos unPago) {
    Optional<Pagos> pagoOptional = pagosRepository.findById(unPago.getId());
    pagoOptional.ifPresent(pagoDb -> {
        pagosRepository.delete(unPago);
    });
    return pagoOptional;
}

@Override
@Transactional(readOnly = true)
public List<Pagos> findByAll() {
    return (List<Pagos>) pagosRepository.findAll();
}

@Override
@Transactional(readOnly = true)
public Optional<Pagos> findById(Long id) {
    return pagosRepository.findById(id);
}

@Override
@Transactional
public Pagos save(Pagos unPago) {
    return pagosRepository.save(unPago);
}



}
