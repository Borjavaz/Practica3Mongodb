package org.example.service;

import org.example.model.Actores;
import org.example.repository.ActoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActoresService {
    @Autowired
    private final ActoresRepository actoresRepository;

    public ActoresService(ActoresRepository actoresRepository) {
        this.actoresRepository = actoresRepository;
    }
    public List<Actores> findAll(){
        return actoresRepository.findAll();
    }
    public Optional<Actores> findById(Long id) {
        return actoresRepository.findById(id);
    }

    public Actores save(Actores actores) {
        return actoresRepository.save(actores);
    }

    public boolean existsById(Long id) {
        return actoresRepository.existsById(id);
    }

    public void deleteById(Long id) {
        actoresRepository.deleteById(id);
    }

}