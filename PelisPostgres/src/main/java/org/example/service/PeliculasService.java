// PeliculasService.java (PostgreSQL)
package org.example.service;

import org.example.model.Peliculas;
import org.example.repository.PeliculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculasService {

    private final PeliculasRepository peliculasRepository;

    @Autowired
    public PeliculasService(PeliculasRepository peliculasRepository) {
        this.peliculasRepository = peliculasRepository;
    }

    public Peliculas save(Peliculas peliculas) {
        return peliculasRepository.save(peliculas);
    }

    public boolean existe(Long id) {
        return peliculasRepository.existsById(id);
    }

    public void delete(Long id) {
        peliculasRepository.deleteById(id);
    }
    public List<Peliculas> obterPeliculasTitulo(String titulo) {
        return peliculasRepository.findByTitulo(titulo);
    }

    public List<Peliculas> obterPeliculasXenero(String xenero) {
        return peliculasRepository.findByXenero(xenero);
    }

    public Optional<Peliculas> findById(Long id) {
        return peliculasRepository.findById(id);
    }

    public List<Peliculas> obterTodosPeliculass() {
        return peliculasRepository.findAll();
    }

}