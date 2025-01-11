package com.panelcontrol.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panelcontrol.model.Usuario;
import com.panelcontrol.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        try {
            // Llama al servicio API para obtener usuarios
            ResponseEntity<String> response = apiService.obtenerUsuarios();
            String responseBody = response.getBody();

            // Mapea el JSON a una lista de objetos Usuario
            ObjectMapper objectMapper = new ObjectMapper();
            List<Usuario> usuarios = objectMapper.readValue(responseBody, new TypeReference<List<Usuario>>() {});
            
            // Añade los usuarios al modelo para Thymeleaf
            model.addAttribute("usuarios", usuarios);
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener usuarios: " + e.getMessage());
        }
        return "usuarios"; // Nombre del archivo HTML en templates
    }

    @GetMapping("/crear-usuario")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "crear-usuario";
    }

    @PostMapping("/crear-usuario")
    public String crearUsuario(@ModelAttribute Usuario usuario, Model model) {
        try {
            apiService.crearUsuario(usuario);
            return "redirect:/usuarios";
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear usuario: " + e.getMessage());
            return "crear-usuario";
        }
    }

    @GetMapping("/actualizar-usuario/{id}")
    public String mostrarFormularioActualizar(@PathVariable int id, Model model) {
        try {
            Usuario usuario = apiService.obtenerUsuarioPorId(id);
            model.addAttribute("usuario", usuario);
            return "actualizar-usuario";
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener usuario: " + e.getMessage());
            return "redirect:/usuarios";
        }
    }

    @PostMapping("/actualizar-usuario")
    public String actualizarUsuario(@ModelAttribute Usuario usuario, Model model) {
        try {
            apiService.actualizarUsuario(usuario);
            return "redirect:/usuarios";
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar usuario: " + e.getMessage());
            return "actualizar-usuario";
        }
    }

    @GetMapping("/buscar-usuario")
    public String mostrarFormularioBuscar(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "buscar-usuario";
    }

    @PostMapping("/buscar-usuario")
    public String buscarUsuario(@ModelAttribute Usuario usuario, Model model) {
        try {
            Usuario usuarioEncontrado = apiService.buscarUsuarioPorNombre(usuario.getNombre());
            model.addAttribute("usuarioEncontrado", usuarioEncontrado);
            return "resultado-buscar";
        } catch (Exception e) {
            model.addAttribute("error", "Error al buscar usuario: " + e.getMessage());
            return "buscar-usuario";
        }
    }

    @GetMapping("/eliminar-usuario/{id}")
    public String eliminarUsuario(@PathVariable int id, Model model) {
        try {
            apiService.eliminarUsuario(id);
            return "redirect:/usuarios";
        } catch (Exception e) {
            model.addAttribute("error", "Error al eliminar usuario: " + e.getMessage());
            return "redirect:/usuarios";
        }
    }
}
