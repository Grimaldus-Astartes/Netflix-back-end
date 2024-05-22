package com.netflix.store.Netflix.webservice;

import com.netflix.store.Netflix.business.StoreService;
import com.netflix.store.Netflix.data.Pelicula;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
}
