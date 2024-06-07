package com.netflix.store.Netflix.data;

import jakarta.persistence.*;

@Entity
@Table(name = "Pelicula")
public class Pelicula {
    @Id
    @Column(name = "id_pelicula")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPelicula;
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Column(name = "nombre_corto")
    private String nombreCorto;
    @Column(name = "director")
    private String director;
    @Column(name = "reparto")
    private String reparto;
    @Column(name = "familiar")
    private boolean familiar;
    @Column(name = "productora")
    private String productora;
    @Column(name = "poster")
    private String poster;
    @Column(name = "existencia")
    private int existencia;

    public Pelicula() {
    }

    public Pelicula(Integer idPelicula, String nombreCompleto, String nombreCorto, int existencia, String poster, String productora, boolean familiar, String reparto, String director) {
        this.idPelicula = idPelicula;
        this.nombreCompleto = nombreCompleto;
        this.nombreCorto = nombreCorto;
        this.existencia = existencia;
        this.poster = poster;
        this.productora = productora;
        this.familiar = familiar;
        this.reparto = reparto;
        this.director = director;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    public boolean isFamiliar() {
        return familiar;
    }

    public void setFamiliar(boolean familiar) {
        this.familiar = familiar;
    }

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "idPelicula=" + idPelicula +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", nombreCorto='" + nombreCorto + '\'' +
                ", director='" + director + '\'' +
                ", reparto='" + reparto + '\'' +
                ", familiar=" + familiar +
                ", productora='" + productora + '\'' +
                ", poster='" + poster + '\'' +
                ", existencia=" + existencia +
                '}';
    }
}
