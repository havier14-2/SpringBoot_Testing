package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import com.perfulandiafull.perfulandiafull.entities.Sucursales;

public interface SucursalesServices {
Optional<Sucursales> delete(Sucursales sucursal);
    List<Sucursales> findByAll();
    Optional<Sucursales> findById(Long id);
    Sucursales save(Sucursales sucursal);

}
