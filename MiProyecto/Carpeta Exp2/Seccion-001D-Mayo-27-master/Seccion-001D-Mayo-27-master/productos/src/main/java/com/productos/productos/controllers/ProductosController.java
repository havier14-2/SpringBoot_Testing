package com.productos.productos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.productos.productos.entities.Producto;
import com.productos.productos.repositories.ProductoRepository;
@Controller
public class ProductosController {
    
    @Autowired
    ProductoRepository productorepository;

    @GetMapping("/productos")
    public String verProductos(Model model){
        List<Producto> productitos = (List<Producto>) productorepository.findAll();
        model.addAttribute("productitos", productitos);
        return "productos";
    } 

}
