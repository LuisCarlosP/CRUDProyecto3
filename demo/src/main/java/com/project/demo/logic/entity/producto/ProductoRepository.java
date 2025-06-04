package com.project.demo.logic.entity.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE %?1%")
    List<Producto> findProductosWithCharacterInNombre(String character);

    List<Producto> findByCategoriaId(Long categoriaId);
}