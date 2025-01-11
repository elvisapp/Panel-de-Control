package com.panelcontrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int edad;

    @JsonProperty("identificacion_fiscal") // Mapea "identificacion_fiscal" del JSON a este atributo
    private String identificacionFiscal;

    private String direccion;

    private String email;

    @JsonProperty("foto_perfil") // Mapea "foto_perfil" del JSON a este atributo
    private String fotoPerfil;

    public Usuario() {}

    public Usuario(Long id, String nombre, int edad, String identificacionFiscal, String direccion, String email, String fotoPerfil) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.identificacionFiscal = identificacionFiscal;
        this.direccion = direccion;
        this.email = email;
        this.fotoPerfil = fotoPerfil;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getIdentificacionFiscal() {
        return identificacionFiscal;
    }

    public void setIdentificacionFiscal(String identificacionFiscal) {
        this.identificacionFiscal = identificacionFiscal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", identificacionFiscal='" + identificacionFiscal + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", fotoPerfil='" + fotoPerfil + '\'' +
                '}';
    }
}
