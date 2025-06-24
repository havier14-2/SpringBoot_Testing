package com.sucursales.sucursales.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sucursales.sucursales.entities.Sucursal;
import com.sucursales.sucursales.repositories.SucursalesRepositories;
@Controller
public class SucursalesControllers {
    @Autowired
    SucursalesRepositories sucursalRepository;

    @GetMapping("/sucursales")
    public String verSucursales(Model model){
        List<Sucursal> sucursales = (List<Sucursal>) sucursalRepository.findAll();
        model.addAttribute("sucursales", sucursales);
        return "sucursales";  
    }


}
