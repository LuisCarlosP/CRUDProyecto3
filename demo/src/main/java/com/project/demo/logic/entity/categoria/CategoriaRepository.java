package com.project.demo.logic.entity.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("SELECT c FROM Categoria c WHERE LOWER(c.nombre) LIKE %?1%")
    List<Categoria> findCategoriasWithCharacterInNombre(String character);

    Optional<Categoria> findByNombre(String nombre);
}