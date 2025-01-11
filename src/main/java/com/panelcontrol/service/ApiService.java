package com.panelcontrol.service;

import com.panelcontrol.model.Usuario;
import com.panelcontrol.model.Inventario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class ApiService {

    @Value("${api.url.usuarios}")
    private String apiUrlUsuarios;

    @Value("${api.url.inventario}")
    private String apiUrlInventario;

    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Métodos para gestionar usuarios
    public ResponseEntity<String> obtenerUsuarios() {
        return restTemplate.getForEntity(apiUrlUsuarios, String.class);
    }

    public void crearUsuario(Usuario usuario) {
        restTemplate.postForEntity(apiUrlUsuarios, usuario, String.class);
    }

    public Usuario obtenerUsuarioPorId(int id) {
        String url = apiUrlUsuarios + "/" + id;
        return restTemplate.getForObject(url, Usuario.class);
    }

    public void actualizarUsuario(Usuario usuario) {
        String url = apiUrlUsuarios + "/" + usuario.getId();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Usuario> entity = new HttpEntity<>(usuario, headers);
        restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
    }

    public Usuario buscarUsuarioPorNombre(String nombre) {
        String url = apiUrlUsuarios + "/buscar?nombre=" + nombre;
        return restTemplate.getForObject(url, Usuario.class);
    }

    public void eliminarUsuario(int id) {
        String url = apiUrlUsuarios + "/" + id;
        restTemplate.delete(url);
    }

    // Métodos para gestionar inventario
    public ResponseEntity<String> obtenerInventario() {
        return restTemplate.getForEntity(apiUrlInventario, String.class);
    }

    public void crearProducto(Inventario producto) {
        restTemplate.postForEntity(apiUrlInventario, producto, String.class);
    }

    public Inventario obtenerProductoPorId(int id) {
        String url = apiUrlInventario + "/" + id;
        return restTemplate.getForObject(url, Inventario.class);
    }

    public void actualizarProducto(Inventario producto) {
        String url = apiUrlInventario + "/" + producto.getId();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Inventario> entity = new HttpEntity<>(producto, headers);
        restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
    }

    public Inventario buscarProductoPorNombre(String nombre) {
        String url = apiUrlInventario + "/buscar?nombre=" + nombre;
        return restTemplate.getForObject(url, Inventario.class);
    }

    public void eliminarProducto(int id) {
        String url = apiUrlInventario + "/" + id;
        restTemplate.delete(url);
    }
}
