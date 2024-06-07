package com.netflix.store.Netflix.mappers;

import com.netflix.store.Netflix.data.Pelicula;
import com.netflix.store.Netflix.dto.PeliculaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PeliculaMapper {
    PeliculaDTO toDto(Pelicula pelicula);
    Pelicula toEntity(PeliculaDTO peliculaDTO);

    void updatePeliculaFromDto(PeliculaDTO dto, @MappingTarget Pelicula entity);
}
