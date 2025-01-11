package com.panelcontrol.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panelcontrol.model.Inventario;
import com.panelcontrol.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
public class InventarioController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/inventario")
    public String listarInventario(Model model) {
        try {
            ResponseEntity<String> response = apiService.obtenerInventario();
            String responseBody = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            List<Inventario> inventario = objectMapper.readValue(responseBody, new TypeReference<List<Inventario>>() {});
            model.addAttribute("inventario", inventario);
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener inventario: " + e.getMessage());
        }
        return "inventario";
    }

    @GetMapping("/crear-producto")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("producto", new Inventario());
        return "crear-producto";
    }

    @PostMapping("/crear-producto")
    public String crearProducto(@ModelAttribute Inventario producto, Model model) {
        try {
            apiService.crearProducto(producto);
            return "redirect:/inventario";
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear producto: " + e.getMessage());
            return "crear-producto";
        }
    }

    @GetMapping("/actualizar-producto/{id}")
    public String mostrarFormularioActualizar(@PathVariable int id, Model model) {
        try {
            Inventario producto = apiService.obtenerProductoPorId(id);
            model.addAttribute("producto", producto);
            return "actualizar-producto";
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener producto: " + e.getMessage());
            return "redirect:/inventario";
        }
    }

    @PostMapping("/actualizar-producto")
    public String actualizarProducto(@ModelAttribute Inventario producto, Model model) {
        try {
            apiService.actualizarProducto(producto);
            return "redirect:/inventario";
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar producto: " + e.getMessage());
            return "actualizar-producto";
        }
    }

    @GetMapping("/buscar-producto")
    public String mostrarFormularioBuscar(Model model) {
        model.addAttribute("producto", new Inventario());
        return "buscar-producto";
    }

    @PostMapping("/buscar-producto")
    public String buscarProducto(@ModelAttribute Inventario producto, Model model) {
        try {
            Inventario productoEncontrado = apiService.buscarProductoPorNombre(producto.getNombre());
            model.addAttribute("productoEncontrado", productoEncontrado);
            return "resultado-buscar";
        } catch (Exception e) {
            model.addAttribute("error", "Error al buscar producto: " + e.getMessage());
            return "buscar-producto";
        }
    }

    @GetMapping("/eliminar-producto/{id}")
    public String eliminarProducto(@PathVariable int id, Model model) {
        try {
            apiService.eliminarProducto(id);
            return "redirect:/inventario";
        } catch (Exception e) {
            model.addAttribute("error", "Error al eliminar producto: " + e.getMessage());
            return "redirect:/inventario";
        }
    }
}
