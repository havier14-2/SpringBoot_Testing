package com.perfulandiafull.perfulandiafull.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.perfulandiafull.perfulandiafull.entities.Pagos;
import com.perfulandiafull.perfulandiafull.repositories.PagosRepository;
@Controller
public class PagosController {
    @Autowired
    PagosRepository Pagosrepository;

    @GetMapping("/pagos")
    public String verPagos(Model model){
        List<Pagos> paguitos = (List<Pagos>)Pagosrepository.findAll();
        model.addAttribute("paguitos", paguitos);
        return "pagos";
    } 

}
