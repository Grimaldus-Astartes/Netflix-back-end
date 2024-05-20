package com.netflix.store.Netflix.business;

import com.netflix.store.Netflix.data.FormPeliculaRepository;
import com.netflix.store.Netflix.data.Pelicula;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    private final FormPeliculaRepository formPeliculaRepository;

    public StoreService(FormPeliculaRepository formPeliculaRepository) {
        this.formPeliculaRepository = formPeliculaRepository;
    }

    public List<Pelicula> getPeliculas(){
        return formPeliculaRepository.findAll();
    }
}
