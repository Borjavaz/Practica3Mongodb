package org.example.service;

import org.example.model.ActorMongo;
import org.example.model.PeliculaMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ConexionService {

    @Autowired
    private RestTemplate restTemplate;


    private static final String POSTGRES_BASE_URL_PELICULASES = "http://localhost:8081/postgres/peliculass";
    private static final String POSTGRES_BASE_URL_ACTORESS = "http://localhost:8081/postgres/actoreses";
    // ========== PELICULASES ==========
    public List<PeliculaMongo> getAllPeliculases() {
        try {
            String url = POSTGRES_BASE_URL_PELICULASES;
            ResponseEntity<List<PeliculaMongo>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<PeliculaMongo>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao obter peliculases: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public PeliculaMongo getPeliculasById(Long id) {
        try {
            String url = POSTGRES_BASE_URL_PELICULASES + "/" + id;
            ResponseEntity<PeliculaMongo> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, PeliculaMongo.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao obter peliculas " + id + ": " + e.getMessage());
            return null;
        }
    }
    public List<PeliculaMongo> getPeliculasByTitulo(String titulo) {
        try {
            String url = POSTGRES_BASE_URL_PELICULASES + "/titulo/" + titulo;
            ResponseEntity<List<PeliculaMongo>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<PeliculaMongo>>() {}
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao obter peliculas " + titulo + ": " + e.getMessage());
            return null;
        }
    }

    public PeliculaMongo createPeliculas(PeliculaMongo peliculas) {
        try {
            String url = POSTGRES_BASE_URL_PELICULASES;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<PeliculaMongo> request = new HttpEntity<>(peliculas, headers);

            ResponseEntity<PeliculaMongo> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, PeliculaMongo.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao crear peliculas: " + e.getMessage());
            return null;
        }
    }

    public PeliculaMongo updatePeliculas(Long id, PeliculaMongo datos) {
        try {
            String url = POSTGRES_BASE_URL_PELICULASES + "/" + id;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<PeliculaMongo> request = new HttpEntity<>(datos, headers);

            ResponseEntity<PeliculaMongo> response = restTemplate.exchange(
                    url, HttpMethod.PUT, request, PeliculaMongo.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao actualizar peliculas " + id + ": " + e.getMessage());
            return null;
        }
    }

    public boolean deletePeliculas(Long id) {
        try {
            String url = POSTGRES_BASE_URL_PELICULASES + "/" + id;
            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao borrar peliculas " + id + ": " + e.getMessage());
            return false;
        }
    }

    //ACTORES
    public List<ActorMongo> getAllActoress() {
        try {
            String url = POSTGRES_BASE_URL_ACTORESS;
            ResponseEntity<List<ActorMongo>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<ActorMongo>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao obter actoress: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public ActorMongo getActoresById(Long id) {
        try {
            String url = POSTGRES_BASE_URL_ACTORESS + "/" + id;
            ResponseEntity<ActorMongo> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, ActorMongo.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao obter actores " + id + ": " + e.getMessage());
            return null;
        }
    }

    public ActorMongo createActores(ActorMongo actores) {
        try {
            String url = POSTGRES_BASE_URL_ACTORESS;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<ActorMongo> request = new HttpEntity<>(actores, headers);
            ResponseEntity<ActorMongo> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, ActorMongo.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao crear actores: " + e.getMessage());
            return null;
        }
    }

    public ActorMongo updateActores(Long id, ActorMongo datos) {
        try {
            String url = POSTGRES_BASE_URL_ACTORESS + "/" + id;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<ActorMongo> request = new HttpEntity<>(datos, headers);
            ResponseEntity<ActorMongo> response = restTemplate.exchange(
                    url, HttpMethod.PUT, request, ActorMongo.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao actualizar actores " + id + ": " + e.getMessage());
            return null;
        }
    }

    public boolean deleteActores(Long id) {
        try {
            String url = POSTGRES_BASE_URL_ACTORESS + "/" + id;
            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao borrar actores " + id + ": " + e.getMessage());
            return false;
        }
    }
}