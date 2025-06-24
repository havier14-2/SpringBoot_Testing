package com.sucursales.sucursales.sevices;

import java.util.List;
import java.util.Optional;

import com.sucursales.sucursales.entities.Sucursal;

public interface SucursalesServices {
    Optional<Sucursal> delete(Sucursal sucursal);
    List<Sucursal> findByAll();
    Optional<Sucursal> findById(Long id);
    Sucursal save(Sucursal sucursal);


}
