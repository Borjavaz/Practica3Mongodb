package org.example.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "peliculas")
public class Peliculas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpelicula")
    private Long id_pelicula;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "xenero")
    private String xenero;

    @Column(name = "ano")
    private int ano;

    @OneToMany(mappedBy = "peliculas", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Actores> actores;

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

    public List<Actores> getActores() {
        return actores;
    }

    public void setActores(List<Actores> actores) {
        this.actores = actores;
    }
}