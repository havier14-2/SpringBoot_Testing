package com.perfulandiafull.perfulandiafull.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.perfulandiafull.perfulandiafull.entities.Producto;
import com.perfulandiafull.perfulandiafull.repositories.ProductoRepository;
@Controller
public class PedidosController {
    @Autowired
    ProductoRepository productorepository;

    @GetMapping("/pedidos")
    public String verProductos(Model model){
        List<Producto> pediditos = (List<Producto>) productorepository.findAll();
        model.addAttribute("pediditos", pediditos);
        return "pedidos";

}
}
