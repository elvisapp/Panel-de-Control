package com.panelcontrol.controller;

import com.panelcontrol.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PanelController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/panel/dashboard")
    public String mostrarDashboard(Model model) {
        String usuarios = apiService.obtenerUsuarios().getBody();
        String inventario = apiService.obtenerInventario().getBody();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("inventario", inventario);
        return "dashboard"; // Certifique-se de que este arquivo existe em /templates
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Certifique-se de que este arquivo existe em /templates
    }
}
