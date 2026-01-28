package org.example;


import org.example.model.ActorMongo;
import org.example.model.PeliculaMongo;
import org.example.service.ConexionService;
import org.example.service.PeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

        ActorMongo ac1 = new ActorMongo();
        ac1.setApelidos("Pepez");
        ac1.setNome("Juan");
        ac1.setNacionalidade("Gambling");


        ActorMongo ac2 = new ActorMongo();
        ac2.setApelidos("Pepez");
        ac2.setNome("Juan");
        ac2.setNacionalidade("Gambling");

        ActorMongo ac3 = new ActorMongo();
        ac3.setApelidos("Pepez");
        ac3.setNome("Juan");
        ac3.setNacionalidade("Gambling");

        acts.add(ac1);
        acts.add(ac2);
        acts.add(ac3);


        PeliculaMongo p1 = new PeliculaMongo();
        p1.setTitulo("Momento Mori");
        p1.setXenero("Pepe");
        p1.setAno(2025);
        p1.setActores(acts);


        acts = new ArrayList<>();

        ActorMongo ac4 = new ActorMongo();
        ac4.setApelidos("Pepez");
        ac4.setNome("Juan");
        ac4.setNacionalidade("Gambling");


        ActorMongo ac5 = new ActorMongo();
        ac5.setApelidos("Pepez");
        ac5.setNome("Juan");
        ac5.setNacionalidade("Gambling");


        ActorMongo ac6 = new ActorMongo();
        ac6.setApelidos("Pepez");
        ac6.setNome("Juan");
        ac6.setNacionalidade("Gambling");

        acts.add(ac4);
        acts.add(ac5);
        acts.add(ac6);

        PeliculaMongo p2 = new PeliculaMongo();

        p2.setTitulo("Pepe");
        p2.setXenero("Pepes");
        p2.setAno(2025);
        p2.setActores(acts);


        p1 = conexionService.createPeliculas(p1);
        p2 = conexionService.createPeliculas(p2);






        PeliculaMongo p1P = conexionService.getPeliculasById(p1.getId_pelicula());
        System.out.println(p1P);

        peliculasService.crearActualizarPeliculas(p1P);

        List<PeliculaMongo> p2P = conexionService.getPeliculasByTitulo("Pepe");
        System.out.println(p2P);

        peliculasService.crearActualizarPeliculas(p2P.get(0));

        peliculasService.bajarJson();
    }
}