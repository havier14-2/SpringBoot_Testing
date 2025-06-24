package com.javier.springboot.ejemplito.perfulandia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javier.springboot.ejemplito.perfulandia.repositories.AdministradorRepository;
import com.javier.springboot.ejemplito.perfulandia.repositories.ClienteRepository;
import com.javier.springboot.ejemplito.perfulandia.repositories.EncargadoLogisticaRepository;
import com.javier.springboot.ejemplito.perfulandia.repositories.EncargadoVentasRepository;
import com.javier.springboot.ejemplito.perfulandia.repositories.GerenteRepository;


    @Controller
public class UsuarioController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private EncargadoVentasRepository encargadoVentasRepository;

    @Autowired
    private EncargadoLogisticaRepository encargadoLogisticaRepository;

    @GetMapping("/cliente")
    public String mostrarUsuarios(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("gerentes", gerenteRepository.findAll());
        model.addAttribute("administrador", administradorRepository.findAll());
        model.addAttribute("ventasas", encargadoVentasRepository.findAll());
        model.addAttribute("logisticas", encargadoLogisticaRepository.findAll());
        return "cliente";
    }
}



