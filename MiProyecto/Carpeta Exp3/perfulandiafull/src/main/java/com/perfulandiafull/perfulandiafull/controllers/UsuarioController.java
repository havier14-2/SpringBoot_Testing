package com.perfulandiafull.perfulandiafull.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.perfulandiafull.perfulandiafull.repositories.AdministradorRepository;
import com.perfulandiafull.perfulandiafull.repositories.ClienteRepository;
import com.perfulandiafull.perfulandiafull.repositories.EncargadoLogisticaRepository;
import com.perfulandiafull.perfulandiafull.repositories.EncargadoVentasRepository;
import com.perfulandiafull.perfulandiafull.repositories.GerenteRepository;
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

    @GetMapping("/usuario")
    public String mostrarUsuarios(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("gerentes", gerenteRepository.findAll());
        model.addAttribute("administrador", administradorRepository.findAll());
        model.addAttribute("ventasas", encargadoVentasRepository.findAll());
        model.addAttribute("logisticas", encargadoLogisticaRepository.findAll());
        return "usuario";
    }

}
