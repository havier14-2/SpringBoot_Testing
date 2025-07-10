package com.perfulandiafull.perfulandiafull.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.perfulandiafull.perfulandiafull.entities.Inventario;
import com.perfulandiafull.perfulandiafull.repositories.InventarioRepository;
@Controller
public class InventarioController {
    @Autowired
    InventarioRepository Inventariorepository;

    @GetMapping("/inventarios")
    public String verInventario(Model model){
        List<Inventario> inventaritos = (List<Inventario>) Inventariorepository.findAll();
        model.addAttribute("inventaritos", inventaritos);
        return "inventarios";
    } 

}
