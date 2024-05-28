package com.netflix.store.Netflix.webservice;

import com.netflix.store.Netflix.business.StoreService;
import com.netflix.store.Netflix.data.Pelicula;
import com.netflix.store.Netflix.dto.PeliculaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PeliculaWebService implements WebMvcConfigurer {
    private final StoreService service;

    public PeliculaWebService(StoreService service) {
        this.service = service;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }

    @GetMapping(value = "/peliculas")
    public List<Pelicula> getPeliculas() {
        List<Pelicula> peliculas = service.getPeliculas();
        System.out.println(peliculas);
        return peliculas;
    }

    @PostMapping(value = "/peliculas")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPelicula(@RequestBody PeliculaDTO peliculaDTO) {
        try {
            service.savePelicula(peliculaDTO);
        } catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
    }
}
