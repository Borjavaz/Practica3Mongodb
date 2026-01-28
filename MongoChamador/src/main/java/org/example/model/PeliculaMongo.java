package org.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "peliculas")
public class PeliculaMongo {
    @Id
    private Long id_pelicula;

    private String titulo;

    private String xenero;

    private int ano;

    private List<ActorMongo> actores;

    public Long getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(Long id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getXenero() {
        return xenero;
    }

    public void setXenero(String xenero) {
        this.xenero = xenero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public List<ActorMongo> getActores() {
        return actores;
    }

    public void setActores(List<ActorMongo> actores) {
        this.actores = actores;
    }

    @Override
    public String toString() {
        return "Peliculas{" +
                "id_pelicula=" + id_pelicula +
                ", titulo='" + titulo + '\'' +
                ", xenero='" + xenero + '\'' +
                ", ano=" + ano +
                ", actores=" + actores +
                '}';
    }
}