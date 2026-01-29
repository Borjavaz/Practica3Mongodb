// Actores.java (PostgreSQL)
package org.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "actores")
public class Actores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idactor")
    private Long idactor;

    @Column(name = "nome")
    private String nome;

    @Column(name = "apelidos")
    private String apelidos;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @ManyToOne
    @JoinColumn(name = "id_pelicula")
    @JsonBackReference
    private Peliculas peliculas;

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

    public Peliculas getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Peliculas peliculas) {
        this.peliculas = peliculas;
    }
}