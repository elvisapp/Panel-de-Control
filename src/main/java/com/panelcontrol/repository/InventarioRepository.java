package com.panelcontrol.repository;

import com.panelcontrol.model.Inventario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends CrudRepository<Inventario, Integer> {
    // Puedes agregar métodos específicos si es necesario
}
