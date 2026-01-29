// ActoresRepository.java (PostgreSQL)
package org.example.repository;

import org.example.model.Actores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActoresRepository extends JpaRepository<Actores,Long> {
    List<Actores> findByNome(String nome);
    List<Actores> findByNacionalidade(String nacionalidade);
}