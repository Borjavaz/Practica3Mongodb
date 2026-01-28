package org.example.repository;

import org.example.model.Actores;
import org.example.model.Peliculas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculasRepository extends JpaRepository<Peliculas,Long> {
    List<Peliculas> findByTitulo(String titulo);
    List<Peliculas> findByXenero(String xenero);
}