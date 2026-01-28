package org.example.controller;

import org.example.model.Peliculas;
import org.example.model.Actores;
import org.example.service.PeliculasService;
import org.example.service.ActoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ActorController.MAPPING)
public class ActorController {

    public static final String MAPPING = "/postgres/actoreses";

    @Autowired
    private PeliculasService peliculasService;
    @Autowired
    private ActoresService actoresService;


    @GetMapping
    public List<Actores> getAll() {
        return actoresService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actores> getById(@PathVariable Long id) {
        return actoresService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Actores> create(@RequestBody Actores actores) { //xogamos con recoller o peliculas e metelo en cada actores
        if (actores.getPeliculas() != null && actores.getPeliculas().getId_pelicula() != null) {
            Peliculas eq = peliculasService.findById(actores.getPeliculas().getId_pelicula())
                    .orElse(null);
            if (eq == null) {
                return ResponseEntity.badRequest().build();
            }
            actores.setPeliculas(eq);
        }
        Actores gardado = actoresService.save(actores);
        return ResponseEntity.ok(gardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actores> update(@PathVariable Long id,
                                          @RequestBody Actores datos) {
        return actoresService.findById(id)
                .map(x -> {
                    x.setNome(datos.getNome());
                    x.setApelidos(datos.getApelidos());
                    x.setNacionalidade(datos.getNacionalidade());

                    if (datos.getPeliculas() != null && datos.getPeliculas().getId_pelicula() != null) {
                        Peliculas eq = peliculasService.findById(datos.getPeliculas().getId_pelicula())
                                .orElse(null);
                        x.setPeliculas(eq);
                    }

                    return ResponseEntity.ok(actoresService.save(x));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!actoresService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        actoresService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}