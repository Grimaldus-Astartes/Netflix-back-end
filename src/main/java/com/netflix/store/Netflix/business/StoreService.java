package com.netflix.store.Netflix.business;

import com.netflix.store.Netflix.data.FormPeliculaRepository;
import com.netflix.store.Netflix.data.Pelicula;
import com.netflix.store.Netflix.dto.PeliculaDTO;
import com.netflix.store.Netflix.exceptions.PeliculaIncorrectDataException;
import com.netflix.store.Netflix.exceptions.PeliculaNotFoundException;
import com.netflix.store.Netflix.mappers.PeliculaMapper;
import com.netflix.store.Netflix.exceptions.PeliculaAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService {
    private final FormPeliculaRepository formPeliculaRepository;
    private final PeliculaMapper peliculaMapper;

    @Autowired
    public StoreService(FormPeliculaRepository formPeliculaRepository, PeliculaMapper peliculaMapper) {
        this.formPeliculaRepository = formPeliculaRepository;
        this.peliculaMapper = peliculaMapper;
    }

    public List<PeliculaDTO> getPeliculas() {
        List<Pelicula> peliculas = formPeliculaRepository.findAll();
        return peliculas.stream()
                .map(peliculaMapper::toDto)
                .collect(Collectors.toList());
    }

    public PeliculaDTO savePelicula(PeliculaDTO peliculaDTO) throws SQLException {
        System.out.println("Entrando a savePelicula con id: " + peliculaDTO.getIdPelicula());

        if (peliculaDTO.getIdPelicula() != null) {
            System.out.println("Buscando película con id: " + peliculaDTO.getIdPelicula());
            Optional<Pelicula> existingPelicula = formPeliculaRepository.findById(peliculaDTO.getIdPelicula());

            System.out.println("Resultado de findById: " + (existingPelicula.isPresent() ? "Encontrado" : "No Encontrado"));

            if (existingPelicula.isPresent()) {
                System.out.println("Lanzando PeliculaAlreadyExistsException para ID: " + peliculaDTO.getIdPelicula());
                throw new PeliculaAlreadyExistsException("La película con el ID " + peliculaDTO.getIdPelicula() + " ya existe.");
            }
        }

        Pelicula pelicula = peliculaMapper.toEntity(peliculaDTO);
        try {
            Pelicula savedPelicula = formPeliculaRepository.save(pelicula);
            System.out.println("Película guardada: " + savedPelicula);
            return peliculaMapper.toDto(savedPelicula);
        } catch (Exception e) {
            System.out.println("Error al guardar la película: " + e.getMessage());
            throw new SQLException("Error al guardar la película: " + e.getMessage());
        }
    }

    public void deletePelicula(Integer idPelicula) throws PeliculaNotFoundException, SQLException {
        //Revisar si el id no esta vacio
        if (idPelicula != null) {
            Optional<Pelicula> peliculaTarget = formPeliculaRepository.findById(idPelicula);
            //Revisar si existe una pelicula con ese id
            if (peliculaTarget.isPresent()) {
                try {
                    formPeliculaRepository.delete(peliculaTarget.get());
                } catch (Exception e) {
                    throw new SQLException("Error al eliminar la pelicula" + e.getMessage());
                }

                return;//fin del método
            }
            throw new PeliculaNotFoundException("La pelicula con el ID" + idPelicula + "no fue encontrada");
        }
        throw new PeliculaNotFoundException("La película con el ID " + idPelicula + " no fue encontrada.");
    }

    public void updatePelicula(PeliculaDTO peliculaDTO) {
        if (peliculaDTO == null || peliculaDTO.getIdPelicula() == null) {
            throw new PeliculaIncorrectDataException("Información incorrecta");
        }
        Pelicula pelicula = formPeliculaRepository.findById(peliculaDTO.getIdPelicula())
                .orElseThrow(() -> new PeliculaNotFoundException("La película con el ID " + peliculaDTO.getIdPelicula() + " no fue encontrada"));

        // Actualiza los campos de la película con los valores de PeliculaDTO
        peliculaMapper.updatePeliculaFromDto(peliculaDTO, pelicula);

        // Llama al método para guardar la película actualizada
        formPeliculaRepository.save(pelicula);
    }
}
