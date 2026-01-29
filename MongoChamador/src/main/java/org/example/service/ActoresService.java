package org.example.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.ActorMongo;
import org.example.repository.ActoresRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class ActoresService {

    private final ActoresRepository ActoresRepo;

    public ActoresService(ActoresRepository ActoresRepo) {
        this.ActoresRepo = ActoresRepo;
    }

    public void crearActualizarActores(ActorMongo a) {
        ActoresRepo.save(a);
    }

    public void borrarActoress() {
        ActoresRepo.deleteAll();
    }

    public ActorMongo buscarActores(Long id) {
        return ActoresRepo.findById(id).orElse(null);
    }
    // Buscar Actores
    public List<ActorMongo> buscarActoreses() {
        return ActoresRepo.findAll();
    }

    public List<ActorMongo> subirJson(){
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("/home/dam/Escritorio/AD/2ºTRIMESTRE/Mongo/Practica3Mongo/MongoChamador/src/main/java/org/example/JSON/Actores.json");
            Type tipoLista = new TypeToken<List<ActorMongo>>() {}.getType();
            List<ActorMongo> actoress = gson.fromJson(reader, tipoLista);

            for (ActorMongo actores : actoress) {
                ActoresRepo.save(actores);
            }

            return actoress;
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado. "+e.getMessage());
            return null;
        }
    }
}