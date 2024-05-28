package com.netflix.store.Netflix.dto;

public class PeliculaDTO {
    private int idPelicula;
    private String nombreCompleto;
    private String nombreCorto;
    private String director;
    private String reparto;
    private boolean familiar;
    private String productora;
    private String poster;
    private int existencia;

    @Override
    public String toString() {
        return "PeliculaDTO{" +
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

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
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
}
