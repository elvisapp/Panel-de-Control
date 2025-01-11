package com.panelcontrol.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Agregar lógica si es necesario
        // Por ejemplo, capturar el código de error:
        String errorMsg = (String) request.getAttribute("javax.servlet.error.message");
        request.setAttribute("errorMessage", errorMsg);
        return "error";  // Nombre de la vista error.html
    }

    // El método getErrorPath ya no es necesario en Spring Boot 2.x y versiones posteriores
}
