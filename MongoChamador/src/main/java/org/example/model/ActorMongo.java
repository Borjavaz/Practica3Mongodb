package org.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class ActorMongo {
    @Id
    private Long idactor;


    private String nome;


    private String apelidos;

    private String nacionalidade;
    private Long idpelicula;

    public Long getIdactor() {
        return idactor;
    }

    public void setIdactor(Long idactor) {
        this.idactor = idactor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelidos() {
        return apelidos;
    }

    public void setApelidos(String apelidos) {
        this.apelidos = apelidos;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Long getId_peliculas() {
        return idpelicula;
    }

    public void setId_peliculas(Long id_peliculas) {
        this.idpelicula = id_peliculas;
    }

    @Override
    public String toString() {
        return "Actores{" +
                "idactor=" + idactor +
                ", nome='" + nome + '\'' +
                ", apelidos='" + apelidos + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", id_peliculas=" + idpelicula +
                '}';
    }
}