package com.netflix.store.Netflix.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PeliculaDTO {
    private Integer idPelicula;

    @NotNull(message = "El nombre completo no puede ser nulo")
    @Size(min = 1, max = 100, message = "El nombre completo debe tener entre 1 y 100 caracteres")
    private String nombreCompleto;

    @NotNull(message = "El nombre corto no puede ser nulo")
    @Size(min = 1, max = 50, message = "El nombre corto debe tener entre 1 y 50 caracteres")
    private String nombreCorto;

    @NotNull(message = "El director no puede ser nulo")
    @Size(min = 1, max = 100, message = "El director debe tener entre 1 y 100 caracteres")
    private String director;

    @NotNull(message = "El reparto no puede ser nulo")
    @Size(min = 1, max = 200, message = "El reparto debe tener entre 1 y 200 caracteres")
    private String reparto;

    @NotNull(message = "El campo familiar no puede ser nulo")
    private Boolean familiar;

    @NotNull(message = "La productora no puede ser nula")
    @Size(min = 1, max = 100, message = "La productora debe tener entre 1 y 100 caracteres")
    private String productora;

    @NotNull(message = "El poster no puede ser nulo")
    @Size(min = 1, max = 200, message = "El poster debe tener entre 1 y 200 caracteres")
    private String poster;

    @NotNull(message = "La existencia no puede ser nula")
    private Integer existencia;

    public PeliculaDTO(Integer idPelicula, String nombreCorto, String nombreCompleto, String director, Boolean familiar, String reparto, String productora, String poster, Integer existencia) {
        this.idPelicula = idPelicula;
        this.nombreCorto = nombreCorto;
        this.nombreCompleto = nombreCompleto;
        this.director = director;
        this.familiar = familiar;
        this.reparto = reparto;
        this.productora = productora;
        this.poster = poster;
        this.existencia = existencia;
    }

    public PeliculaDTO() {
    }

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
}
