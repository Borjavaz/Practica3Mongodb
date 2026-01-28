package org.example.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.PeliculaMongo;
import org.example.model.ActorMongo;
import org.example.repository.PeliculasRepository;
import org.example.repository.ActoresRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class PeliculasService {

    private final PeliculasRepository PeliculasRepo;
    private final ActoresRepository ActoresRepo;

    public PeliculasService(PeliculasRepository PeliculasRepo, ActoresRepository ActoresRepo) {
        this.PeliculasRepo = PeliculasRepo;
        this.ActoresRepo = ActoresRepo;
    }

    public void crearActualizarPeliculas(PeliculaMongo a) {
        PeliculasRepo.save(a);
    }

    // Borrar Alumno
    public void borrarPeliculases (){
        PeliculasRepo.deleteAll();
    }


    public PeliculaMongo buscarPeliculas(Long id) {
        return PeliculasRepo.findById(id).orElse(null);
    }
    // Buscar Peliculas
    public List<PeliculaMongo> buscarPeliculass() {
        return PeliculasRepo.findAll();
    }

    // Buscar ActoresDePeliculas
    public List<ActorMongo> buscarActoresDePeliculas(Long idPeliculas) {
        PeliculaMongo Peliculas = buscarPeliculas(idPeliculas);
        if (Peliculas == null) return null;

        List<ActorMongo> acts = Peliculas.getActores();
        List<ActorMongo> actores = new ArrayList<>();
        for (ActorMongo act:acts){
            actores.add(ActoresRepo.findById(act.getIdactor()).orElse(null));
        }
        return actores;
    }

    public List<PeliculaMongo> subirJson(){
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("src/main/java/org/example/Json/Peliculas.json");
            Type tipoLista = new TypeToken<List<PeliculaMongo>>() {}.getType();
            List<PeliculaMongo> peliculases = gson.fromJson(reader, tipoLista);

            for (PeliculaMongo peliculas : peliculases) {
                PeliculasRepo.save(peliculas);
            }

            return peliculases;
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado. "+e.getMessage());
            return null;
        }
    }

    public boolean bajarJson(){
        List<PeliculaMongo> ps = buscarPeliculass();
        Gson gson = new Gson();

        try (FileWriter escritor = new FileWriter("src/main/java/org/example/Json/Peliculas.json")){

            String json = gson.toJson(ps);
            escritor.write(json);

        } catch (IOException e) {
            System.out.println("Error. "+e.getMessage());
            return false;
        }
        return true;
    }


}