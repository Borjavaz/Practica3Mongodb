// PeliculaController.java
package org.example.controller;

import org.example.model.Peliculas;
import org.example.service.PeliculasService;
import org.example.service.ActoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PeliculaController.MAPPING)
public class PeliculaController {

    public static final String MAPPING = "/postgres/peliculass";

    @Autowired
    private PeliculasService peliculasService;
    @Autowired
    private ActoresService actoresService;

    @GetMapping
    public List<Peliculas> getAll() {
        return peliculasService.obterTodosPeliculass();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Peliculas> getById(@PathVariable Long id) {
        return peliculasService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("titulo/{titulo}")
    public ResponseEntity<List<Peliculas>> getByTitulo(@PathVariable String titulo){
        List<Peliculas> p = peliculasService.obterPeliculasTitulo(titulo);
        if (p == null || p.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(p);

    }

    @PostMapping
    public ResponseEntity<Peliculas> create(@RequestBody Peliculas peliculas) { //acepta crear actoreses no peliculas porque se crea 1º peliculas
        Peliculas gardado = peliculasService.save(peliculas);
        return ResponseEntity.ok(gardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Peliculas> update(@PathVariable Long id,
                                            @RequestBody Peliculas datos) {
      /*  return peliculasService.findById(id)
                .map(e -> {
                    e.setNome(datos.getNome());
                    e.setCidade(datos.getCidade());
                    return ResponseEntity.ok(peliculasService.save(e));
                })
                .orElse(ResponseEntity.notFound().build());
        */

        var peliculasOptional= peliculasService.findById(id);
        if(peliculasOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Peliculas peliculasToUpdate = peliculasOptional.get();
        peliculasToUpdate.setXenero(datos.getXenero());
        peliculasToUpdate.setTitulo(datos.getTitulo());
        peliculasToUpdate = peliculasService.save(peliculasToUpdate);

        return ResponseEntity.ok(peliculasToUpdate);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!peliculasService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        peliculasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}