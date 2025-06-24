package com.pedidos.pedidos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pedidos.pedidos.entities.Pedidos;
import com.pedidos.pedidos.respositories.PedidosRepository;
@Controller
public class PedidosControllers {
    @Autowired
PedidosRepository pedidosRepository;

@GetMapping("/pedidos")
public String verPedidos(Model model) {
    List<Pedidos> pedidos = (List<Pedidos>) pedidosRepository.findAll();
    model.addAttribute("pedidos", pedidos);
    return "pedidos";
}


}
