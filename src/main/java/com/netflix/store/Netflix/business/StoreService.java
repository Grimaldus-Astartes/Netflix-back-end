package com.netflix.store.Netflix.business;

import com.netflix.store.Netflix.data.FormPeliculaRepository;
import com.netflix.store.Netflix.data.Pelicula;
import com.netflix.store.Netflix.dto.PeliculaDTO;
import com.netflix.store.Netflix.mappers.PeliculaMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    public PeliculaDTO savePelicula(PeliculaDTO peliculaDTO) throws SQLException {
        Pelicula pelicula = PeliculaMapper.INSTANCE.toEntity(peliculaDTO);
        Pelicula savedPelicula = formPeliculaRepository.save(pelicula);
        return PeliculaMapper.INSTANCE.toDto(savedPelicula);
    }
}
