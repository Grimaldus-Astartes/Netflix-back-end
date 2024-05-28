package com.netflix.store.Netflix.mappers;

import com.netflix.store.Netflix.data.Pelicula;
import com.netflix.store.Netflix.dto.PeliculaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PeliculaMapper {
    PeliculaMapper INSTANCE = Mappers.getMapper(PeliculaMapper.class);

    PeliculaDTO toDto(Pelicula pelicula);
    Pelicula toEntity(PeliculaDTO peliculaDTO);
}
