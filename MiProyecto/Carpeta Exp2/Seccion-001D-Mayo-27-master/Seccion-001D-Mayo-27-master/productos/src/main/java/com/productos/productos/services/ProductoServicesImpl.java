package com.productos.productos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.productos.productos.entities.Producto;
import com.productos.productos.repositories.ProductoRepository;
@Service
public class ProductoServicesImpl implements ProductoServices {
    
    @Autowired
    private ProductoRepository productorepository;


    @Override
    @Transactional
    public Optional<Producto> delete(Producto unProducto) {
        Optional<Producto> productoOptional = productorepository.findById(unProducto.getId());
        productoOptional.ifPresent(productoDb ->{ 
            productorepository.delete(unProducto);
         });
        return productoOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByAll() {
        return (List<Producto>) productorepository.findAll();   //funcionalmente select * from tabla
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Long id) {
        return productorepository.findById(id);
    }

    @Override
    @Transactional
    public Producto save(Producto unProducto) {
        return productorepository.save(unProducto);
    }


    


}
