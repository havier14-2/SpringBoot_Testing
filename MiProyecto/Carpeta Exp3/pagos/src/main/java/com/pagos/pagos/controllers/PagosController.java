package com.pagos.pagos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pagos.pagos.entities.Pagos;
import com.pagos.pagos.repositories.PagosRepository;

@Controller
public class PagosController {
    @Autowired
    PagosRepository pagosRepository;

@GetMapping("/pagos")
public String verPagos(Model model){
    List<Pagos> pagos = (List<Pagos>) pagosRepository.findAll();
    model.addAttribute("pagos", pagos);
    return "pagos";  
}

}
