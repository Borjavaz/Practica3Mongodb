package org.example;

import org.example.model.ActorMongo;
import org.example.model.PeliculaMongo;
import org.example.service.ConexionService;
import org.example.service.PeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Secuencia {

    private final ConexionService conexionService;
    private final PeliculasService peliculasService;

    @Autowired
    public Secuencia(ConexionService conexionService, PeliculasService peliculasService) {
        this.conexionService = conexionService;
        this.peliculasService = peliculasService;
    }

    public void executar() {
        List<ActorMongo> acts = new ArrayList<>();

        // --- Película 1: "Memento Mori" ---
        ActorMongo ac1 = new ActorMongo();
        ac1.setApelidos("Bardem");
        ac1.setNome("Javier");
        ac1.setNacionalidade("Española");

        ActorMongo ac2 = new ActorMongo();
        ac2.setApelidos("Blunt");
        ac2.setNome("Emily");
        ac2.setNacionalidade("Británica");

        ActorMongo ac3 = new ActorMongo();
        ac3.setApelidos("Pascal");
        ac3.setNome("Pedro");
        ac3.setNacionalidade("Chilena");

        acts.add(ac1);
        acts.add(ac2);
        acts.add(ac3);

        PeliculaMongo p1 = new PeliculaMongo();
        p1.setTitulo("Memento Mori");
        p1.setXenero("Thriller");
        p1.setAno(2025);
        p1.setActores(acts);

        // --- Película 2: "Sombras do Mañá" ---
        acts = new ArrayList<>();

        ActorMongo ac4 = new ActorMongo();
        ac4.setApelidos("Darín");
        ac4.setNome("Ricardo");
        ac4.setNacionalidade("Arxentina");

        ActorMongo ac5 = new ActorMongo();
        ac5.setApelidos("Cotillard");
        ac5.setNome("Marion");
        ac5.setNacionalidade("Francesa");

        ActorMongo ac6 = new ActorMongo();
        ac6.setApelidos("Mikkelsen");
        ac6.setNome("Mads");
        ac6.setNacionalidade("Danesa");

        acts.add(ac4);
        acts.add(ac5);
        acts.add(ac6);

        PeliculaMongo p2 = new PeliculaMongo();
        p2.setTitulo("Sombras do Mañá");
        p2.setXenero("Drama");
        p2.setAno(2025);
        p2.setActores(acts);

        // Guardar películas
        p1 = conexionService.createPeliculas(p1);
        p2 = conexionService.createPeliculas(p2);

        // Recuperar y procesar Película 1
        PeliculaMongo p1P = conexionService.getPeliculasById(p1.getId_pelicula());
        System.out.println(p1P);
        peliculasService.crearActualizarPeliculas(p1P);

        // Recuperar y procesar Película 2 (buscando por el nuevo título)
        List<PeliculaMongo> p2P = conexionService.getPeliculasByTitulo("Sombras do Mañá");
        System.out.println(p2P);

        if (!p2P.isEmpty()) {
            peliculasService.crearActualizarPeliculas(p2P.get(0));
        }

        peliculasService.bajarJson();
    }
}