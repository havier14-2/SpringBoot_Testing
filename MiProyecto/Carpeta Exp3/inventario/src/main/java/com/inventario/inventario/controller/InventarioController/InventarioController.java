package com.inventario.inventario.controller.InventarioController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventario.inventario.entities.Inventario;
import com.inventario.inventario.repositories.InventarioRepositories;

@Controller
public class InventarioController {
    @Autowired
    InventarioRepositories inventarioRepository;

    @GetMapping("/inventarios")
    public String verInventarios(Model model){
        List<Inventario> inventarios = (List<Inventario>) inventarioRepository.findAll();
        model.addAttribute("inventarios", inventarios);
        return "inventarios";  
    }


}
