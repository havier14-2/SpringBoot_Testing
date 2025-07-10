package com.perfulandiafull.perfulandiafull.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.perfulandiafull.perfulandiafull.entities.Sucursales;
import com.perfulandiafull.perfulandiafull.repositories.SucursalesRepository;
@Controller
public class SucursalesController {
     @Autowired
    SucursalesRepository sucursalRepository;

    @GetMapping("/sucursales")
    public String verSucursales(Model model){
        List<Sucursales> sucursales = (List<Sucursales>) sucursalRepository.findAll();
        model.addAttribute("sucursales", sucursales);
        return "sucursales";  
    }



}
